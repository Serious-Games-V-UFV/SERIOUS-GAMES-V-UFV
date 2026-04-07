package com.example.opiapp;

public class UI_Updater extends Thread {

    private boolean repeat = true;
    private final MainActivity ma;

    UI_Updater(MainActivity ma) {
        this.ma = ma;
    }

    public void stopUpdating() {
        repeat = false;
    }

    @Override
    public void run() {
        while (repeat) {
            try {
                if (ma != null && !ma.isFinishing()) {
                    // Simplemente llamamos al método que ya maneja la lógica de DB y UI
                    ma.updateProgressUI();
                } else {
                    repeat = false;
                }
                
                // Dormimos el hilo durante 1 segundo (1000 ms)
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                repeat = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
