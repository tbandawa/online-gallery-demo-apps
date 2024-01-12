package me.tbandawa.android.online.gallery.data.domain.repo

import me.tbandawa.android.online.gallery.data.remote.requests.SignInRequest

interface GalleryRepository {
    suspend fun signInUser(signInRequest: SignInRequest)
}