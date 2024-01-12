package me.tbandawa.android.online.gallery.data.domain.mapper

interface ResponseMapper<I, O>  {
    fun mapToModel(entity: I): O

}