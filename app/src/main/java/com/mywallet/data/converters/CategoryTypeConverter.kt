package com.mywallet.data.converters

//import android.arch.persistence.room.TypeConverter


import androidx.room.TypeConverter
import com.mywallet.data.models.enums.CategoryType

/**
 * Created by srilathaputchakayala on 29/5/18.
 */
class CategoryTypeConverter {

    @TypeConverter
    fun stringToEnum(value: String?): CategoryType? {
        return when (value) {
            null -> null
            else -> try { CategoryType.valueOf(value) } catch (e: IllegalArgumentException) { null }
        }
    }

    @TypeConverter
    fun enumToString(type: CategoryType?): String? {
        return when (type) {
            null -> null
            else -> type.toString()
        }
    }
}