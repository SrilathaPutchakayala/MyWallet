package com.mywallet.injection

import android.app.Application
import android.content.Context
import com.mywallet.data.DatabaseContext
import com.mywallet.data.MainDatabase
import com.mywallet.data.dao.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [ViewModelModule::class])
class AppModule(private val app: Application) {

    @Provides
    @Singleton
    fun provideApp(): Application = app

    @Provides
    @Singleton
    fun provideContext(): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideDb(app: Application): MainDatabase {
        return MainDatabase.getDatabase(DatabaseContext(app))
    }

    @Provides
    @Singleton
    fun provideUserDao(db: MainDatabase): UserDao {
        return db.userDao()
    }

    @Provides
    @Singleton
    fun provideExpenseCategoryDao(db: MainDatabase): ExpenseCategoryDao {
        return db.expenseCategoryDao()
    }

    @Provides
    @Singleton
    fun provideIncomeCategoryDao(db: MainDatabase): IncomeCategoryDao {
        return db.incomeCategoryDao()
    }

    @Provides
    @Singleton
    fun provideTransactionsDao(db: MainDatabase): TransactionsDao {
        return db.transactionsDao()
    }


    @Provides
    @Singleton
    fun provideSettingsDao(db: MainDatabase): SettingsDao {
        return db.settingsDao()
    }
}