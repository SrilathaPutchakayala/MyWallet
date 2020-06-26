package com.mywallet.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.mywallet.R
import com.mywallet.databinding.FragmentMainBinding
import com.mywallet.ui.adapters.PageAdapter
import com.mywallet.viewmodel.MainViewModel
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateClickedListener
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import com.mywallet.MyApplication


class MainFragment : Fragment(), OnDateClickedListener {

    private val title by lazy(LazyThreadSafetyMode.NONE)
    {
        arguments?.getString("title") ?: ""
    }

    private lateinit var dataViewBinding: FragmentMainBinding
    private var mSectionsPagerAdapter: PageAdapter? = null
    @Inject
    internal lateinit var viewModelFactory: ViewModelProvider.Factory


    var selectedDates = mutableListOf(Date(118, 4, 25), Date(118, 4, 20))

    val todaysDate = Calendar.getInstance()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        MyApplication.instance.component.inject(this)

        dataViewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        dataViewBinding.viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)//ViewModelProviders.of(this).get(MainViewModel::class.java)

        return dataViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val today = CalendarDay.today()

        /*calendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .commit()*/

        activity?.toast("=====today=======$today======Size is=====${dataViewBinding.viewModel?.userListSize}")

        calendarView.currentDate = today
        calendarView.selectedDate = today

        setMonthTitle()
        setNextDate()
        setPrevDate()

        tv_previous.setOnClickListener {
            calendarView.goToPrevious()
            todaysDate.add(Calendar.MONTH, -1)
            setMonthTitle()
            setNextDate()
            setPrevDate()
        }

        tv_next.setOnClickListener {
            calendarView.goToNext()
            todaysDate.add(Calendar.MONTH, 1)
            setMonthTitle()
            setNextDate()
            setPrevDate()
        }

        tv_income.setOnClickListener {
            activity?.toast("Income Tab clicked===")

            val action = MainFragmentDirections.action_mainFragment_to_addIncomeFragment()

            val navController = view.findNavController()
            navController.navigate(action)

        }

        tv_settings.setOnClickListener {
            goToNextScreen(it)
        }

        tv_expense.setOnClickListener {
            activity?.toast("Expenses Tab clicked=========Size is=====${dataViewBinding.viewModel?.userListSize}")
        }

        //calendarView.tileSize = LinearLayout.LayoutParams.MATCH_PARENT
        calendarView.setHeaderTextAppearance(R.style.TextAppearance_AppCompat_Large)

        //calendarView.setDateTextAppearance(R.style.CustomDayTextAppearance)
        calendarView.tileSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 58F, resources.displayMetrics).toInt()
        //calendarView.tileSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 36F, resources.displayMetrics).toInt()

        //calendarView.setSelectedDates(selectedDates)

        // Create the adapter that will return a fragment for each of the tabs of the activity
        mSectionsPagerAdapter = PageAdapter(fragmentManager!!)

        // Set up the ViewPager with the sections adapter.
        viewpager?.adapter = mSectionsPagerAdapter

        tabs?.setupWithViewPager(viewpager)

        tabs?.getTabAt(0)?.text = resources.getString(R.string.income_text)
        tabs?.getTabAt(1)?.text = resources.getString(R.string.balance_text)
        tabs?.getTabAt(2)?.text = resources.getString(R.string.expense_text)

        calendarView.setOnDateClickListener(this)
    }

    private fun goToNextScreen(view: View) {
        val action = MainFragmentDirections.action_mainFragment_to_settingsFragment()

        val navController = view.findNavController()
        navController.navigate(action)
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun setMonthTitle() {
        tv_monthtitle.text = "${SimpleDateFormat("MMM").format(todaysDate.time)}\n${SimpleDateFormat("yyyy").format(todaysDate.time)}"
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun setNextDate() {
        val nextDate = Calendar.getInstance()
        nextDate.time = todaysDate.time
        nextDate.add(Calendar.MONTH, 1)
        tv_next.text = "${SimpleDateFormat("MMM").format(nextDate.time)}\n${SimpleDateFormat("yyyy").format(nextDate.time)}"
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun setPrevDate() {
        val prevDate = Calendar.getInstance()
        prevDate.time = todaysDate.time
        prevDate.add(Calendar.MONTH, -1)
        tv_previous.text = "${SimpleDateFormat("MMM").format(prevDate.time)}\n${SimpleDateFormat("yyyy").format(prevDate.time)}"
    }

    override fun onDateClicked(widget: MaterialCalendarView, date: CalendarDay, selected: Boolean) {
        // activity?.toast("onDateClicked===${date.date}")

        val action = MainFragmentDirections.action_mainFragment_to_transactionsFragment()
        action.setSelectedDate(date.date.toString())

        val navController = widget.findNavController()
        navController.navigate(action)

    }
}