package me.tbandawa.android.online.gallery.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class ProfileInfoResponse(
    val firstname: String,
    val lastname: String,
    val profilePhoto: ProfilePhotoResponse
)
