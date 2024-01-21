package me.tbandawa.android.online.gallery.data.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.tbandawa.android.online.gallery.data.domain.models.Profile
import me.tbandawa.android.online.gallery.data.domain.models.ProfilePhoto
import me.tbandawa.android.online.gallery.data.domain.repo.GalleryRepository
import me.tbandawa.android.online.gallery.data.remote.state.ResourceState

class ProfileViewModel(
    private val galleryRepository: GalleryRepository
): BaseViewModel() {

    private val _profileResource = MutableStateFlow<ResourceState<Profile>>(ResourceState.Empty)
    val profileResource: StateFlow<ResourceState<Profile>> get() = _profileResource

    private val _profilePhotoResource = MutableStateFlow<ResourceState<ProfilePhoto>>(ResourceState.Empty)
    val profilePhotoResource: StateFlow<ResourceState<ProfilePhoto>> get() = _profilePhotoResource

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
}