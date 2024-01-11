package me.tbandawa.android.online.gallery.data

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform