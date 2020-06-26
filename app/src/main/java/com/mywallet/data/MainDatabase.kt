/*
 * Copyright (C) 2017 | TS Applications Pty Ltd
 * All Rights Reserved.
 */

package com.mywallet.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.mywallet.R
import com.mywallet.data.converters.CategoryTypeConverter
import com.mywallet.data.converters.DateConverter
import com.mywallet.data.dao.*
import com.mywallet.data.models.*
import kotlinx.android.synthetic.main.fragment_expense_category_list.view.*

@Database(entities = [(User::class), (ExpenseCategory::class), (IncomeCategory::class), (Transactions::class), (SettingsItem::class)],
        version = MainDatabase.DB_VERSION, exportSchema = false)
@TypeConverters(DateConverter::class, CategoryTypeConverter::class)
abstract class MainDatabase : RoomDatabase() {

    abstract fun settingsDao(): SettingsDao
    abstract fun userDao(): UserDao
    abstract fun expenseCategoryDao(): ExpenseCategoryDao
    abstract fun incomeCategoryDao(): IncomeCategoryDao
    abstract fun transactionsDao(): TransactionsDao

    companion object {

        const val DB_NAME = "mywallet.db"
        const val DB_VERSION = 1

        /*@Volatile private var instance: MainDatabase? = null

        fun getInstance(context: Context): MainDatabase =
                instance ?: synchronized(this) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                        initialiseDatabase()
                    }
                }

        private fun buildDatabase(context: Context) =

                Room.databaseBuilder(DatabaseContext(context),
                        MainDatabase::class.java, DB_NAME).build()

        }*/


        /**
         * This is just for singleton pattern
         */
        private var INSTANCE: MainDatabase? = null

        fun getDatabase(context: Context): MainDatabase {
            if (INSTANCE == null) {
                synchronized(MainDatabase::class.java) {
                    if (INSTANCE == null) {
                        // Get MainDatabase database instance
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                MainDatabase::class.java, DB_NAME)
                                .allowMainThreadQueries()
                                .build()

                        initialiseDatabase(context)

                        /*
                        addCallback(object : Callback() {

                        })
                         */
                    }
                }
            }
            return INSTANCE!!
        }

        private fun initialiseDatabase(context: Context) {
            INSTANCE?.let {

                it.settingsDao().insertOrUpdate(SettingsItem(1, context.resources.getString(R.string.expense_category_title)))
                it.settingsDao().insertOrUpdate(SettingsItem(2, context.resources.getString(R.string.income_category_title)))
            }
        }
    }
}
