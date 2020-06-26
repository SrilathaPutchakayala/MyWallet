package com.mywallet.viewmodel

import android.app.Application
//import android.arch.lifecycle.AndroidViewModel
//import android.databinding.ObservableField
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
//import com.applandeo.materialcalendarview.utils.DateUtils
import com.mywallet.MyApplication
import com.mywallet.common.BaseViewModel
import com.mywallet.data.dao.ExpenseCategoryDao
import com.mywallet.data.dao.UserDao
import com.mywallet.data.models.ExpenseCategory
import com.mywallet.data.models.User
import org.jetbrains.anko.doAsync
import java.util.*
import javax.inject.Inject

/**
 * Created by srilathaputchakayala on 18/5/18.
 */
//class MainViewModel(ctx: Application): AndroidViewModel(ctx) {
class MainViewModel
@Inject constructor(
        private var userDao: UserDao,private var expenseCategoryDao: ExpenseCategoryDao
): BaseViewModel(){

    val mCurrentMonthName = ObservableField<String>()
    val mNextMonthName = ObservableField<String>()
    val mPreviousMonthName = ObservableField<String>()

    var userListSize: Int = 0

    init {
        //getMonthAndYearDate(Calendar.getInstance())
        //doAsync {
            val sydneyAirport = User(1, "Sydney", 20)
            val tokyoAirport = User(2, "Tokyo", 25)

            userDao.insertOrUpdate(sydneyAirport)
            userDao.insertOrUpdate(tokyoAirport)
            userListSize = userDao.all.size

            expenseCategoryDao.insertOrUpdate(ExpenseCategory(1,"ATM","blue"))
            expenseCategoryDao.insertOrUpdate(ExpenseCategory(2,"General","blue"))
       // }
    }


    fun getNextMonth(){

    }

    fun getMonthAndYearDate(calendar:Calendar) {

        //mCurrentMonthName.set(DateUtils.getMonthAndYearDate(MyApplication.instance, calendar))
        println("mCurrentMonthName======${mCurrentMonthName.get()}")
    }

}