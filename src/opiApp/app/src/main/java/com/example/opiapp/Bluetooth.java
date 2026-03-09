package com.example.opiapp;

import android.Manifest;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import androidx.annotation.RequiresPermission;
import org.json.JSONObject;
import java.io.InputStream;
import java.util.UUID;

public class Bluetooth{ // extends Service {

    private BluetoothAdapter blueadapter;
    private BluetoothSocket socket;
    private InputStream inputStream;

    private static final UUID UUID_BT =
            UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    public Bluetooth() {
        blueadapter = BluetoothAdapter.getDefaultAdapter();
    }


    @RequiresPermission(Manifest.permission.BLUETOOTH_CONNECT)
    public boolean connect(String deviceName) {
        try {

            BluetoothDevice bluedevice = null;

            for (BluetoothDevice d : blueadapter.getBondedDevices()) {
                if (d.getName().equals(deviceName)) {
                    bluedevice = d;
                    break;
                }
            }

            if (bluedevice == null)
                return false;

            socket = bluedevice.createRfcommSocketToServiceRecord(UUID_BT);
            socket.connect();

            inputStream = socket.getInputStream();

            return true;

        } catch (Exception e) {
            System.err.println(e.toString());
            return false;
        }
    }

    public WaterData readData() {

        try {
            byte[] buffer = new byte[1024];
            int bytes = inputStream.read(buffer);
            if (bytes <0){return null;}

            String message = new String(buffer, 0, bytes);
            JSONObject json = new JSONObject(message);

            double capacity = json.getDouble("capacity");
            int hours = json.getInt("hoursSinceDrink");
            double totalDrunk = json.getDouble("totalDrunk");
            boolean bottlePlaced = json.getBoolean("bottlePlaced");

            return new WaterData(capacity, hours, totalDrunk, bottlePlaced);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
//    public WaterData readData(boolean develop){
    //TODO: BORRAR AL TERMINAR EL DESARROLLO
//        double capacity = 740;
//        int hoursSinceDrink = 3;
//        double totalDrunk = 0;
//        boolean bottlePlaced = false;
//        return new WaterData(capacity, hoursSinceDrink, totalDrunk, bottlePlaced);
    //}

    public void disconnect() {
        try {
            socket.close();
        } catch (Exception ignored) {}
    }
}