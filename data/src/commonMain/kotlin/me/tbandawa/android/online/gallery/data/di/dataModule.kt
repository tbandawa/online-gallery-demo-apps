package me.tbandawa.android.online.gallery.data.di

import me.tbandawa.android.online.gallery.data.domain.mappers.ErrorMapper
import me.tbandawa.android.online.gallery.data.domain.mappers.GalleriesMapper
import me.tbandawa.android.online.gallery.data.domain.mappers.GalleryMapper
import me.tbandawa.android.online.gallery.data.domain.mappers.ImageMapper
import me.tbandawa.android.online.gallery.data.domain.mappers.ProfileInfoMapper
import me.tbandawa.android.online.gallery.data.domain.mappers.ProfileMapper
import me.tbandawa.android.online.gallery.data.domain.mappers.ProfilePhotoMapper
import me.tbandawa.android.online.gallery.data.domain.mappers.UserMapper
import me.tbandawa.android.online.gallery.data.domain.repo.GalleryRepository
import me.tbandawa.android.online.gallery.data.domain.repo.GalleryRepositoryImpl
import me.tbandawa.android.online.gallery.data.remote.api.GalleryApi
import me.tbandawa.android.online.gallery.data.viewmodel.SplashViewModel
import me.tbandawa.android.online.gallery.data.viewmodel.AuthViewModel
import me.tbandawa.android.online.gallery.data.viewmodel.GalleryViewModel
import me.tbandawa.android.online.gallery.data.viewmodel.ProfileViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

val errorMapperModule = module {
    single { ErrorMapper() }
}

val imagesMapperModule = module {
    single { ProfilePhotoMapper() }
    single { ImageMapper() }
}

val userMapperModule = module {
    single { UserMapper(get()) }
}

val galleryMapperModule = module {
    single { GalleryMapper(get(), get()) }
}

val galleriesMapperModule = module {
    single { GalleriesMapper(get()) }
}

val profileMapperModule = module {
    single { ProfileMapper(get(), get()) }
}

val profileInfoMapperModule = module {
    single { ProfileInfoMapper(get()) }
}

val apiModule = module {
    single { GalleryApi() }
}

val repositoryModule = module {
    single { GalleryRepositoryImpl(get(), get(), get(), get(), get(), get(), get(), get()) } bind GalleryRepository::class
}

val viewModelModule = module {
    single { SplashViewModel(get()) }
    single { AuthViewModel(get()) }
    single { ProfileViewModel(get()) }
    single { GalleryViewModel(get()) }
}

fun initKoin(appDeclaration: KoinAppDeclaration) = startKoin {
    appDeclaration()
    modules(
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
        viewModelModule
    )
}

