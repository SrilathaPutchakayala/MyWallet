package com.mywallet.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SettingsItem")
data class SettingsItem(
        @PrimaryKey(autoGenerate = true) val id: Int=0,
        @ColumnInfo(name = "name")
        val itemName: String
)