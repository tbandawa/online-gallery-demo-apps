package me.tbandawa.android.online.gallery.data.domain.mappers

import me.tbandawa.android.online.gallery.data.domain.models.User
import me.tbandawa.android.online.gallery.data.remote.responses.UserResponse

class UserMapper(
    private val profilePhotoMapper: ProfilePhotoMapper
): ResponseMapper<UserResponse, User> {

    override fun mapToModel(entity: UserResponse): User {
        return User(
            entity.token,
            entity.id,
            entity.firstname,
            entity.lastname,
            entity.username,
            entity.email,
            entity.roles,
            profilePhotoMapper.mapToModel(entity.profilePhoto)
        )
    }
}