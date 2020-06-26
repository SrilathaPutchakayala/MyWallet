package com.mywallet.data.models

/*import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters*/

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

import com.mywallet.data.converters.CategoryTypeConverter
import com.mywallet.data.models.enums.CategoryType
import java.util.*

/**
 * Created by srilathaputchakayala on 29/5/18.
 */
@Entity(tableName = "Transactions")
@TypeConverters(CategoryTypeConverter::class)
data class Transactions (
        @PrimaryKey(autoGenerate = true) val id: Int,
        val categoryName: String,
        val categoryType: CategoryType,
        val price: Double,
        val notes: String,
        val transactionDateUTC: Date? = null
)