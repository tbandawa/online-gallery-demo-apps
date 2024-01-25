package me.tbandawa.android.online.gallery.data.domain.models

data class Gallery(
    val id: Long,
    val title: String,
    val description: String,
    val images: List<Image>,
    val created: String,
    val userId: Long,
    val profile: ProfileInfo?
)
