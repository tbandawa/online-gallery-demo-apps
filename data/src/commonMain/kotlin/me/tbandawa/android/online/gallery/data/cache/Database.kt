package me.tbandawa.android.online.gallery.data.cache

import com.squareup.sqldelight.db.SqlDriver
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import me.tbandawa.android.online.gallery.data.domain.models.ProfilePhoto
import me.tbandawa.android.online.gallery.data.domain.models.User

internal class Database(sqlDriver: SqlDriver) {

    private val database = AppDatabase(sqlDriver)
    private val dbQuery = database.appDatabaseQueries

    internal fun saveUser(
        id: Int,
        token: String,
        firstname: String,
        lastname: String,
        username: String,
        email: String,
        thumbnailUrl: String,
        imageUrl: String
    ) {
        dbQuery.transaction {
            dbQuery.insertUser(id.toLong(), token, firstname, lastname, username, email, thumbnailUrl, imageUrl)
        }
    }

    internal fun getUser(id: Long): User? {
        val userData = dbQuery.getUser(id).executeAsOneOrNull()
        return userData?.let { mapUser(it) }
    }

    private fun mapUser(
        userData: UserData
    ): User {
        return User(
            userData.token,
            userData.id,
            userData.firstname,
            userData.lastname,
            userData.username,
            userData.email,
            arrayListOf(),
            ProfilePhoto(userData.thumbnailUrl, userData.imageUrl)
        )
    }
}