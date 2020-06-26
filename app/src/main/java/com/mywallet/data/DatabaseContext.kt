package com.mywallet.data

import android.content.Context
import android.content.ContextWrapper
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import java.io.File

/**
 * Created by srilathaputchakayala on 16/5/18.
 */
class DatabaseContext(ctx: Context) : ContextWrapper(ctx) {

    override fun getDatabasePath(name: String): File {
        return File(getExternalFilesDir(null), name)
    }

    override fun getApplicationContext(): Context {
        return this
    }

    override fun openOrCreateDatabase(name: String, mode: Int, factory: SQLiteDatabase.CursorFactory?): SQLiteDatabase {
        return super.openOrCreateDatabase(getDatabasePath(name).absolutePath, mode, factory)
    }

    override fun openOrCreateDatabase(name: String, mode: Int, factory: SQLiteDatabase.CursorFactory?, errorHandler: DatabaseErrorHandler?): SQLiteDatabase {
        return super.openOrCreateDatabase(getDatabasePath(name).absolutePath, mode, factory, errorHandler)
    }

    override fun deleteDatabase(name: String): Boolean {
        return super.deleteDatabase(getDatabasePath(name).absolutePath)
    }
}