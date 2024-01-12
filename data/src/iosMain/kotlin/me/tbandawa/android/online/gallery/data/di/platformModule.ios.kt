package me.tbandawa.android.online.gallery.data.di

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import me.tbandawa.android.online.gallery.data.cache.AppDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<SqlDriver> {
        NativeSqliteDriver(AppDatabase.Schema, "gallery.db")
    }
}