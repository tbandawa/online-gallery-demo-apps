package me.tbandawa.android.online.gallery.data.domain.repo

import kotlinx.coroutines.flow.Flow
import me.tbandawa.android.online.gallery.data.domain.models.Galleries
import me.tbandawa.android.online.gallery.data.domain.models.Gallery
import me.tbandawa.android.online.gallery.data.domain.models.Profile
import me.tbandawa.android.online.gallery.data.domain.models.ProfilePhoto
import me.tbandawa.android.online.gallery.data.domain.models.User
import me.tbandawa.android.online.gallery.data.remote.requests.SignInRequest
import me.tbandawa.android.online.gallery.data.remote.requests.UserRequest
import me.tbandawa.android.online.gallery.data.remote.state.ResourceState

interface GalleryRepository {
    fun getUser(): User?
    suspend fun signInUser(signInRequest: SignInRequest): Flow<ResourceState<User>>
    suspend fun signUpUser(userRequest: UserRequest): Flow<ResourceState<User>>
    suspend fun getProfile(profileId: Long): Flow<ResourceState<Profile>>
    suspend fun createGallery(title: String, description: String, images: Map<String, ByteArray>): Flow<ResourceState<Gallery>>
    suspend fun getGalleries(page: Int): ResourceState<Galleries>
    suspend fun uploadProfilePicture(photoTitle: String, photoBytes: ByteArray): Flow<ResourceState<ProfilePhoto>>
    suspend fun editUser(userRequest: UserRequest): Flow<ResourceState<User>>
}