package com.example.opiapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.UUID;

public class Bluetooth {

    private BluetoothAdapter adapter;
    private BluetoothSocket socket;
    private InputStream inputStream;

    private static final UUID UUID_BT =
            UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    public Bluetooth() {
        adapter = BluetoothAdapter.getDefaultAdapter();
    }

    public boolean connect(String deviceName) {
        try {

            BluetoothDevice device = null;

            for (BluetoothDevice d : adapter.getBondedDevices()) {
                if (d.getName().equals(deviceName)) {
                    device = d;
                    break;
                }
            }

            if (device == null)
                return false;

            socket = device.createRfcommSocketToServiceRecord(UUID_BT);
            socket.connect();

            inputStream = socket.getInputStream();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public WaterData readData() {

        try {

            byte[] buffer = new byte[1024];
            int bytes = inputStream.read(buffer);

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

    public void disconnect() {
        try {
            socket.close();
        } catch (Exception ignored) {}
    }
}