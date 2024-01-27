package me.tbandawa.android.online.gallery.demo.ui.viewmodels

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import me.tbandawa.android.online.gallery.data.domain.models.Gallery
import me.tbandawa.android.online.gallery.data.domain.repo.GalleryRepository
import me.tbandawa.android.online.gallery.data.viewmodel.BaseViewModel
import me.tbandawa.android.online.gallery.demo.utils.GalleryPagingSource

class PagingGalleryViewModel(
    private val galleryRepository: GalleryRepository
): BaseViewModel() {

    val newsData: Flow<PagingData<Gallery>> =
        Pager(config = PagingConfig(pageSize = 10, prefetchDistance = 2),
            pagingSourceFactory = { GalleryPagingSource(galleryRepository) }
        ).flow.cachedIn(coroutineScope)

}