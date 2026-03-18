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
 * Class for displaying the main screen of the app
 */
public class MainActivity extends BaseActivity {
    private TextView tvProgressValue;
    private Button btnAdd;
    private TextView userFullname;

    private TextView currentStreakText;

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
        userFullname = findViewById(R.id.userFullName);
        currentStreakText = findViewById(R.id.currentStreak);


        // Carga datos de DB y luego actualiza UI
        executor.execute(() -> {
            targetHydration = Double.parseDouble(db.getDatum("account", "desired_water", currentUser));
            String userName = db.getDatum("account", "first_name", currentUser);
            String formattedName = userName.substring(0, 1).toUpperCase() + userName.substring(1);

            runOnUiThread(() -> {
                userFullname.setText(formattedName);
                updateProgressUI();
            });
        });

        if (btnAdd != null) {
            btnAdd.setOnClickListener(v -> addWaterGlass());
        }
    }

    private void addWaterGlass() {
        totalDrunk += 0.25;

        double targetInLiters = targetHydration / 1000.0;
        if (totalDrunk >= targetInLiters) {
            if (!reached) {
                Toast.makeText(this, "Target reached!", Toast.LENGTH_SHORT).show();
                sendNotification(1);
                reached = true;
                saveReachedStatus();
            }
        }

        updateProgressUI();

        double snapshot = totalDrunk;
        executor.execute(() -> db.updateDailyReminder(currentUser, today, snapshot));
    }

    private void updateProgressUI() {
        String progressText = String.format("%.2fL / %.1fL", totalDrunk, targetHydration / 1000.0);
        tvProgressValue.setText(progressText);

        executor.execute(() -> {
            int streak = db.getUserStreak(String.valueOf(currentUser));
            System.out.println("Current streak: " + streak);
            runOnUiThread(() -> {
                if (currentStreakText != null) {
                    if (streak == 1) {
                        currentStreakText.setText(String.valueOf(streak + " día"));
                    } else if (streak > 1 || streak == 0) {
                        currentStreakText.setText(String.valueOf(streak + " días"));
                    }
                }
            });
        });

    }
}
