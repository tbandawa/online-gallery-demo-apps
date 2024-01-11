package me.tbandawa.android.online.gallery.data.viewmodel

import kotlinx.coroutines.CoroutineScope

expect open class BaseViewModel() {
    val coroutineScope: CoroutineScope
    fun dispose()
    protected fun onCleared()
}