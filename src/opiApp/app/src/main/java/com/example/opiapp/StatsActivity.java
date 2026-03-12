package com.example.opiapp;

import android.os.Bundle;
import android.widget.CalendarView;
import java.util.Calendar;

public class StatsActivity extends BaseActivity {
    public static int dayStreak = 0;
    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        setupBottomNavigation();

        calendarView = findViewById(R.id.calendarView);

        // Establecer la fecha de hoy como seleccionada por defecto
        long todayMillis = Calendar.getInstance().getTimeInMillis();
        calendarView.setDate(todayMillis, false, true);

        // Ejemplo: Escuchar cuando el usuario cambia de fecha
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            // El mes (month) empieza en 0 (Enero = 0)
            String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            // Aquí podrías cargar los datos de hidratación de ese día específico
        });
    }
}
