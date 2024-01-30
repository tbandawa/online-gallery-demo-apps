package me.tbandawa.android.online.gallery.data.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.delete
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.utils.EmptyContent.headers
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.append
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.utils.io.core.buildPacket
import io.ktor.utils.io.core.writeFully
import kotlinx.serialization.json.Json
import me.tbandawa.android.online.gallery.data.remote.requests.SignInRequest
import me.tbandawa.android.online.gallery.data.remote.requests.UserRequest
import me.tbandawa.android.online.gallery.data.remote.responses.GalleriesResponse
import me.tbandawa.android.online.gallery.data.remote.responses.GalleryResponse
import me.tbandawa.android.online.gallery.data.remote.responses.ProfilePhotoResponse
import me.tbandawa.android.online.gallery.data.remote.responses.ProfileResponse
import me.tbandawa.android.online.gallery.data.remote.responses.UserResponse

class GalleryApi {

    companion object {
        const val BASE_URL = "http://192.168.0.77:8080/spring-image-upload/api"
    }

    private val httpClient = HttpClient {
        expectSuccess = true
        install(HttpTimeout) {
            requestTimeoutMillis = 15000L
            connectTimeoutMillis = 15000L
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.BODY
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    suspend fun signInUser(signInRequest: SignInRequest): UserResponse {
        return httpClient.post {
            url("$BASE_URL/auth/signin")
            contentType(ContentType.Application.Json)
            setBody(signInRequest)
        }.body<UserResponse>()
    }

    suspend fun signUpUser(userRequest: UserRequest): UserResponse {
        return httpClient.post {
            url("$BASE_URL/auth/signup")
            contentType(ContentType.Application.Json)
            setBody(userRequest)
        }.body<UserResponse>()
    }

    suspend fun getProfile(token: String, userId: Long): ProfileResponse {
        return httpClient.get {
            url("$BASE_URL/user/$userId")
            headers {
                append("Authorization", "Bearer $token")
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    suspend fun uploadProfilePicture(token: String, photoTitle: String, photoBytes: ByteArray): ProfilePhotoResponse {
        return httpClient.post("$BASE_URL/profile") {
            headers {
                append("Authorization", "Bearer $token")
            }
            contentType(ContentType.MultiPart.FormData)
            setBody(MultiPartFormDataContent(
                formData {
                    append("profile_photo", photoBytes, Headers.build {
                        append(HttpHeaders.ContentType, "image/png")
                        append(HttpHeaders.ContentDisposition, "filename=\"$photoTitle\"")
                    })
                }
            ))
        }.body()
    }

    suspend fun createGallery(token: String, title: String, description: String, images: Map<String, ByteArray>): GalleryResponse {
        return httpClient.post("$BASE_URL/gallery") {
            headers {
                append("Authorization", "Bearer $token")
            }
            contentType(ContentType.MultiPart.FormData)
            setBody(MultiPartFormDataContent(
                formData {
                    append("title", title)
                    append("description", description)
                    images.forEach { (k, v) ->
                        append("gallery_images", v, Headers.build {
                            append(HttpHeaders.ContentType, "image/png")
                            append(HttpHeaders.ContentDisposition, "filename=\"$k\"")
                        })
                    }
                }
            ))
        }.body()
    }

    suspend fun getGalleries(token: String, page: Int): GalleriesResponse {
        return httpClient.get {
            url("$BASE_URL/galleries/$page")
            headers {
                append("Authorization", "Bearer $token")
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    suspend fun getGallery(token: String, galleryId: Long): GalleryResponse {
        return httpClient.get {
            url("$BASE_URL/gallery/$galleryId")
            headers {
                append("Authorization", "Bearer $token")
            }
            contentType(ContentType.Application.Json)
        }.body()
    }

    suspend fun deleteGallery(token: String, galleryId: Long): Boolean {
        httpClient.delete {
            url("$BASE_URL/gallery/$galleryId")
            headers {
                append("Authorization", "Bearer $token")
            }
            contentType(ContentType.Application.Json)
        }
        return true
    }

    suspend fun editUser(token: String, userRequest: UserRequest): UserResponse {
        return httpClient.put {
            url("$BASE_URL/user")
            headers {
                append("Authorization", "Bearer $token")
            }
            contentType(ContentType.Application.Json)
            setBody(userRequest)
        }.body<UserResponse>()
    }
}