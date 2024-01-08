package me.tbandawa.android.online_gallery_demo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform