package com.mywallet.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by srilathaputchakayala on 17/5/18.
 */
@Entity(tableName = "ExpenseCategory")
data class ExpenseCategory(
        @PrimaryKey(autoGenerate = true) val id: Int=0,
        @ColumnInfo(name = "name")
        val name: String,
        val color: String
)


