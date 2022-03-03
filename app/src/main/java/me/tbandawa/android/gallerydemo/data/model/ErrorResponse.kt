package me.tbandawa.android.gallerydemo.data.model

data class ErrorResponse(
    val status: Int,
    val error: String,
    val messages: List<String>,
    val timeStamp: String
)
