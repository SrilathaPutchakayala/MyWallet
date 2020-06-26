package com.mywallet.data.models

/*import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey*/

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 */
@Entity(tableName = "User")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val age: Int
)

