package me.tbandawa.android.online.gallery.demo

import android.app.Application
import me.tbandawa.android.online.gallery.BuildConfig
import me.tbandawa.android.online.gallery.data.di.initKoin
import org.koin.android.ext.koin.androidContext
import timber.log.Timber

class OnlineGallery: Application() {

    override fun onCreate() {
        super.onCreate()

        // start koin
        initKoin {
            androidContext(this@OnlineGallery)
        }

        // enable Timber in debug mode
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}