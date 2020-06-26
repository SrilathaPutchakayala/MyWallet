package com.mywallet

import android.app.Application
import android.os.Environment
import com.mywallet.injection.AppModule
import com.mywallet.injection.DaggerAppComponent
import timber.log.Timber
import java.io.File


/**
 * Application class that initiates the dependency graph
 *
 */
open class MyApplication : Application(){

    val component by lazy { DaggerAppComponent.builder().appModule(AppModule(this)).build() }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        instance = this
        createDirectoryStructure()
        //Room.databaseBuilder(applicationContext, MainDatabase::class.java, "main").build()
        /*
        doAsync {
            val db = MainDatabase.getInstance(instance)
            println("User List Size=====${db.userDao().all}=====")
            println("Expense Category List Size=====${db.expenseCategoryDao().all}=====")
        }*/
    }

    companion object {
        lateinit var instance: MyApplication

        val EXTERNAL_FILES_DIR: String = File(Environment.getExternalStorageDirectory(),
                "Android/data/com.mywallet/files").canonicalPath

        fun createDirectoryStructure(): Boolean {
            return listOf(EXTERNAL_FILES_DIR).fold(true) { partialResult, dirPath  ->
                val file = File(dirPath)
                partialResult && (file.exists() || file.mkdirs())
            }
        }
    }
}