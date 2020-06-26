package com.mywallet.ui.fragments

import android.content.Context
import android.os.Bundle
//import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mywallet.R


/**
 * Created by srilathaputchakayala on 29/5/18.
 */
class IncomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater!!.inflate(R.layout.fragment_income, container, false)


        return rootView
    }

    /*override fun onAttach(context: Context?) {
        super.onAttach(context)
        //Log.v("Context====", "=====CONTEXT=====$context")
    }*/

    companion object {
        fun newInstance(): IncomeFragment {
            val fragment = IncomeFragment()
            return fragment
        }
    }
}