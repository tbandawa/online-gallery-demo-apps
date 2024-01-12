package me.tbandawa.android.online.gallery.data.domain.mappers

import me.tbandawa.android.online.gallery.data.domain.models.ProfilePhoto
import me.tbandawa.android.online.gallery.data.remote.responses.ProfilePhotoResponse

class ProfilePhotoMapper: ResponseMapper<ProfilePhotoResponse, ProfilePhoto> {

    override fun mapToModel(entity: ProfilePhotoResponse): ProfilePhoto {
        return ProfilePhoto(
            entity.thumbnail,
            entity.image
        )
    }
}