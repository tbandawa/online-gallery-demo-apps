package me.tbandawa.android.gallerydemo.data.model

sealed class NetworkResult<out M> {
    class Success<out M>(val data: M) : NetworkResult<M>()
    class Error(val data: ErrorResponse? = null) : NetworkResult<Nothing>()
    class Failure(val message: String) : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()
    object Empty : NetworkResult<Nothing>()
}
