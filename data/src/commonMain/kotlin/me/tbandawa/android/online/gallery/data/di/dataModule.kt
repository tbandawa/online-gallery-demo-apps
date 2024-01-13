package me.tbandawa.android.online.gallery.data.di

import me.tbandawa.android.online.gallery.data.domain.mappers.GalleriesMapper
import me.tbandawa.android.online.gallery.data.domain.mappers.GalleryMapper
import me.tbandawa.android.online.gallery.data.domain.mappers.ImageMapper
import me.tbandawa.android.online.gallery.data.domain.mappers.ProfilePhotoMapper
import me.tbandawa.android.online.gallery.data.domain.mappers.UserMapper
import me.tbandawa.android.online.gallery.data.domain.repo.GalleryRepository
import me.tbandawa.android.online.gallery.data.domain.repo.GalleryRepositoryImpl
import me.tbandawa.android.online.gallery.data.remote.api.GalleryApi
import me.tbandawa.android.online.gallery.data.viewmodel.UserViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

val imagesMapperModule = module {
    single { ProfilePhotoMapper() }
    single { ImageMapper() }
}

val userMapperModule = module {
    single { UserMapper(get()) }
}

val galleryMapperModule = module {
    single { GalleryMapper(get()) }
}

val galleriesMapperModule = module {
    single { GalleriesMapper(get()) }
}

val apiModule = module {
    single { GalleryApi() }
}

val repositoryModule = module {
    single { GalleryRepositoryImpl(get(), get(), get()) } bind GalleryRepository::class
}

val viewModelModule = module {
    single { UserViewModel(get()) }
}

fun initKoin(appDeclaration: KoinAppDeclaration) = startKoin {
    appDeclaration()
    modules(
        platformModule,
        imagesMapperModule,
        userMapperModule,
        galleryMapperModule,
        galleriesMapperModule,
        apiModule,
        repositoryModule,
        viewModelModule
    )
}

