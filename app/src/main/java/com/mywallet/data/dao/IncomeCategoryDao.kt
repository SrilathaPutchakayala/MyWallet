package com.mywallet.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mywallet.data.models.IncomeCategory

@Dao
interface IncomeCategoryDao {

    @Query("SELECT * FROM IncomeCategory ORDER BY name ASC")
    fun loadAllIncomeCategories(): List<IncomeCategory>

    @Query("SELECT * FROM IncomeCategory WHERE id = :id")
    fun getIncomeCategoryById(id: Int): IncomeCategory

    @Query("SELECT * FROM IncomeCategory WHERE name = :name")
    fun getIncomeCategoryByName(name: String): IncomeCategory

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(incomeCategory: IncomeCategory):Long

    @Query("DELETE FROM IncomeCategory")
    fun deleteAll()

    @Query("DELETE FROM IncomeCategory WHERE id = :id")
    fun deleteIncomeCategoryById(id: Int)
}