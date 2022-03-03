package me.tbandawa.android.gallerydemo.data.api

import me.tbandawa.android.gallerydemo.data.model.Gallery
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GalleryApi {

    @GET("api/gallery")
    suspend fun fetchGalleries() : Response<List<Gallery>>

    @GET("api/gallery/{id}")
    suspend fun fetchGallery(@Path("id") id: Long) : Response<Gallery>

}