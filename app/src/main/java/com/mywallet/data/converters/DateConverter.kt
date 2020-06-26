package com.mywallet.data.converters

//import android.arch.persistence.room.TypeConverter

import androidx.room.TypeConverter
import java.util.*

/**
 * Created by srilathaputchakayala on 16/5/18.
 */
class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return when (value) {
            null -> null
            else -> Date(value)
        }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return when (date) {
            null -> null
            else -> date.time
        }
    }
}