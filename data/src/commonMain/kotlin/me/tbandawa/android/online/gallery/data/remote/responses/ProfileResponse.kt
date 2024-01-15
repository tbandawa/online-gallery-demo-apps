package me.tbandawa.android.online.gallery.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(
    val id: Long,
    val firstname: String,
    val lastname: String,
    val username: String,
    val email: String,
    val gallery: List<GalleryResponse>,
    val profilePhoto: ProfilePhotoResponse
)
