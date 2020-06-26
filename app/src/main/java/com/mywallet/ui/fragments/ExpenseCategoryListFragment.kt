package com.mywallet.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mywallet.MyApplication
import com.mywallet.R
import com.mywallet.data.models.ExpenseCategory
import com.mywallet.databinding.FragmentExpenseCategoryListBinding
import com.mywallet.databinding.ItemExpenseCategoryBinding
import com.mywallet.ui.adapters.ModelBindingAdapter
import com.mywallet.ui.databinding.handlers.OnModelClickListener
import com.mywallet.viewmodel.ExpenseCategoryViewModel
import kotlinx.android.synthetic.main.fragment_expense_category_list.*
import kotlinx.android.synthetic.main.item_expense_category.*
import javax.inject.Inject

class ExpenseCategoryListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var dataViewBinding: FragmentExpenseCategoryListBinding
    private lateinit var listAdapter: ModelBindingAdapter<ExpenseCategory, ItemExpenseCategoryBinding>

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        MyApplication.instance.component.inject(this)
        dataViewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_expense_category_list, container, false)
        dataViewBinding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(ExpenseCategoryViewModel::class.java)

        return dataViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab_add_expense_category.setOnClickListener {

            val action = ExpenseCategoryListFragmentDirections.action_expenseCategoryListFragment_to_addExpenseCategory()
            val navController = it.findNavController()
            navController.navigate(action)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setListAdapter()
        subscribeData()
    }

    private fun subscribeData() {
        val list = dataViewBinding.viewModel?.getAllExpenseCategories()!!.toMutableList()
        listAdapter.addAllItems(list)
    }

    private fun setListAdapter() {
        listAdapter = ModelBindingAdapter(mutableListOf(), R.layout.item_expense_category, { binding, model ->
            binding.model = model
            binding.listener = object : OnModelClickListener<ExpenseCategory> {
                override fun onClick(model: ExpenseCategory) {
                    val bundleArgs = Bundle()
                    bundleArgs.putInt("expense_category_id", model.id)
                    tvExpenseCategoryName.findNavController().navigate(R.id.addExpenseCategoryFragment, bundleArgs)
                }
            }
        })
        dataViewBinding.rvExpenseCategoryList.adapter = listAdapter
        dataViewBinding.rvExpenseCategoryList.layoutManager = LinearLayoutManager(context)
        dataViewBinding.rvExpenseCategoryList.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    companion object {
        fun newInstance(): ExpenseCategoryListFragment {
            return ExpenseCategoryListFragment()
        }
    }
}