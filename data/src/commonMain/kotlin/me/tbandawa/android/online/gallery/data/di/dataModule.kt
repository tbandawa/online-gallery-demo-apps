package me.tbandawa.android.online.gallery.data.di

import me.tbandawa.android.online.gallery.data.domain.mappers.GalleriesMapper
import me.tbandawa.android.online.gallery.data.domain.mappers.GalleryMapper
import me.tbandawa.android.online.gallery.data.domain.mappers.ImageMapper
import me.tbandawa.android.online.gallery.data.domain.mappers.ProfilePhotoMapper
import me.tbandawa.android.online.gallery.data.domain.mappers.ResponseMapper
import me.tbandawa.android.online.gallery.data.domain.mappers.UserMapper
import me.tbandawa.android.online.gallery.data.domain.models.Galleries
import me.tbandawa.android.online.gallery.data.domain.models.Gallery
import me.tbandawa.android.online.gallery.data.domain.models.Image
import me.tbandawa.android.online.gallery.data.domain.models.ProfilePhoto
import me.tbandawa.android.online.gallery.data.domain.models.User
import me.tbandawa.android.online.gallery.data.domain.repo.GalleryRepository
import me.tbandawa.android.online.gallery.data.domain.repo.GalleryRepositoryImpl
import me.tbandawa.android.online.gallery.data.remote.api.GalleryApi
import me.tbandawa.android.online.gallery.data.remote.responses.GalleriesResponse
import me.tbandawa.android.online.gallery.data.remote.responses.GalleryResponse
import me.tbandawa.android.online.gallery.data.remote.responses.ImageResponse
import me.tbandawa.android.online.gallery.data.remote.responses.ProfilePhotoResponse
import me.tbandawa.android.online.gallery.data.remote.responses.UserResponse
import me.tbandawa.android.online.gallery.data.viewmodel.UserViewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

val imagesMapperModule = module {
    single<ResponseMapper<ProfilePhotoResponse, ProfilePhoto>>(named("profilePhotoMapper")) {
        ProfilePhotoMapper()
    }
    single<ResponseMapper<ImageResponse, Image>>(named("imageMapper")) {
        ImageMapper()
    }
}

val userMapperModule = module {
    single<ResponseMapper<UserResponse, User>>(named("userMapper")) {
        UserMapper(get(qualifier = named("profilePhotoMapper")))
    }
}

val galleryMapperModule = module {
    single<ResponseMapper<GalleryResponse, Gallery>>(named("galleryMapper")) {
        GalleryMapper(get(qualifier = named("imageMapper")))
    }
}

val galleriesMapperModule = module {
    single<ResponseMapper<GalleriesResponse, Galleries>>(named("galleriesMapper")) {
        GalleriesMapper(get(qualifier = named("galleryMapper")))
    }
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

