package com.example.opiapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import java.util.Calendar;

public class StatsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        setupBottomNavigation();

        CalendarView calendarView = findViewById(R.id.calendarView);

        long todayMillis = Calendar.getInstance().getTimeInMillis();
        calendarView.setDate(todayMillis, false, true);

        // Listener for when the user selects another date
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            // TODO: load data from selectedDate
            Log.d("StatsActivity", "Date selected: " + selectedDate);
        });
    }
}
