package com.mywallet.viewmodel

import androidx.databinding.ObservableField
import com.mywallet.common.BaseViewModel
import com.mywallet.data.dao.IncomeCategoryDao
import com.mywallet.data.models.IncomeCategory
import javax.inject.Inject

class IncomeCategoryViewModel@Inject constructor(
        private var incomeCategoryDao: IncomeCategoryDao
) : BaseViewModel() {

    val selectedExpenseCategory = ObservableField<IncomeCategory>()

    fun insertIncomeCategory(incomeCategory: IncomeCategory): Long {
        return if (!getIncomeCategoryByName(incomeCategory.name))
            incomeCategoryDao.insertOrUpdate(incomeCategory)
        else -1
    }


    fun getAllIncomeCategories(): List<IncomeCategory> {
        return incomeCategoryDao.loadAllIncomeCategories()
    }

    fun getIncomeCategoryById(incomeCategoryId: Int): IncomeCategory? {
        return incomeCategoryDao.getIncomeCategoryById(incomeCategoryId)
    }

    private fun getIncomeCategoryByName(incomeCategoryName: String): Boolean {
        return incomeCategoryDao.getIncomeCategoryByName(incomeCategoryName) != null
    }

}