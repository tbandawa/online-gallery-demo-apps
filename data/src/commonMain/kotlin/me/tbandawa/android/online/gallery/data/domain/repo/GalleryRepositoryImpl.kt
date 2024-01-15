package me.tbandawa.android.online.gallery.data.domain.repo

import com.squareup.sqldelight.db.SqlDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import me.tbandawa.android.online.gallery.data.cache.Database
import me.tbandawa.android.online.gallery.data.domain.mappers.ErrorMapper
import me.tbandawa.android.online.gallery.data.domain.mappers.ProfileMapper
import me.tbandawa.android.online.gallery.data.domain.mappers.UserMapper
import me.tbandawa.android.online.gallery.data.domain.models.Profile
import me.tbandawa.android.online.gallery.data.domain.models.User
import me.tbandawa.android.online.gallery.data.remote.api.BaseApiCall
import me.tbandawa.android.online.gallery.data.remote.api.GalleryApi
import me.tbandawa.android.online.gallery.data.remote.requests.SignInRequest
import me.tbandawa.android.online.gallery.data.remote.requests.UserRequest
import me.tbandawa.android.online.gallery.data.remote.state.ResourceState

class GalleryRepositoryImpl(
    sqlDriver: SqlDriver,
    private val galleryApi: GalleryApi,
    private val userMapper: UserMapper,
    private val profileMapper: ProfileMapper,
    errorMapper: ErrorMapper
): GalleryRepository, BaseApiCall(errorMapper) {

    private val database = Database(sqlDriver)
    override fun getUser(): User? = database.getUser()

    override suspend fun signInUser(signInRequest: SignInRequest): Flow<ResourceState<User>> = flow {
        emit(ResourceState.Loading)
        emit(handleApiCall {
            userMapper.mapToModel(galleryApi.signInUser(signInRequest))
        }.also { results ->
            if (results is ResourceState.Success) {
                database.clearUser()
                database.saveUser(results.data)
            }
        })
    }.flowOn(Dispatchers.Main)

    override suspend fun signUpUser(userRequest: UserRequest): Flow<ResourceState<User>> = flow {
        emit(ResourceState.Loading)
        emit(handleApiCall {
            userMapper.mapToModel(galleryApi.signUpUser(userRequest))
        }.also { results ->
            if (results is ResourceState.Success) {
                database.clearUser()
                database.saveUser(results.data)
            }
        })
    }.flowOn(Dispatchers.Main)

    override suspend fun getProfile(token: String, profileId: Long): Flow<ResourceState<Profile>> = flow {
        emit(ResourceState.Loading)
        emit(handleApiCall {
            profileMapper.mapToModel(galleryApi.getProfile(token, profileId))
        })
    }.flowOn(Dispatchers.Main)
}