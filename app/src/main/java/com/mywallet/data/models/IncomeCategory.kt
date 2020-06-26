package com.mywallet.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

//import android.arch.persistence.room.Entity
//import android.arch.persistence.room.PrimaryKey

/**
 * Created by srilathaputchakayala on 17/5/18.
 */
@Entity(tableName = "IncomeCategory")
data class IncomeCategory(
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
        val name: String,
        val color: String
)
