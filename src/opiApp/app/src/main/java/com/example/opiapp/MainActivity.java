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

public class MainActivity extends BaseActivity {

    private TextView tvProgressValue;
    private Button btnAdd;

    Bluetooth btcon = new Bluetooth();








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setupBottomNavigation();
        // TODO: Integrate it with Service (developer.android.com/develop/background-work/services)
        //  or ForegroundServices (https://developer.android.com/develop/background-work/services/fgs)
            WaterData receivedData = btcon.readData();
            processWaterData(receivedData);

        super.createNotificationChannel("Hydration Goal","Notifications for reaching hydration goal");
        super.requestNotificationPermission();


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
            
            // FIXME Avoid notification to be prompted everytime new water is added
            if (totalDrank >= targetHydration) {
                totalDrank = targetHydration;
                Toast.makeText(this, "Target reached!", Toast.LENGTH_SHORT).show();
                sendNotification(1);
            }

            // Update the UI
            String progressText = String.format("%.2fL / %.1fL", totalDrank, targetHydration);
            tvProgressValue.setText(progressText);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }


}
