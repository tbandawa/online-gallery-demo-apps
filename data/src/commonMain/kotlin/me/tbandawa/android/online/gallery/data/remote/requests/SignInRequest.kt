package me.tbandawa.android.online.gallery.data.remote.requests

import kotlinx.serialization.Serializable

@Serializable
data class SignInRequest(
    val username: String,
    val password: String
)
