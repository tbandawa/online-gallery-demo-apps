package me.tbandawa.android.online.gallery.data.domain.repo

import com.squareup.sqldelight.db.SqlDriver
import me.tbandawa.android.online.gallery.data.cache.Database
import me.tbandawa.android.online.gallery.data.remote.api.GalleryApi
import me.tbandawa.android.online.gallery.data.remote.requests.SignInRequest
import me.tbandawa.android.online.gallery.data.remote.requests.UserRequest

class GalleryRepositoryImpl(
    sqlDriver: SqlDriver,
    private val galleryApi: GalleryApi
): GalleryRepository {

    private val database = Database(sqlDriver)

    override suspend fun signInUser(signInRequest: SignInRequest) {
        TODO("Not yet implemented")
    }
}