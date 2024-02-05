package me.tbandawa.android.online.gallery.data.domain.repo

import com.squareup.sqldelight.db.SqlDriver
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.datetime.Clock
import me.tbandawa.android.online.gallery.data.cache.Database
import me.tbandawa.android.online.gallery.data.domain.mappers.ErrorMapper
import me.tbandawa.android.online.gallery.data.domain.mappers.GalleriesMapper
import me.tbandawa.android.online.gallery.data.domain.mappers.GalleryMapper
import me.tbandawa.android.online.gallery.data.domain.mappers.ProfileMapper
import me.tbandawa.android.online.gallery.data.domain.mappers.ProfilePhotoMapper
import me.tbandawa.android.online.gallery.data.domain.mappers.UserMapper
import me.tbandawa.android.online.gallery.data.domain.models.Galleries
import me.tbandawa.android.online.gallery.data.domain.models.Gallery
import me.tbandawa.android.online.gallery.data.domain.models.Profile
import me.tbandawa.android.online.gallery.data.domain.models.ProfilePhoto
import me.tbandawa.android.online.gallery.data.domain.models.User
import me.tbandawa.android.online.gallery.data.remote.api.BaseApiCall
import me.tbandawa.android.online.gallery.data.remote.api.GalleryApi
import me.tbandawa.android.online.gallery.data.remote.requests.SignInRequest
import me.tbandawa.android.online.gallery.data.remote.requests.UserRequest
import me.tbandawa.android.online.gallery.data.remote.responses.ErrorResponse
import me.tbandawa.android.online.gallery.data.remote.state.ResourceState

class GalleryRepositoryImpl(
    sqlDriver: SqlDriver,
    private val galleryApi: GalleryApi,
    private val userMapper: UserMapper,
    private val profileMapper: ProfileMapper,
    private val galleryMapper: GalleryMapper,
    private val galleriesMapper: GalleriesMapper,
    private val profilePhotoMapper: ProfilePhotoMapper,
    private val errorMapper: ErrorMapper
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
    }.flowOn(Dispatchers.Default)

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
    }.flowOn(Dispatchers.Default)

    override suspend fun getProfile(profileId: Long): Flow<ResourceState<Profile>> = flow {
        val user = getUser()
        emit(ResourceState.Empty)
        emit(ResourceState.Loading)
        emit(handleApiCall {
            profileMapper.mapToModel(galleryApi.getProfile(user!!.token, profileId))
        })
    }.flowOn(Dispatchers.Default)

    override suspend fun createGallery(
        title: String,
        description: String,
        images: Map<String, ByteArray>
    ): Flow<ResourceState<Gallery>> = flow {
        val user = getUser()
        emit(ResourceState.Empty)
        emit(ResourceState.Loading)
        emit(handleApiCall {
            galleryMapper.mapToModel(galleryApi.createGallery(user!!.token, title, description, images))
        })
    }.flowOn(Dispatchers.Default)

    override suspend fun getGalleries(page: Int): ResourceState<Galleries> {
        return try {
            val user = getUser()
            val response = galleriesMapper.mapToModel(galleryApi.getGalleries(user!!.token, page))
            ResourceState.Success(response)
        } catch (exception: ResponseException) {
            ResourceState.Error(
                errorMapper.mapToModel(exception.response.body<ErrorResponse>())
            )
        } catch (e: IOException) {
            ResourceState.Error(
                errorMapper.mapToModel(ErrorResponse(Clock.System.now().toString(),500, "Unknown Error", listOf(e.message.toString())))
            )
        } catch (e: Exception) {
            ResourceState.Error(
                errorMapper.mapToModel(ErrorResponse(Clock.System.now().toString(),500, "Unknown Error", listOf(e.message.toString())))
            )
        }
    }

    override suspend fun getGallery(galleryId: Long): Flow<ResourceState<Gallery>> = flow {
        val user = getUser()
        emit(ResourceState.Empty)
        emit(ResourceState.Loading)
        emit(handleApiCall {
            galleryMapper.mapToModel(galleryApi.getGallery(user!!.token, galleryId))
        })
    }.flowOn(Dispatchers.Default)

    override suspend fun searchGallery(query: String): Flow<ResourceState<List<Gallery>>> = flow {
        val user = getUser()
        emit(ResourceState.Empty)
        emit(ResourceState.Loading)
        emit(handleApiCall {
            galleryApi.searchGallery(user!!.token, query).map {
                galleryMapper.mapToModel(it)
            }
        })
    }.flowOn(Dispatchers.Default)

    override suspend fun deleteGallery(galleryId: Long): Flow<ResourceState<Boolean>> = flow {
        val user = getUser()
        emit(ResourceState.Empty)
        emit(ResourceState.Loading)
        emit(handleApiCall {
            galleryApi.deleteGallery(user!!.token, galleryId)
        })
    }.flowOn(Dispatchers.Default)

    override suspend fun uploadProfilePicture(
        photoTitle: String,
        photoBytes: ByteArray
    ): Flow<ResourceState<ProfilePhoto>> = flow {
        val user = getUser()
        emit(ResourceState.Empty)
        emit(ResourceState.Loading)
        emit(handleApiCall {
            profilePhotoMapper.mapToModel(galleryApi.uploadProfilePicture(user!!.token, photoTitle, photoBytes))
        }.also { results ->
            if (results is ResourceState.Success) {
                database.updatePhoto(
                    user!!.id, results.data.thumbnail!!,
                    results.data.image!!
                )
            }
        })
    }.flowOn(Dispatchers.Default)

    override suspend fun editUser(userRequest: UserRequest): Flow<ResourceState<User>> = flow {
        val user = getUser()
        emit(ResourceState.Loading)
        emit(handleApiCall {
            userMapper.mapToModel(galleryApi.editUser(user!!.token, userRequest))
        }.also { results ->
            if (results is ResourceState.Success) {
                database.clearUser()
                database.saveUser(results.data)
            }
        })
    }.flowOn(Dispatchers.Default)

    override suspend fun signOutUser(): Flow<ResourceState<String>> = flow {
        val user = getUser()
        emit(ResourceState.Loading)
        emit(handleApiCall {
            galleryApi.signOutUser(user!!.token).token
        }.also { results ->
            if (results is ResourceState.Success) {
                database.clearUser()
            }
        })
    }.flowOn(Dispatchers.Default)
}