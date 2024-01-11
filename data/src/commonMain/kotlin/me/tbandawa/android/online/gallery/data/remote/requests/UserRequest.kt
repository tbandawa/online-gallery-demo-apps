package me.tbandawa.android.online.gallery.data.remote.requests

data class UserRequest(
    val firstname: String,
    val lastname: String,
    val username: String,
    val email: String,
    val password: String,
    val role: List<String>
)
