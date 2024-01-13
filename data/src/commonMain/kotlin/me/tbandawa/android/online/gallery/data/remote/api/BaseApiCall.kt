package me.tbandawa.android.online.gallery.data.remote.api

import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.utils.io.errors.*
import kotlinx.datetime.Clock
import me.tbandawa.android.online.gallery.data.domain.mappers.ErrorMapper
import me.tbandawa.android.online.gallery.data.remote.responses.ErrorResponse
import me.tbandawa.android.online.gallery.data.remote.state.ResourceState

abstract class BaseApiCall(private val errorMapper: ErrorMapper) {

    suspend fun <T> handleApiCall(
        apiCall: suspend () -> T
    ): ResourceState<T> {
        return try {
            val response = apiCall()
            ResourceState.Success(response)
        } catch (exception: ResponseException) {
            ResourceState.Error(
                errorMapper.mapToModel(exception.response.body<ErrorResponse>())
            )
        } catch (e: IOException) {
            ResourceState.Error(
                errorMapper.mapToModel(ErrorResponse(Clock.System.now().toString(),500, "Unknown Error", listOf(e.message.toString())))
            )
        } catch (e: Exception) {
            ResourceState.Error(
                errorMapper.mapToModel(ErrorResponse(Clock.System.now().toString(),500, "Unknown Error", listOf(e.message.toString())))
            )
        }
    }
}