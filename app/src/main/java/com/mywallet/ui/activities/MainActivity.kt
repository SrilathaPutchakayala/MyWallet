package com.mywallet.ui.activities

import android.annotation.SuppressLint
//import android.arch.lifecycle.ViewModelProviders
//import android.databinding.DataBindingUtil
import android.os.Bundle
//import android.support.v7.app.AppCompatActivity
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.mywallet.R
import com.mywallet.databinding.ActivityMainBinding
import com.mywallet.ui.adapters.PageAdapter
import com.mywallet.viewmodel.MainViewModel
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateClickedListener
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by srilathaputchakayala on 17/5/18.
 */
@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity(), OnDateClickedListener {

    private var mSectionsPagerAdapter: PageAdapter? = null
    private lateinit var dataViewBinding: ActivityMainBinding

    var selectedDates = mutableListOf(Date(118, 4, 25), Date(118, 4, 20))

    val todaysDate = Calendar.getInstance()

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        dataViewBinding.viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val today = CalendarDay.today()

        calendarView.state().edit()
                .setFirstDayOfWeek(Calendar.MONDAY)
                .commit()

        calendarView.currentDate = today

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
            toast("Income Tab clicked===")
        }

        tv_settings.setOnClickListener {
            toast("Settings Tab clicked===")
        }

        tv_expense.setOnClickListener {
            toast("Expenses Tab clicked===")
        }

        //calendarView.tileSize = LinearLayout.LayoutParams.MATCH_PARENT
        calendarView.setHeaderTextAppearance(R.style.TextAppearance_AppCompat_Large)

        //calendarView.setDateTextAppearance(R.style.CustomDayTextAppearance)
        calendarView.tileSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 58F, resources.displayMetrics).toInt()
        //calendarView.tileSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 36F, resources.displayMetrics).toInt()

        calendarView.setSelectedDates(selectedDates)

        // Create the adapter that will return a fragment for each of the tabs of the activity
        mSectionsPagerAdapter = PageAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        viewpager?.adapter = mSectionsPagerAdapter

        tabs?.setupWithViewPager(viewpager)

        tabs?.getTabAt(0)?.text = resources.getString(R.string.income_text)
        tabs?.getTabAt(1)?.text = resources.getString(R.string.balance_text)
        tabs?.getTabAt(2)?.text = resources.getString(R.string.expense_text)

        calendarView.setOnDateClickListener(this)
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
        toast("onDateClicked===${date.date}")
        startActivity<TransactionsActivity>("selectedDate" to date.date)
    }
}