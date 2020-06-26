package com.mywallet.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mywallet.data.models.SettingsItem

@Dao
interface SettingsDao {

    @Query("SELECT * FROM SettingsItem")
    fun loadAllSettingsItems(): List<SettingsItem>

    @Query("SELECT * FROM SettingsItem WHERE id = :id")
    fun getSettingsItemById(id: Int): SettingsItem

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(settingsItem: SettingsItem)
}