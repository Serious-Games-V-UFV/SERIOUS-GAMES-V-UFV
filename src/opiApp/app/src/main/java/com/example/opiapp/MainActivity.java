package com.example.opiapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tvProgressValue;
    private Button btnAdd;
    private double currentHydration = 0.0;
    private final double targetHydration = 5;   // cambiarlo a que sea el valor de la variable de la base de datos aguaDeseada

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Handle window insets for Edge-to-Edge
        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // Initialize UI elements
        tvProgressValue = findViewById(R.id.tv_progress_value);
        btnAdd = findViewById(R.id.btn_add);

        // Example interaction in Java
        if (btnAdd != null) {
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addWater(0.25);
                }
            });
        }
    }

    private int addWater(double amount) {
        try{
            currentHydration += amount;
            if (currentHydration > targetHydration) {
                currentHydration = targetHydration;
                Toast.makeText(this, "Target reached!", Toast.LENGTH_SHORT).show();
            }

            // Update the UI
            String progressText = String.format("%.2fL / %.1fL", currentHydration, targetHydration);
            tvProgressValue.setText(progressText);
            return 0;
        } catch (Exception e) {
            return 1;
        }
            }
        }
