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
import com.mywallet.data.models.ExpenseCategory
import com.mywallet.databinding.FragmentAddExpenseCategoryBinding
import com.mywallet.viewmodel.ExpenseCategoryViewModel
import kotlinx.android.synthetic.main.fragment_add_expense_category.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class AddExpenseCategoryFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var dataViewBinding: FragmentAddExpenseCategoryBinding

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        MyApplication.instance.component.inject(this)
        dataViewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_expense_category, container, false)
        dataViewBinding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(ExpenseCategoryViewModel::class.java)

        return dataViewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val expenseId = arguments?.getInt("expense_category_id")
        val expenseCategory = dataViewBinding.viewModel?.getExpenseCategoryById(expenseId!!)

        et_expense_category_name.setText(dataViewBinding.viewModel?.getExpenseCategoryById(expenseId!!)?.name)

        tv_save.setOnClickListener {

            if (!et_expense_category_name.text.toString().isNullOrEmpty()) {

                var expenseCategory = if (expenseCategory == null)
                    ExpenseCategory(name = et_expense_category_name.text.toString(), color = "")
                 else
                    ExpenseCategory(id = expenseId!!, name = et_expense_category_name.text.toString(), color = "")

                if (dataViewBinding.viewModel?.insertExpenseCategory(expenseCategory) == -1L) {
                    activity?.toast("Expense Category Name already exists!")
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