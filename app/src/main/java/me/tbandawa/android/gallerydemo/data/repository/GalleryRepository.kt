package me.tbandawa.android.gallerydemo.data.repository

import kotlinx.coroutines.flow.Flow
import me.tbandawa.android.gallerydemo.data.model.Gallery
import me.tbandawa.android.gallerydemo.data.model.NetworkResult

interface GalleryRepository {

    suspend fun fetchGalleries(): Flow<NetworkResult<List<Gallery>>>

    suspend fun fetchGallery(id: Long): Flow<NetworkResult<Gallery>>

}