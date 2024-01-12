package me.tbandawa.android.online.gallery.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class ProfilePhotoResponse(
    val thumbnail: String?,
    val image: String?
)
