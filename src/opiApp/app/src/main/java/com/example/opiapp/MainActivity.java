package com.example.opiapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

/**
 * CLass for displaying the main screen of the app
 */
public class MainActivity extends BaseActivity {

    private TextView tvProgressValue;
    private Button btnAdd;
    private TextView userFullname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setupBottomNavigation();
        setPersonalValues();


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
        userFullname = findViewById(R.id.userFullName);


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
        try {
            totalDrank += 0.25;

            if (totalDrank >= targetHydration) {

                Toast.makeText(this, "Target reached!", Toast.LENGTH_SHORT).show();
                if (!reached) {
                    sendNotification(1);
                }
                reached = true;
            }

            String progressText = String.format("%.2fL / %.1fL", totalDrank, targetHydration);
            tvProgressValue.setText(progressText);

            double snapshot = totalDrank;
            executor.execute(() -> db.updateDailyReminder(currentUser, today, snapshot));

            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    public void setPersonalValues() {
        //System.out.println(db.getDatum("account", "desired_water", currentUser));

        executor.execute(() -> {
            targetHydration = Double.parseDouble(db.getDatum("account", "desired_water", currentUser));
            String progressText = String.format("%.2fL / %.1fL", totalDrank, targetHydration/1000);
            tvProgressValue.setText(progressText);

            String user_name = db.getDatum("account", "first_name", currentUser);
            user_name = user_name.substring(0,1).toUpperCase() + user_name.substring(1);
            userFullname.setText(user_name);
        });

    }
    public void updatePersonalValues() {

        String progressText = String.format("%.2fL / %.1fL", totalDrank, targetHydration/1000);
        tvProgressValue.setText(progressText);

    }
}
