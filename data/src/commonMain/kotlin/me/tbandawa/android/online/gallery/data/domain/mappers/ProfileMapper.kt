package me.tbandawa.android.online.gallery.data.domain.mappers

import me.tbandawa.android.online.gallery.data.domain.models.Profile
import me.tbandawa.android.online.gallery.data.remote.responses.ProfileResponse

class ProfileMapper(
    private val profilePhotoMapper: ProfilePhotoMapper,
    private val galleryMapper: GalleryMapper
): ResponseMapper<ProfileResponse, Profile> {
    override fun mapToModel(entity: ProfileResponse): Profile {
        return Profile(
            entity.id,
            entity.firstname,
            entity.lastname,
            entity.username,
            entity.email,
            entity.gallery.map { galleryResponse -> galleryMapper.mapToModel(galleryResponse) },
            profilePhotoMapper.mapToModel(entity.profilePhoto)
        )
    }
}