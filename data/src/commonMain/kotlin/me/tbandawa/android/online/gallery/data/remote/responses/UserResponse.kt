package me.tbandawa.android.online.gallery.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val token: String,
    val id: Long,
    val firstname: String,
    val lastname: String,
    val username: String,
    val email: String,
    val roles: List<String>,
    val profilePhoto: ProfilePhotoResponse
)
