package com.example.opiapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * CLass for displaying the main screen of the app
 */
public class MainActivity extends BaseActivity {

    private TextView tvProgressValue;
    private Button btnAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setupBottomNavigation();


        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }


        tvProgressValue = findViewById(R.id.tv_progress_value);
        btnAdd = findViewById(R.id.btn_add);


        if (btnAdd != null) {
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addWaterGlass();
                }
            });
        }
    }



    private int addWaterGlass() {
        try{
            totalDrank += 0.25;
            
// TEMPORAL FIX Avoid notification to be prompted everytime new water is added and goal is reached
            if (totalDrank >= targetHydration) {
                totalDrank = targetHydration;
                Toast.makeText(this, "Target reached!", Toast.LENGTH_SHORT).show();
                if(!reached){
                    sendNotification(1);
                }
                reached = true;
            }

            // Update the UI
            String progressText = String.format("%.2fL / %.1fL", totalDrank, targetHydration);
            tvProgressValue.setText(progressText);
            int userId = db.getuserID("a");
            db.upsertDailyHydration(userId, today, totalDrank);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }


}
