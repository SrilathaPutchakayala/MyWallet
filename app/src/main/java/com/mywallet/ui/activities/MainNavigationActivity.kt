package com.mywallet.ui.activities

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.mywallet.R
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject



class MainNavigationActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_navigation)
        //setupNavigation()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.mainNavigationFragment)
        //setupActionBarWithNavController(this, navController)

        //NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }

    override fun onSupportNavigateUp() =
            findNavController(R.id.mainNavigationFragment).navigateUp()

}