package me.tbandawa.android.online.gallery.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val timeStamp: String,
    val status: Long,
    val error: String,
    val messages: List<String>
)
