package com.prolificinteractive.materialcalendarview;

//import android.support.annotation.NonNull;

import androidx.annotation.NonNull;

/**
 * Created by srilathaputchakayala on 29/5/18.
 */

public interface OnDateClickedListener {
    /**
     * Called when a user clicks on a day.
     * There is no logic to prevent multiple calls for the same date and state.
     *
     * @param widget   the view associated with this listener
     * @param date     the date that was selected or unselected
     * @param selected true if the day is now selected, false otherwise
     */
    void onDateClicked(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected);
}

