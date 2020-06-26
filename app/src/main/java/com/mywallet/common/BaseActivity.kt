package com.mywallet.common

//import android.arch.lifecycle.Observer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import dagger.android.support.DaggerAppCompatActivity

/**
 *
 */
open class BaseActivity: AppCompatActivity() {
//open class BaseActivity: DaggerAppCompatActivity() {

    fun observeLoader(viewModel: BaseViewModel, loaderView: View) {
        viewModel.loader.observe(this, Observer {
            loaderView.visibility = if(it != null && it) View.VISIBLE else View.GONE
        })
    }


}