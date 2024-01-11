package me.tbandawa.android.online.gallery.data

import me.tbandawa.android.online.gallery.data.Platform

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()