package com.example.opiapp;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Base class for activities that share common functionality like the bottom navigation bar.
 * the notification send
 */
public class BaseActivity extends AppCompatActivity {

    /**
     * Allows the notification to be on top of the screen
     */
    final static int NOTIFICATION_PRIORITY = NotificationManager.IMPORTANCE_HIGH;
    protected static final String CHANNEL_ID = "hydration_notifications";
    protected static final int NOTIFICATION_ID = 1;
    protected double totalDrunk = 0.0;
    protected double targetHydration = 2.5;
    protected boolean isBottlePlaced = false;
    protected int hoursSinceDrink = 0;
    protected double capacity = 750;
    protected boolean reached = false;
    protected Bluetooth btcon = new Bluetooth();
    protected static Database db;
    protected String today = LocalDate.now().toString();

    // Executor for background tasks
    protected final ExecutorService executor = Executors.newSingleThreadExecutor();

    protected static int currentUser = -1;
    protected static int dayStreak = 0;

    protected static final String PREFS_NAME = "OpiAppPrefs";
    protected static final String KEY_USER_ID = "userId";
    protected static final String KEY_REACHED_PREFIX = "reached_";
    protected SharedPreferences prefs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        requestNotificationPermission();
        createNotificationChannel("Hydration Goal", "Notifications for reaching hydration goal");

        prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        
        // Restore currentUser from SharedPreferences if not set
        if (currentUser == -1) {
            currentUser = prefs.getInt(KEY_USER_ID, -1);
        }

        // Carga si ya se alcanzó la meta hoy
        if (currentUser != -1) {
            reached = prefs.getBoolean(KEY_REACHED_PREFIX + today + "_" + currentUser, false);
        }

        executor.execute(() -> {
            if (db == null) {
                db = new Database();
            }
            String total = db.getTodayHydration(today);
            runOnUiThread(() -> {
                totalDrunk = (total == null || total.isEmpty()) ? 0 : Double.parseDouble(total);
            });
        });
    }

    protected void saveUserSession(int userId) {
        currentUser = userId;
        if (prefs == null) prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putInt(KEY_USER_ID, userId).apply();
    }

    protected void saveReachedStatus() {
        if (currentUser != -1) {
            if (prefs == null) prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            prefs.edit().putBoolean(KEY_REACHED_PREFIX + today + "_" + currentUser, true).apply();
            reached = true;
        }
    }

    protected void logout() {
        currentUser = -1;
        if (prefs == null) prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().remove(KEY_USER_ID).apply();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


//===========================NAVIGATION===========================//

    /**
     * Initializes and configures the click listeners for the bottom navigation bar.
     * It maps each TextView to its corresponding Activity, handling transitions
     * through Intents while preventing the current Activity from restarting
     * if the user is already on the selected screen.
     */
    protected void setupBottomNavigation() {
        TextView navHome = findViewById(R.id.btn_nav_home);
        TextView navStats = findViewById(R.id.btn_nav_stats);
        TextView navSocial = findViewById(R.id.btn_nav_social);
        TextView navTools = findViewById(R.id.btn_nav_profile);

        if (navHome != null) {
            navHome.setOnClickListener(v -> {
                if (!(this instanceof MainActivity)) {
                    startActivity(new Intent(this, MainActivity.class));
                }
            });
        }

        if (navStats != null) {
            navStats.setOnClickListener(v -> {
                if (!(this instanceof StatsActivity)) {
                    startActivity(new Intent(this, StatsActivity.class));
                }
            });
        }

        if (navSocial != null) {
            navSocial.setOnClickListener(v -> {
                startActivity(new Intent(this, SocialActivity.class));
            });
        }

        if (navTools != null) {
            navTools.setOnClickListener(v -> {
                startActivity(new Intent(this, ProfileActivity.class));
            });
        }
    }

//===========================NOTIFICATIONS===========================//

    /**
     * Launcher for requesting runtime permissions from the user.
     * This specific instance handles the request for notification permissions,
     * triggering a toast message if the user denies the request.
     */
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (!isGranted) {
                    Toast.makeText(this, "Notification permission denied", Toast.LENGTH_SHORT).show();
                }
            });

    /**
     * Creates a notification channel for a specific category of notifications.
     * @param name        name for the channel
     * @param description context for the channel
     */
    protected void createNotificationChannel(String name, String description) {
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, NOTIFICATION_PRIORITY);
        channel.setDescription(description);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

    /**
     * Requests permission to send notifications.
     */
    protected void requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) !=
                    PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            }
        }
    }

    /**
     * Sends a specific notification based on a code.
     * @param code states the type of notification to be sent:
     *             1 = hydration goal reached
     *             2 = backpack not connected
     */
    protected void sendNotification(int code) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setColor(ContextCompat.getColor(this, R.color.white))
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        if (code == 1) {
            builder.setAutoCancel(true);
            builder.setContentTitle("Meta conseguida");
            builder.setContentText("¡Felicidades! Has llegado a tu meta de hidratación.");
        } if (code == 2) {
            builder.setSilent(true);
            builder.setOngoing(true);
            builder.setContentTitle("Mochila no conectada");
            builder.setContentText("No se ha encontrado una conexion con la mochila, conectala");
        }

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED ||
                Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }
    }

//===========================DataProcessing===========================//

    /**
     * Processes the data received via Bluetooth connection (if not null).
     * If capacity received differs from stored value, updates stored capacity.
     * If totalDrunk received is greater than stored value, updates stored totalDrunk.
     * @param dataStream data received from the board
     */
    protected void processWaterData(WaterData dataStream) {
        if (dataStream != null) {
            if (dataStream.getCapacity() != capacity) {
                capacity = dataStream.getCapacity();
            }
            if (dataStream.getTotalDrunk() > totalDrunk) {
                totalDrunk = dataStream.getTotalDrunk();
            }
            isBottlePlaced = dataStream.isBottlePlaced();
            hoursSinceDrink = dataStream.getHoursSinceDrink();
        } else {
            sendNotification(2);
        }
    }

    /**
     * Overload of processWaterData for real sensor data from the LilyPad.
     * The boolean parameter exists solely to differentiate this overload
     * from the standard one — call this version when weight data does not come from the board.
     * @param dataStream data received from LilyPad via Bluetooth
     * @param isFaking   FIXME: remove after development — used to invoke this overload during testing
     */
    protected void processWaterData(WaterData dataStream, boolean isFaking) {
        double alterValue = 0.1 + (Math.random() * (0.5 - 0.1));
        if (dataStream != null) {
            isBottlePlaced = dataStream.isBottlePlaced();
            if (!isBottlePlaced) {
                capacity -= alterValue;
                totalDrunk += alterValue;
            }
            hoursSinceDrink = dataStream.getHoursSinceDrink();
        } else {
            sendNotification(2);
        }
    }
}
