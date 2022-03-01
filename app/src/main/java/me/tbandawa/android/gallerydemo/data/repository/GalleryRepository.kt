package me.tbandawa.android.gallerydemo.data.repository

import kotlinx.coroutines.flow.Flow
import me.tbandawa.android.gallerydemo.data.model.Gallery
import me.tbandawa.android.gallerydemo.data.model.Resource

interface GalleryRepository {

    suspend fun fetchGallery(): Flow<Resource<List<Gallery>>>

}