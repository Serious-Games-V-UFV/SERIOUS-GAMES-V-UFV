package com.example.opiapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import static java.util.concurrent.TimeUnit.*;
import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends BaseActivity {

    private TextView tvProgressValue;
    private Button btnAdd;
    private double totalDrunk = 0.0;
    private final double targetHydration = 5;
    private boolean bottlePlaced = false;
    private int hoursSinceDrink = 0;
    private double capacity = 750;
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
            totalDrunk += 0.25;
            // FIXME Avoid notification to be prompted everytime new water is added
            if (totalDrunk >= targetHydration) {
                totalDrunk = targetHydration;
                Toast.makeText(this, "Target reached!", Toast.LENGTH_SHORT).show();
                sendNotification(1);
            }

            // Update the UI
            String progressText = String.format("%.2fL / %.1fL", totalDrunk, targetHydration);
            tvProgressValue.setText(progressText);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    }

    /**
     * Process the data received by the bluetooth connection (if not null)
     * If capacity received differs from stored value, updates stored
     * If totalDrunk received is greater than stored value, updates stored
     * @param dataStream data received
     */
    private void processWaterData(WaterData dataStream){
        if(dataStream != null){
            if(dataStream.capacity != capacity){
                capacity = dataStream.capacity;
            }
            if(dataStream.totalDrunk > totalDrunk){
                totalDrunk = dataStream.totalDrunk;
            }
            bottlePlaced = dataStream.bottlePlaced;
            hoursSinceDrink = dataStream.hoursSinceDrink;
        }else{
            sendNotification(2);
        }
    }
}
