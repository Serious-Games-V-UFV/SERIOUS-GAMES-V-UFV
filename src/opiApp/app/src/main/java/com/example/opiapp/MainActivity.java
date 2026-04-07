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
    public TextView tvProgressValue;
    private Button btnAdd;
    public TextView userFullname;

    public TextView currentStreakText;

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
        
        UI_Updater uiu = new UI_Updater(this);
        uiu.start();

        // Carga datos iniciales de DB
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

        double snapshot = totalDrunk;
        executor.execute(() -> {
            db.updateDailyReminder(currentUser, today, snapshot);
            updateProgressUI();
        });
    }

    public void updateProgressUI() {
        executor.execute(() -> {
            // Petición a la base de datos para ver si el agua cambió
            String total = db.getTodayHydration(today);
            double newTotalDrunk = (total == null || total.isEmpty()) ? 0 : Double.parseDouble(total);
            
            // Obtener el streak
            int streak = db.getUserStreak(String.valueOf(currentUser));

            runOnUiThread(() -> {
                this.totalDrunk = newTotalDrunk;
                
                String progressText = String.format("%.2fL / %.1fL", totalDrunk, targetHydration / 1000.0);
                if (tvProgressValue != null) {
                    tvProgressValue.setText(progressText);
                }

                if (currentStreakText != null) {
                    if (streak == 1) {
                        currentStreakText.setText(streak + " día");
                    } else {
                        currentStreakText.setText(streak + " días");
                    }
                }
                
                // Verificar meta
                double targetInLiters = targetHydration / 1000.0;
                if (totalDrunk >= targetInLiters && !reached) {
                    sendNotification(1);
                    reached = true;
                    saveReachedStatus();
                }
            });
        });
    }
}
