package me.tbandawa.android.online.gallery.data.domain.mappers

import me.tbandawa.android.online.gallery.data.domain.models.Image
import me.tbandawa.android.online.gallery.data.remote.responses.ImageResponse

class ImageMapper: ResponseMapper<ImageResponse, Image> {

    override fun mapToModel(entity: ImageResponse): Image {
        return Image(
            entity.thumbnail,
            entity.image
        )
    }
}