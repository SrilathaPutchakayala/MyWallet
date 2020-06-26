package com.mywallet.ui.activities

/*import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders*/
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.mywallet.R
import com.mywallet.common.BaseActivity
import com.mywallet.viewmodel.MainViewModel_Old
import timber.log.Timber
import javax.inject.Inject

/**
 *
 */

class MainActivity_Old : BaseActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var mainViewModel: MainViewModel_Old

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_old)

        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel_Old::class.java)

        //observeLoader(mainViewModel, loaderView)

        observeSearchResults()

        //init a query
        mainViewModel.searchRepos("android")

    }

    private fun observeSearchResults() {
        //println("Inside  ")



        mainViewModel.repoList.observe(this, Observer {
            Timber.i("repo count received  ${it?.size}")


            println("First Item =====${it?.get(0)?.fullName}")
            println("First Item =====${it?.get(0)?.description}")
        })
    }
}
