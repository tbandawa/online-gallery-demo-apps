package me.tbandawa.android.online.gallery.data.remote.responses

data class Galleries(
    val count: Long,
    val perPage: Long,
    val currentPage: Long,
    val nextPage: Long,
    val galleries: List<Gallery>
)
