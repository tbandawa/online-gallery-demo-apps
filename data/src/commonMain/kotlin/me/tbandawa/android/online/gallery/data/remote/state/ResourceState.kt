package me.tbandawa.android.online.gallery.data.remote.state

import me.tbandawa.android.online.gallery.data.domain.models.Error as ErrorModel

sealed class ResourceState<out M> {
    data class Success<out M>(val data: M): ResourceState<M>()
    data class Error<out M>(val data: ErrorModel? = null) : ResourceState<M>()
    data object Loading : ResourceState<Nothing>()
    data object Empty : ResourceState<Nothing>()
}
