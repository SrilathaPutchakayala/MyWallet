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
import com.mywallet.data.models.IncomeCategory
import com.mywallet.databinding.FragmentIncomeCategoryListBinding
import com.mywallet.databinding.ItemIncomeCategoryBinding
import com.mywallet.ui.adapters.ModelBindingAdapter
import com.mywallet.ui.databinding.handlers.OnModelClickListener
import com.mywallet.viewmodel.IncomeCategoryViewModel
import kotlinx.android.synthetic.main.fragment_income_category_list.*
import kotlinx.android.synthetic.main.item_income_category.*
import javax.inject.Inject

class IncomeCategoryListFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var dataViewBinding: FragmentIncomeCategoryListBinding
    private lateinit var listAdapter: ModelBindingAdapter<IncomeCategory, ItemIncomeCategoryBinding>

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        MyApplication.instance.component.inject(this)
        dataViewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_income_category_list, container, false)
        dataViewBinding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(IncomeCategoryViewModel::class.java)

        return dataViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab_add_income_category.setOnClickListener {

            val action = IncomeCategoryListFragmentDirections.action_incomeCategoryListFragment_to_addIncomeCategory()
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
        val list = dataViewBinding.viewModel?.getAllIncomeCategories()!!.toMutableList()
        listAdapter.addAllItems(list)
    }

    private fun setListAdapter() {
        listAdapter = ModelBindingAdapter(mutableListOf(), R.layout.item_income_category, { binding, model ->
            binding.model = model
            binding.listener = object : OnModelClickListener<IncomeCategory> {
                override fun onClick(model: IncomeCategory) {
                    val bundleArgs = Bundle()
                    bundleArgs.putInt("income_category_id", model.id)
                    tvIncomeCategoryName.findNavController().navigate(R.id.addIncomeCategoryFragment, bundleArgs)
                }
            }
        })
        dataViewBinding.rvIncomeCategoryList.adapter = listAdapter
        dataViewBinding.rvIncomeCategoryList.layoutManager = LinearLayoutManager(context)
        dataViewBinding.rvIncomeCategoryList.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    override fun onResume() {
        super.onResume()
        //subscribeData()
    }

    companion object {
        fun newInstance(): ExpenseCategoryListFragment {
            return ExpenseCategoryListFragment()
        }
    }
}