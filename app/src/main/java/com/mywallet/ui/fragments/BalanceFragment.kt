package com.mywallet.ui.fragments

import android.content.Context
//import android.support.v4.app.Fragment
import android.util.Log
import androidx.fragment.app.Fragment


/**
 * Created by srilathaputchakayala on 29/5/18.
 */
class BalanceFragment : Fragment() {


    /*override fun onAttach(context: Context?) {
        super.onAttach(context)
        //Log.v("Context====", "=====CONTEXT====Inside Balance Fragment===" + context)
    }*/

    companion object {
        fun newInstance(): BalanceFragment {
            return BalanceFragment()
        }
    }
}