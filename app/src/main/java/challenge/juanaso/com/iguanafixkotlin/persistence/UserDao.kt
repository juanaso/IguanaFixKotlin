package challenge.juanaso.com.iguanafixkotlin.persistence

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import challenge.juanaso.com.iguanafixkotlin.model.User

@Dao
interface UserDao {
    @get:Query("SELECT * FROM user")
    val all: List<User>

    @Insert
    fun insertAll(user: List<User>)

    @Query("DELETE FROM user")
    fun deleteAllUsers()
}