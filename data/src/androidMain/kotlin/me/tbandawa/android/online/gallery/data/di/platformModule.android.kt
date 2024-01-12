package me.tbandawa.android.online.gallery.data.di

import org.koin.core.module.Module
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import me.tbandawa.android.online.gallery.data.cache.AppDatabase
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            schema = AppDatabase.Schema,
            context = get(),
            name = "gallery.db"
        )
    }
}