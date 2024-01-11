package me.tbandawa.android.online.gallery.data.remote.state

import me.tbandawa.android.online.gallery.data.remote.responses.ErrorResponse

sealed class ResourceState<out M> {
    data class Success<out M>(val data: M): ResourceState<M>()
    data class Error<out M>(val data: ErrorResponse? = null) : ResourceState<M>()
    object Loading : ResourceState<Nothing>()
    object Empty : ResourceState<Nothing>()
}
