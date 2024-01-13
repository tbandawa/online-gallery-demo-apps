package me.tbandawa.android.online.gallery.data.domain.mappers

import me.tbandawa.android.online.gallery.data.remote.responses.ErrorResponse
import me.tbandawa.android.online.gallery.data.domain.models.Error

class ErrorMapper: ResponseMapper<ErrorResponse, Error> {

    override fun mapToModel(entity: ErrorResponse): Error {
        return Error(
            entity.timeStamp,
            entity.status,
            entity.error,
            entity.messages
        )
    }
}