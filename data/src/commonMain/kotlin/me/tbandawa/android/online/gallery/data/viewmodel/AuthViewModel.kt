package me.tbandawa.android.online.gallery.data.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import me.tbandawa.android.online.gallery.data.domain.models.User
import me.tbandawa.android.online.gallery.data.domain.repo.GalleryRepository
import me.tbandawa.android.online.gallery.data.remote.requests.SignInRequest
import me.tbandawa.android.online.gallery.data.remote.requests.UserRequest
import me.tbandawa.android.online.gallery.data.remote.state.ResourceState

class AuthViewModel(
    private val galleryRepository: GalleryRepository
): BaseViewModel() {

    private val _userResource = MutableStateFlow<ResourceState<User>>(ResourceState.Empty)
    val userResource: StateFlow<ResourceState<User>> get() = _userResource

    private val _logOutResource = MutableStateFlow<ResourceState<String>>(ResourceState.Empty)
    val logOutResource: StateFlow<ResourceState<String>> get() = _logOutResource

    fun signInUser(username: String, password: String) {
        coroutineScope.launch {
            galleryRepository.signInUser(SignInRequest(username, password)).collect { results ->
                _userResource.value = results
            }
        }
    }

    fun signUpUser(
        firstname: String,
        lastname: String,
        username: String,
        email: String,
        password: String
    ) {
        coroutineScope.launch {
            galleryRepository.signUpUser(
                UserRequest(firstname, lastname, username, email, password, arrayListOf("user"))
            ).collect { results ->
                _userResource.value = results
            }
        }
    }

    fun signOutUser() {
        coroutineScope.launch {
            galleryRepository.signOutUser().collect { results ->
                _logOutResource.value = results
            }
        }
    }

    fun resetState() {
        _userResource.value = ResourceState.Empty
        _logOutResource.value = ResourceState.Empty
    }

    @Suppress("unused")
    fun observeUserResource(provideUserState: ((ResourceState<User>) -> Unit)) {
        _userResource.onEach {
            provideUserState.invoke(it)
        }.launchIn(coroutineScope)
    }
}