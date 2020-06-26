package com.mywallet.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mywallet.R
import kotlinx.android.synthetic.main.activity_transactions.*
import java.text.SimpleDateFormat
import java.util.*

class TransactionsFragment : Fragment() {

    private var currentDate: Date? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_transaction, container, false)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {

            val passedSelectedDate = TransactionsFragmentArgs.fromBundle(it)

            //Fri Jul 06 00:00:00 AEST 2018

            currentDate = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(passedSelectedDate.selectedDate)

            val selectedCalDate = Calendar.getInstance()
            selectedCalDate.time = currentDate
            tv_selecteddate.text = SimpleDateFormat("EEEE,MMM dd").format(selectedCalDate.time).toString()
            tv_selectedYear.text = SimpleDateFormat("yyyy").format(selectedCalDate.time).toString()

            setNextDate()
            setPrevDate()

        }

        nextCalDate.setOnClickListener {

            val nextDate = Calendar.getInstance()
            nextDate.time = currentDate
            nextDate.add(Calendar.DAY_OF_YEAR, 1)
            currentDate = nextDate.time

            tv_selecteddate.text = SimpleDateFormat("EEEE,MMM dd").format(nextDate.time).toString()
            tv_selectedYear.text = SimpleDateFormat("yyyy").format(nextDate.time).toString()

            setNextDate()
            setPrevDate()
        }

        prevCalDate.setOnClickListener {
            val nextDate = Calendar.getInstance()
            nextDate.time = currentDate
            nextDate.add(Calendar.DAY_OF_YEAR, -1)
            currentDate = nextDate.time

            tv_selecteddate.text = SimpleDateFormat("EEEE,MMM dd").format(nextDate.time).toString()
            tv_selectedYear.text = SimpleDateFormat("yyyy").format(nextDate.time).toString()

            setNextDate()
            setPrevDate()
        }
    }


    @SuppressLint("SimpleDateFormat")
    private fun setNextDate() {
        val nextDate = Calendar.getInstance()
        nextDate.time = currentDate
        nextDate.add(Calendar.DAY_OF_YEAR, 1)
        nextCalDate.text = SimpleDateFormat("MMM dd").format(nextDate.time).toString()
    }

    @SuppressLint("SimpleDateFormat")
    private fun setPrevDate() {
        val prevDate = Calendar.getInstance()
        prevDate.time = currentDate
        prevDate.add(Calendar.DAY_OF_YEAR, -1)
        prevCalDate.text = SimpleDateFormat("MMM dd").format(prevDate.time).toString()
    }

    companion object {

        fun newInstance():TransactionsFragment{

            return TransactionsFragment()
        }
    }
}