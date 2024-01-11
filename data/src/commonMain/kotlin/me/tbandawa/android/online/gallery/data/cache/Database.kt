package me.tbandawa.android.online.gallery.data.cache

import com.squareup.sqldelight.db.SqlDriver
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal class Database(sqlDriver: SqlDriver) {

    private val database = AppDatabase(sqlDriver)
    private val dbQuery = database.appDatabaseQueries


}