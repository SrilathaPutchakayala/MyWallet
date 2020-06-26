package com.mywallet.data.dao

/*import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query*/
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mywallet.data.models.User

/**
 * Created by srilathaputchakayala on 16/5/18.
 */
@Dao
interface UserDao {

    @get:Query("SELECT * FROM User")
    val all: List<User>

    @Query("SELECT * FROM User WHERE id = :id")
    fun getUserById(id: Int): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(user: User)

    @Query("DELETE FROM User")
    fun deleteAll()

    @Query("DELETE FROM User WHERE id = :id")
    fun deleteUserById(id: Int)
}