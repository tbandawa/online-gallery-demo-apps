package me.tbandawa.android.gallerydemo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.tbandawa.android.gallerydemo.data.api.GalleryApi
import me.tbandawa.android.gallerydemo.data.api.RetrofitHelper
import me.tbandawa.android.gallerydemo.data.repository.GalleryRepository
import me.tbandawa.android.gallerydemo.data.repository.GalleryRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideGalleryApi(): GalleryApi = RetrofitHelper.getInstance().create(GalleryApi::class.java)

    @Provides
    @Singleton
    fun provideGalleryRepository(galleryApi: GalleryApi): GalleryRepository = GalleryRepositoryImpl(galleryApi)

}