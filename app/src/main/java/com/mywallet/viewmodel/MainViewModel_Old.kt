package com.mywallet.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.MutableLiveData
//import android.arch.lifecycle.MutableLiveData
import com.mywallet.common.BaseViewModel
import com.mywallet.common.network.ApiObserver
import com.mywallet.data.MainDatabase
import com.mywallet.data.models.ErrorData
import com.mywallet.data.models.RepoDTO
import com.mywallet.data.models.RepoSearchResponse
import com.mywallet.data.repository.DataRepository
//import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 *
 */
class MainViewModel_Old @Inject constructor(private val dataRepository: DataRepository) : BaseViewModel() {

    private var msg: String = "test"
    var repoList: MutableLiveData<List<RepoDTO>> = MutableLiveData()

    @SuppressLint("StaticFieldLeak")
    //private val _ctx = ctx

    fun getMessage(): String {
        msg = dataRepository.getMessage()
        return msg
    }

    fun searchRepos(query: String) {

        //displayLoader(true)



        /*dataRepository.searchRepositories(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : ApiObserver<RepoSearchResponse>(compositeDisposable) {
                    override fun onError(e: ErrorData) {
                        //displayLoader(false)
                        error.value = e
                    }

                    override fun onSuccess(data: RepoSearchResponse) {
                        //displayLoader(false)
                        repoList.value = data.repoList
                    }
                })*/
    }
}