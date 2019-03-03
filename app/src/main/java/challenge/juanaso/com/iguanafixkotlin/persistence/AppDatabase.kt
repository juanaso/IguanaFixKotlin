package challenge.juanaso.com.iguanafixkotlin.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import challenge.juanaso.com.iguanafixkotlin.model.User


@Database(entities = arrayOf(User::class), version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}