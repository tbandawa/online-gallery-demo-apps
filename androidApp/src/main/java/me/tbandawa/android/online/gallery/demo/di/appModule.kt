package me.tbandawa.android.online.gallery.demo.di

import me.tbandawa.android.online.gallery.demo.ui.viewmodels.PagingGalleryViewModel
import org.koin.dsl.module

val pagingGalleryViewModel = module {
    single { PagingGalleryViewModel(get()) }
}