package com.mywallet.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mywallet.data.models.ExpenseCategory


@Dao
interface ExpenseCategoryDao {

    @Query("SELECT * FROM ExpenseCategory ORDER BY name ASC")
    fun loadAllExpenseCategories(): List<ExpenseCategory>

    @Query("SELECT * FROM ExpenseCategory WHERE id = :id")
    fun getExpenseCategoryById(id: Int): ExpenseCategory

    @Query("SELECT * FROM ExpenseCategory WHERE name = :name")
    fun getExpenseCategoryByName(name: String): ExpenseCategory

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(expenseCategory: ExpenseCategory):Long

    @Query("DELETE FROM ExpenseCategory")
    fun deleteAll()

    @Query("DELETE FROM ExpenseCategory WHERE id = :id")
    fun deleteExpenseCategoryById(id: Int)
}