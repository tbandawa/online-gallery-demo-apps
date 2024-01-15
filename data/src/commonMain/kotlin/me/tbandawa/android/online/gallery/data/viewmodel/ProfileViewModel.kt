package me.tbandawa.android.online.gallery.data.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.tbandawa.android.online.gallery.data.domain.models.Profile
import me.tbandawa.android.online.gallery.data.domain.repo.GalleryRepository
import me.tbandawa.android.online.gallery.data.remote.state.ResourceState

class ProfileViewModel(
    private val galleryRepository: GalleryRepository
): BaseViewModel() {

    private val _profileResource = MutableStateFlow<ResourceState<Profile>>(ResourceState.Empty)
    val profileResource: StateFlow<ResourceState<Profile>> get() = _profileResource

    fun getProfile() {
        coroutineScope.launch {
            val user = galleryRepository.getUser()
            galleryRepository.getProfile(user!!.token, user.id).collect { results ->
                _profileResource.value = results
            }
        }
    }

    fun viewProfile(profileId: Long) {
        coroutineScope.launch {
            val user = galleryRepository.getUser()
            galleryRepository.getProfile(user!!.token, profileId).collect { results ->
                _profileResource.value = results
            }
        }
    }
}