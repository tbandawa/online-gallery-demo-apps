package me.tbandawa.android.online.gallery.data.domain.mappers

import me.tbandawa.android.online.gallery.data.domain.models.Galleries
import me.tbandawa.android.online.gallery.data.remote.responses.GalleriesResponse

class GalleriesMapper(
    private val galleryMapper: GalleryMapper
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