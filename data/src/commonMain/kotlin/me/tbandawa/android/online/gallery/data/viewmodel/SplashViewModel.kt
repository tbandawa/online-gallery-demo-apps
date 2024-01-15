package me.tbandawa.android.online.gallery.data.viewmodel

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.tbandawa.android.online.gallery.data.domain.models.AuthState
import me.tbandawa.android.online.gallery.data.domain.repo.GalleryRepository
import me.tbandawa.android.online.gallery.data.remote.state.ResourceState

class SplashViewModel(
    private val galleryRepository: GalleryRepository
): BaseViewModel() {

    private val _authState= MutableStateFlow(AuthState(0, true))
    val authState = _authState.asStateFlow()

    fun getProfile() {
        val user = galleryRepository.getUser()
        if (user != null) {
            coroutineScope.launch {
                delay(3000)
                galleryRepository.getProfile(user.token, user.id).collect { results ->
                    when(results) {
                        is ResourceState.Loading -> {}
                        is ResourceState.Success -> {
                            _authState.value = AuthState(1, false)
                        }
                        is ResourceState.Error -> {
                            _authState.value = AuthState(2, false)
                        }
                        is ResourceState.Empty -> {}
                    }
                }
            }
        } else {
            coroutineScope.launch {
                delay(3000)
                _authState.value = AuthState(2, false)
            }
        }
    }
}