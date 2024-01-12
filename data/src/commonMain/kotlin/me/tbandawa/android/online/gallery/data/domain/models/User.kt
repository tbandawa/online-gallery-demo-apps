package me.tbandawa.android.online.gallery.data.domain.models

data class User(
    val token: String,
    val id: Long,
    val firstname: String,
    val lastname: String,
    val username: String,
    val email: String,
    val roles: List<String>,
    val profilePhoto: ProfilePhoto
)
