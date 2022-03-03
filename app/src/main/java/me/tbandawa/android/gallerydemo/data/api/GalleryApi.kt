package me.tbandawa.android.gallerydemo.data.api

import me.tbandawa.android.gallerydemo.data.model.Gallery
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GalleryApi {

    @GET("gallery")
    suspend fun fetchGallery() : Response<List<Gallery>>

    @GET("/{id}/")
    suspend fun fetchResources(@Path("id") id: Long) : Response<Gallery>

}