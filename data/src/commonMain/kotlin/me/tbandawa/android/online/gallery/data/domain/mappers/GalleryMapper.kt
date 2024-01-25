package me.tbandawa.android.online.gallery.data.domain.mappers

import me.tbandawa.android.online.gallery.data.domain.models.Gallery
import me.tbandawa.android.online.gallery.data.remote.responses.GalleryResponse

class GalleryMapper(
    private val imageMapper: ImageMapper,
    private val profileInfoMapper: ProfileInfoMapper
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
            entity.userId,
            entity.user?.let { user ->
                profileInfoMapper.mapToModel(user)
            }
        )
    }
}