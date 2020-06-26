package com.mywallet.viewmodel

import android.annotation.SuppressLint
import androidx.databinding.ObservableField
import com.mywallet.common.BaseViewModel
import com.mywallet.data.dao.TransactionsDao
import com.mywallet.data.models.Transactions
import com.mywallet.data.models.enums.CategoryType
import java.text.SimpleDateFormat
import javax.inject.Inject
import java.util.*

@SuppressLint("SimpleDateFormat")
class TransactionViewModel @Inject constructor(
        private var transactionsDao: TransactionsDao
) : BaseViewModel() {

    val displayDate = ObservableField<String>()

    init {
        val date = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("EEEE, MMM dd yyyy")
        displayDate.set(dateFormat.format(date))
    }

    fun getTransactionsByCategoryType(categoryType: CategoryType){
        transactionsDao.loadAllTransactionsByCategoryType(categoryType)
    }

    fun saveOrUpdateTransaction(transaction: Transactions){
        transactionsDao.insertOrUpdate(transaction)
    }
}