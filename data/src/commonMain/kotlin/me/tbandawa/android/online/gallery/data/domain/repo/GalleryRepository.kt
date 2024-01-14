package me.tbandawa.android.online.gallery.data.domain.repo

import kotlinx.coroutines.flow.Flow
import me.tbandawa.android.online.gallery.data.domain.models.User
import me.tbandawa.android.online.gallery.data.remote.requests.SignInRequest
import me.tbandawa.android.online.gallery.data.remote.requests.UserRequest
import me.tbandawa.android.online.gallery.data.remote.state.ResourceState

interface GalleryRepository {
    fun getUser(): User?
    suspend fun signInUser(signInRequest: SignInRequest): Flow<ResourceState<User>>
    suspend fun signUpUser(userRequest: UserRequest): Flow<ResourceState<User>>
}