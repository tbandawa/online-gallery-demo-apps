package me.tbandawa.android.online.gallery.data.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import me.tbandawa.android.online.gallery.data.remote.requests.SignInRequest
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
            url("$BASE_URL/signin")
            contentType(ContentType.Application.Json)
            setBody(signInRequest)
        }.body<UserResponse>()
    }
}