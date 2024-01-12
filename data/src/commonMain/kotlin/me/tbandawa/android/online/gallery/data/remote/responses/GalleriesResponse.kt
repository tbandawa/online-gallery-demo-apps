package me.tbandawa.android.online.gallery.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class GalleriesResponse(
    val count: Long,
    val perPage: Long,
    val currentPage: Long,
    val nextPage: Long,
    val galleries: List<GalleryResponse>
)
