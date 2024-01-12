package me.tbandawa.android.online.gallery.data.domain.mapper

import me.tbandawa.android.online.gallery.data.domain.models.Gallery
import me.tbandawa.android.online.gallery.data.domain.models.Image
import me.tbandawa.android.online.gallery.data.remote.responses.GalleryResponse
import me.tbandawa.android.online.gallery.data.remote.responses.ImageResponse

class GalleryMapper(
    private val imageMapper: ResponseMapper<ImageResponse, Image>
): ResponseMapper<GalleryResponse, Gallery> {

    override fun mapToModel(entity: GalleryResponse): Gallery {
        return Gallery(
            entity.id,
            entity.title,
            entity.description,
            entity.images.map {
                imageResponse -> imageMapper.mapToModel(imageResponse)
            },
            entity.created,
            entity.userId
        )
    }
}