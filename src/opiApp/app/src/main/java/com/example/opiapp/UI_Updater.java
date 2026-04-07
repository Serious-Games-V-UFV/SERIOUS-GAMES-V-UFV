package com.example.opiapp;

public class UI_Updater extends Thread {

    boolean repeat = true;
    MainActivity ma = null;

    UI_Updater(MainActivity ma) {
        ma = this.ma;
    }

    public void run() {
        try {
            while (repeat) {
                String progressText = String.format("%.2fL / %.1fL", ma.totalDrunk, ma.targetHydration / 1000.0);
                ma.tvProgressValue.setText(progressText);

                ma.executor.execute(() -> {
                    int streak = ma.db.getUserStreak(String.valueOf(ma.currentUser));
                    System.out.println("Current streak: " + streak);
                    ma.runOnUiThread(() -> {
                        if (ma.currentStreakText != null) {
                            if (streak == 1) {
                                ma.currentStreakText.setText(String.valueOf(streak + " día"));
                            } else if (streak > 1 || streak == 0) {
                                ma.currentStreakText.setText(String.valueOf(streak + " días"));
                            }
                        }
                    });
                });
            }
            Thread.sleep(2000);
        } catch (Exception e) {

        }


    }

}
