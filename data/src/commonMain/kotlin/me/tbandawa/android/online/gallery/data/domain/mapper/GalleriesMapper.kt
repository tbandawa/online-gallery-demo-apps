package me.tbandawa.android.online.gallery.data.domain.mapper

import me.tbandawa.android.online.gallery.data.domain.models.Galleries
import me.tbandawa.android.online.gallery.data.domain.models.Gallery
import me.tbandawa.android.online.gallery.data.remote.responses.GalleriesResponse
import me.tbandawa.android.online.gallery.data.remote.responses.GalleryResponse

class GalleriesMapper(
    private val galleryMapper: ResponseMapper<GalleryResponse, Gallery>
): ResponseMapper<GalleriesResponse, Galleries> {

    override fun mapToModel(entity: GalleriesResponse): Galleries {
        return Galleries(
            entity.count,
            entity.perPage,
            entity.currentPage,
            entity.nextPage,
            entity.galleries.map {
                galleryResponse -> galleryMapper.mapToModel(galleryResponse)
            }
        )
    }
}