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
import com.mywallet.data.models.SettingsItem
import com.mywallet.databinding.FragmentSettingsBinding
import com.mywallet.databinding.ItemSettingBinding
import com.mywallet.ui.adapters.ModelBindingAdapter
import com.mywallet.ui.databinding.handlers.OnModelClickListener
import com.mywallet.viewmodel.SettingsViewModel
import kotlinx.android.synthetic.main.item_setting.*
import javax.inject.Inject

class SettingsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var dataViewBinding: FragmentSettingsBinding
    private lateinit var listAdapter: ModelBindingAdapter<SettingsItem, ItemSettingBinding>

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        MyApplication.instance.component.inject(this)
        dataViewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_settings, container, false)
        dataViewBinding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(SettingsViewModel::class.java)

        return dataViewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setListAdapter()
        subscribeData()
    }

    private fun subscribeData() {
        val list = dataViewBinding.viewModel?.getAllSettingsItems()!!.toMutableList()
        listAdapter.addAllItems(list)
    }

    private fun setListAdapter() {
        listAdapter = ModelBindingAdapter(mutableListOf(), R.layout.item_setting, { binding, model ->
            binding.model = model
            binding.listener = object : OnModelClickListener<SettingsItem> {
                override fun onClick(model: SettingsItem) {

                    when(model.itemName) {
                        resources.getString(R.string.expense_category_title) ->{
                            val action = SettingsFragmentDirections.action_settingsFragment_to_expenseCategoryListFragment()

                            val navController = tvSettingItemName.findNavController()
                            navController.navigate(action)
                        }
                        resources.getString(R.string.income_category_title) ->{
                            val action = SettingsFragmentDirections.action_settingsFragment_to_incomeCategoryListFragment()

                            val navController = tvSettingItemName.findNavController()
                            navController.navigate(action)
                        }

                    }

                    //tvExpenseCategoryName.findNavController().navigate(R.id.addExpenseCategoryFragment, bundleArgs)
                }
            }
        })
        dataViewBinding.rvSettingsItemsList.adapter = listAdapter
        dataViewBinding.rvSettingsItemsList.layoutManager = LinearLayoutManager(context)
        dataViewBinding.rvSettingsItemsList.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
    }

    companion object {

        fun newInstance(): SettingsFragment {

            return SettingsFragment()
        }
    }
}