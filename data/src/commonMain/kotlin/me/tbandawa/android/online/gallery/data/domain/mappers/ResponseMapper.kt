package me.tbandawa.android.online.gallery.data.domain.mappers

interface ResponseMapper<I, O>  {
    fun mapToModel(entity: I): O

}