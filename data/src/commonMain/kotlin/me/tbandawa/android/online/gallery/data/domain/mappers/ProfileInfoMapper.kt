package me.tbandawa.android.online.gallery.data.domain.mappers

import me.tbandawa.android.online.gallery.data.domain.models.ProfileInfo
import me.tbandawa.android.online.gallery.data.remote.responses.ProfileInfoResponse

class ProfileInfoMapper(
    private val profilePhotoMapper: ProfilePhotoMapper
): ResponseMapper<ProfileInfoResponse, ProfileInfo> {

    override fun mapToModel(entity: ProfileInfoResponse): ProfileInfo {
        return ProfileInfo(
            entity.firstname,
            entity.lastname,
            profilePhotoMapper.mapToModel(entity.profilePhoto)
        )
    }
}