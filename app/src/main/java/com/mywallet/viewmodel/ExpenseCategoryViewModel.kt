package com.mywallet.viewmodel

import androidx.databinding.ObservableField
import com.mywallet.common.BaseViewModel
import com.mywallet.data.dao.ExpenseCategoryDao
import com.mywallet.data.models.ExpenseCategory
import javax.inject.Inject

class ExpenseCategoryViewModel @Inject constructor(
        private var expenseCategoryDao: ExpenseCategoryDao
) : BaseViewModel() {

    val selectedExpenseCategory = ObservableField<ExpenseCategory>()

    fun insertExpenseCategory(expenseCategory: ExpenseCategory): Long {
        return if (!getExpenseCategoryByName(expenseCategory.name))
            expenseCategoryDao.insertOrUpdate(expenseCategory)
        else -1
    }


    fun getAllExpenseCategories(): List<ExpenseCategory> {
        return expenseCategoryDao.loadAllExpenseCategories()
    }

    fun getExpenseCategoryById(expenseCategoryId: Int): ExpenseCategory? {
        return expenseCategoryDao.getExpenseCategoryById(expenseCategoryId)
    }

    private fun getExpenseCategoryByName(expenseCategoryName: String): Boolean {
        return expenseCategoryDao.getExpenseCategoryByName(expenseCategoryName) != null
    }

}