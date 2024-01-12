package me.tbandawa.android.online.gallery.data.domain.models

data class Error(
    val timeStamp: String,
    val status: Long,
    val error: String,
    val messages: List<String>
)
