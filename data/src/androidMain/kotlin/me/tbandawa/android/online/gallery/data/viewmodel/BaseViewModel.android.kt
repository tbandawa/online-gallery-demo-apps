package me.tbandawa.android.online.gallery.data.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

actual open class BaseViewModel : ViewModel() {

    actual val coroutineScope = CoroutineScope(Job() + Dispatchers.Main)

    actual fun dispose() {
        coroutineScope.cancel()
        onCleared()
    }

    actual override fun onCleared() {
        super.onCleared()
    }
}