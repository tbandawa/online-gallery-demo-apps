package me.tbandawa.android.gallerydemo.data.repository

import kotlinx.coroutines.flow.Flow
import me.tbandawa.android.gallerydemo.data.api.GalleryApi
import me.tbandawa.android.gallerydemo.data.model.Gallery
import me.tbandawa.android.gallerydemo.data.model.Resource
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(
    val galleryApi: GalleryApi
): GalleryRepository {

    override suspend fun fetchGallery(): Flow<Resource<List<Gallery>>> {
        TODO("Not yet implemented")
    }

}