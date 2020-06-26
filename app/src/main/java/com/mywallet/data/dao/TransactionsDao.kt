package com.mywallet.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mywallet.data.models.Transactions
import com.mywallet.data.models.enums.CategoryType

@Dao
interface TransactionsDao {

    @Query("SELECT * FROM Transactions WHERE categoryType = :categoryType ORDER BY transactionDateUTC ASC")
    fun loadAllTransactionsByCategoryType(categoryType: CategoryType): List<Transactions>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(transaction: Transactions):Long
}