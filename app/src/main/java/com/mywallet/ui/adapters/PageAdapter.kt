package com.mywallet.ui.adapters

/*import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter*/
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.mywallet.ui.fragments.BalanceFragment
import com.mywallet.ui.fragments.ExpenseFragment
import com.mywallet.ui.fragments.IncomeFragment

/**
 * Created by srilathaputchakayala on 29/5/18.
 */
class PageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> IncomeFragment.newInstance()
            1 -> BalanceFragment.newInstance()
            else -> {
                ExpenseFragment.newInstance()
            }
        }
    }

    override fun getCount(): Int {
        // Show 3 total pages.
        return 3
    }

}