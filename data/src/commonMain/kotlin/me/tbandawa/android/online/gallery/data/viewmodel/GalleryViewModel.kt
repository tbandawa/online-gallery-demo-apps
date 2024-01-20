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
}