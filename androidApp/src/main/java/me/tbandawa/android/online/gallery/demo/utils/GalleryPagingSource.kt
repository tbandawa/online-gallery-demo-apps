package me.tbandawa.android.online.gallery.demo.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import me.tbandawa.android.online.gallery.data.domain.models.Gallery
import me.tbandawa.android.online.gallery.data.domain.repo.GalleryRepository
import me.tbandawa.android.online.gallery.data.remote.state.ResourceState

class GalleryPagingSource(
    private val galleryRepository: GalleryRepository
): PagingSource<Int, Gallery>() {

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gallery> {
        val position = params.key ?: STARTING_PAGE_INDEX
        return when (val result = galleryRepository.getGalleries(position)) {
            is ResourceState.Error -> LoadResult.Error(Exception(result.data?.error))
            is ResourceState.Empty -> LoadResult.Error(Exception())
            is ResourceState.Loading -> LoadResult.Error(Exception())
            is ResourceState.Success ->
                LoadResult.Page(
                    data = result.data.galleries,
                    prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                    nextKey = if (result.data.galleries.isEmpty()) null else position + 1
                )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Gallery>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}