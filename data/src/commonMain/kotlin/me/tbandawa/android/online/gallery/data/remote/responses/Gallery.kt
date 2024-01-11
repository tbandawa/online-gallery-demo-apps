package me.tbandawa.android.online.gallery.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class Gallery(
    val id: Long,
    val title: String,
    val description: String,
    val images: List<Image>,
    val created: String,
    val userId: Long
)
