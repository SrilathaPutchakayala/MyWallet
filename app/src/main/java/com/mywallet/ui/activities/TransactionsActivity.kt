package com.mywallet.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
//import android.support.v7.app.AppCompatActivity
import com.mywallet.R
import kotlinx.android.synthetic.main.activity_transactions.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by srilathaputchakayala on 29/5/18.
 */
class TransactionsActivity : AppCompatActivity() {


    private var currentDate: Date? = null

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transactions)

        if (intent?.extras != null) {
            if (intent.extras.containsKey("selectedDate")) {

                currentDate = intent.extras.get("selectedDate") as Date

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

}