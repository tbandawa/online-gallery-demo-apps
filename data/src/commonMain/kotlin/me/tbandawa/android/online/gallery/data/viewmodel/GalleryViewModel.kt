package me.tbandawa.android.online.gallery.data.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.tbandawa.android.online.gallery.data.domain.models.Gallery
import me.tbandawa.android.online.gallery.data.domain.repo.GalleryRepository
import me.tbandawa.android.online.gallery.data.remote.state.ResourceState

class GalleryViewModel(
    private val galleryRepository: GalleryRepository
): BaseViewModel() {

    private val _galleryResource = MutableStateFlow<ResourceState<Gallery>>(ResourceState.Empty)
    val galleryResource: StateFlow<ResourceState<Gallery>> get() = _galleryResource

    private val _galleryDeleteResource = MutableStateFlow<ResourceState<Boolean>>(ResourceState.Empty)
    val galleryDeleteResource: StateFlow<ResourceState<Boolean>> get() = _galleryDeleteResource

    fun createGallery(
        title: String,
        description: String,
        images: Map<String, ByteArray>
    ) {
        coroutineScope.launch {
            galleryRepository.createGallery(title, description, images).collect { results ->
                _galleryResource.value = results
            }
        }
    }

    fun getGallery(
        galleryId: Long
    ) {
        coroutineScope.launch {
            galleryRepository.getGallery(galleryId).collect { results ->
                _galleryResource.value = results
            }
        }
    }

    fun deleteGallery(
        galleryId: Long
    ) {
        coroutineScope.launch {
            galleryRepository.deleteGallery(galleryId).collect { results ->
                _galleryDeleteResource.value = results
            }
        }
    }

    fun resetState() {
        _galleryResource.value = ResourceState.Empty
    }
}