package me.tbandawa.android.online.gallery.data.cache

import com.squareup.sqldelight.db.SqlDriver
import me.tbandawa.android.online.gallery.data.domain.models.ProfilePhoto
import me.tbandawa.android.online.gallery.data.domain.models.User

internal class Database(sqlDriver: SqlDriver) {

    private val database = AppDatabase(sqlDriver)
    private val dbQuery = database.appDatabaseQueries

    internal fun saveUser(
        user: User
    ) {
        dbQuery.transaction {
            dbQuery.insertUser(
                user.id,
                user.token,
                user.firstname,
                user.lastname,
                user.username,
                user.email,
                user.profilePhoto.thumbnail ?: "",
                user.profilePhoto.image ?: ""
            )
        }
    }

    internal fun getUser(id: Long): User? {
        val userData = dbQuery.getUser(id).executeAsOneOrNull()
        return userData?.let { mapUser(it) }
    }

    internal fun clearUser() {
        dbQuery.transaction { dbQuery.removeUser() }
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