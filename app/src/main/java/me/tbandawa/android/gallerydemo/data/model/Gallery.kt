package me.tbandawa.android.gallerydemo.data.model

data class Gallery(
    val id: Int,
    val title: String,
    val description: String,
    val images: List<String>,
    val created: String
)
