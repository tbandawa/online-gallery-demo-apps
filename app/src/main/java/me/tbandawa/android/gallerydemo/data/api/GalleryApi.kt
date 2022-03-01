package me.tbandawa.android.gallerydemo.data.api

import me.tbandawa.android.gallerydemo.data.model.Gallery
import retrofit2.Response
import retrofit2.http.GET

interface GalleryApi {

    @GET("gallery")
    suspend fun fetchGallery() : Response<Gallery>

}