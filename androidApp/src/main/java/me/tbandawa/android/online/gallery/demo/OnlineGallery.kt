package me.tbandawa.android.online.gallery.demo

import android.app.Application
import me.tbandawa.android.online.gallery.BuildConfig
import me.tbandawa.android.online.gallery.data.di.apiModule
import me.tbandawa.android.online.gallery.data.di.errorMapperModule
import me.tbandawa.android.online.gallery.data.di.galleriesMapperModule
import me.tbandawa.android.online.gallery.data.di.galleryMapperModule
import me.tbandawa.android.online.gallery.data.di.imagesMapperModule
import me.tbandawa.android.online.gallery.data.di.platformModule
import me.tbandawa.android.online.gallery.data.di.profileInfoMapperModule
import me.tbandawa.android.online.gallery.data.di.profileMapperModule
import me.tbandawa.android.online.gallery.data.di.repositoryModule
import me.tbandawa.android.online.gallery.data.di.userMapperModule
import me.tbandawa.android.online.gallery.data.di.viewModelModule
import me.tbandawa.android.online.gallery.demo.di.pagingGalleryViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class OnlineGallery: Application() {

    override fun onCreate() {
        super.onCreate()

        // start koin
        startKoin {
            androidContext(this@OnlineGallery)
            modules(
                listOf(
                    errorMapperModule,
                    platformModule,
                    imagesMapperModule,
                    userMapperModule,
                    galleryMapperModule,
                    galleriesMapperModule,
                    profileMapperModule,
                    profileInfoMapperModule,
                    apiModule,
                    repositoryModule,
                    viewModelModule,
                    pagingGalleryViewModel
                )
            )
        }

        // enable Timber in debug mode
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }
}