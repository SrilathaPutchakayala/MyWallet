package com.mywallet.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.mywallet.MyApplication
import com.mywallet.R
import com.mywallet.databinding.FragmentAddIncomeBinding
import com.mywallet.viewmodel.TransactionViewModel
import kotlinx.android.synthetic.main.fragment_add_income.*
import javax.inject.Inject

class AddIncomeFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var dataViewBinding: FragmentAddIncomeBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        MyApplication.instance.component.inject(this)
        dataViewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_income, container, false)
        dataViewBinding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(TransactionViewModel::class.java)

        return dataViewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        tv_close.setOnClickListener {
            fragmentManager?.popBackStack()
        }

    }

}