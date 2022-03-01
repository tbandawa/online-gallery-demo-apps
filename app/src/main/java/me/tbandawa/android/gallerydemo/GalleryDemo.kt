package me.tbandawa.android.gallerydemo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class GalleryDemo: Application() {

    override fun onCreate() {
        super.onCreate()

        // enable Timber in debug mode
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

    }

}