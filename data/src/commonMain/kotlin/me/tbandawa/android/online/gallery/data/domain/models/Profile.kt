package me.tbandawa.android.online.gallery.data.domain.models

data class Profile(
    val id: Long,
    val firstname: String,
    val lastname: String,
    val username: String,
    val email: String,
    val gallery: List<Gallery>,
    val profilePhoto: ProfilePhoto
)
