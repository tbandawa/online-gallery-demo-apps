package me.tbandawa.android.online.gallery.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class GalleryResponse(
    val id: Long,
    val title: String,
    val description: String,
    val images: List<ImageResponse>,
    val created: String,
    val userId: Long,
    val user: ProfileInfoResponse?
)
