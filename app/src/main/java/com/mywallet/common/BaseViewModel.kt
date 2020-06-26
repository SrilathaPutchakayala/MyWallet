package com.mywallet.common


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mywallet.data.models.ErrorData
import io.reactivex.disposables.CompositeDisposable

/**
 *
 */
open class BaseViewModel: ViewModel() {

    val loader: MutableLiveData<Boolean> = MutableLiveData()
    val error: MutableLiveData<ErrorData?> = MutableLiveData()
    var compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}