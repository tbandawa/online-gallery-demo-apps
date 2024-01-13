package me.tbandawa.android.online.gallery.data.domain.repo

import com.squareup.sqldelight.db.SqlDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import me.tbandawa.android.online.gallery.data.cache.Database
import me.tbandawa.android.online.gallery.data.domain.mappers.UserMapper
import me.tbandawa.android.online.gallery.data.domain.models.User
import me.tbandawa.android.online.gallery.data.remote.api.BaseApiCall
import me.tbandawa.android.online.gallery.data.remote.api.GalleryApi
import me.tbandawa.android.online.gallery.data.remote.requests.SignInRequest
import me.tbandawa.android.online.gallery.data.remote.state.ResourceState

class GalleryRepositoryImpl(
    sqlDriver: SqlDriver,
    private val galleryApi: GalleryApi,
    private val userMapper: UserMapper
): GalleryRepository, BaseApiCall() {

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
}