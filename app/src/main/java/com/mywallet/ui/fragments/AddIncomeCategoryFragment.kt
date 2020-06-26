package com.mywallet.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.mywallet.MyApplication
import com.mywallet.R
import com.mywallet.data.models.IncomeCategory
import com.mywallet.databinding.FragmentAddIncomeCategoryBinding
import com.mywallet.viewmodel.IncomeCategoryViewModel
import kotlinx.android.synthetic.main.fragment_add_income_category.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class AddIncomeCategoryFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var dataViewBinding: FragmentAddIncomeCategoryBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        MyApplication.instance.component.inject(this)
        dataViewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_income_category, container, false)
        dataViewBinding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(IncomeCategoryViewModel::class.java)

        return dataViewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val incomeCategoryId = arguments?.getInt("income_category_id")
        val incomeCategory = dataViewBinding.viewModel?.getIncomeCategoryById(incomeCategoryId!!)

        et_income_category_name.setText(dataViewBinding.viewModel?.getIncomeCategoryById(incomeCategoryId!!)?.name)

        tv_save.setOnClickListener {

            if (!et_income_category_name.text.toString().isNullOrEmpty()) {

                val incomeCategory = if (incomeCategory == null)
                    IncomeCategory(name = et_income_category_name.text.toString(), color = "")
                else
                    IncomeCategory(id = incomeCategoryId!!, name = et_income_category_name.text.toString(), color = "")

                if (dataViewBinding.viewModel?.insertIncomeCategory(incomeCategory) == -1L) {
                    activity?.toast("Income Category Name already exists!")
                }
                fragmentManager?.popBackStack()

            } else {
                Toast.makeText(activity?.applicationContext, resources.getString(R.string.prompt_empty_category_name), Toast.LENGTH_SHORT).show()
            }
        }

        tv_close.setOnClickListener {
            fragmentManager?.popBackStack()
        }
    }

    companion object {

        fun newInstance(): AddExpenseCategoryFragment {
            return AddExpenseCategoryFragment()
        }
    }
}