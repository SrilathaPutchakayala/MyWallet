package com.mywallet.injection

import android.app.Application
import android.content.Context
import com.mywallet.ui.fragments.*
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class)])
interface AppComponent {

    fun app(): Application

    fun context(): Context

    fun inject(mainFragment: MainFragment)
    fun inject(expenseCategoryListFragment: ExpenseCategoryListFragment)
    fun inject(incomeCategoryListFragment: IncomeCategoryListFragment)
    fun inject(addExpenseCategoryFragment: AddExpenseCategoryFragment)
    fun inject(addIncomeCategoryFragment: AddIncomeCategoryFragment)
    fun inject(settingsFragment: SettingsFragment)
    fun inject(transactionsFragment: TransactionsFragment)
    fun inject(addIncomeFragment: AddIncomeFragment)

}