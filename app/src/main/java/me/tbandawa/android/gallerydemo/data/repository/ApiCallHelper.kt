package me.tbandawa.android.gallerydemo.data.repository

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import me.tbandawa.android.gallerydemo.data.model.ErrorResponse
import me.tbandawa.android.gallerydemo.data.model.NetworkResult
import okio.IOException
import retrofit2.Response

abstract class ApiCallHelper {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>
    ): NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Success(body)
                }
            }
            return NetworkResult.Error(convertErrorBody(response))
        } catch (e: Exception) {
            return NetworkResult.Failure(e.message ?: e.toString())
        }
    }

    private fun <T> convertErrorBody(response: Response<T>): ErrorResponse? {
        val parser = JsonParser()
        val mJson: JsonElement?
        return try {
            mJson = parser.parse(response.errorBody()?.string())
            val gson = Gson()
            gson.fromJson(mJson, ErrorResponse::class.java)
        } catch (ex: IOException) {
            null
        }
    }

}