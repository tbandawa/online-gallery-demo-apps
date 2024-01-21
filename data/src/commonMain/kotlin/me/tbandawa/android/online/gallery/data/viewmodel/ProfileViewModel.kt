package me.tbandawa.android.online.gallery.data.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.tbandawa.android.online.gallery.data.domain.models.Profile
import me.tbandawa.android.online.gallery.data.domain.models.ProfilePhoto
import me.tbandawa.android.online.gallery.data.domain.models.User
import me.tbandawa.android.online.gallery.data.domain.repo.GalleryRepository
import me.tbandawa.android.online.gallery.data.remote.requests.UserRequest
import me.tbandawa.android.online.gallery.data.remote.state.ResourceState

class ProfileViewModel(
    private val galleryRepository: GalleryRepository
): BaseViewModel() {

    private val _profileResource = MutableStateFlow<ResourceState<Profile>>(ResourceState.Empty)
    val profileResource: StateFlow<ResourceState<Profile>> get() = _profileResource

    private val _profilePhotoResource = MutableStateFlow<ResourceState<ProfilePhoto>>(ResourceState.Empty)
    val profilePhotoResource: StateFlow<ResourceState<ProfilePhoto>> get() = _profilePhotoResource

    private val _userResource = MutableStateFlow<ResourceState<User>>(ResourceState.Empty)
    val userResource: StateFlow<ResourceState<User>> get() = _userResource

    init {
        coroutineScope.launch {
            _userResource.value = ResourceState.Success(galleryRepository.getUser()!!)
        }
    }

    fun getUserData(): User? = galleryRepository.getUser()

    fun getProfile() {
        coroutineScope.launch {
            val user = galleryRepository.getUser()
            galleryRepository.getProfile(user!!.id).collect { results ->
                _profileResource.value = results
            }
        }
    }

    fun viewProfile(profileId: Long) {
        coroutineScope.launch {
            galleryRepository.getProfile(profileId).collect { results ->
                _profileResource.value = results
            }
        }
    }

    fun uploadProfilePicture(
        photoTitle: String,
        photoBytes: ByteArray
    ) {
        coroutineScope.launch {
            galleryRepository.uploadProfilePicture(photoTitle, photoBytes).collect { results ->
                _profilePhotoResource.value = results
            }
        }
    }

    fun editUser(
        firstname: String,
        lastname: String,
        username: String,
        email: String,
        password: String
    ) {
        coroutineScope.launch {
            galleryRepository.editUser(
                UserRequest(firstname, lastname, username, email, password, arrayListOf("user"))
            ).collect { results ->
                _userResource.value = results
            }
        }
    }
}