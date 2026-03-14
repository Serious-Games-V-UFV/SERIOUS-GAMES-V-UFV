package com.example.opiapp;

import android.util.Log;
import java.sql.*;

public class Database {

    private Connection con = null;
    private static final String TAG = "Database";
    private final String url = "jdbc:mysql://smiguels.net:3306/opi_backend?useSSL=false&allowPublicKeyRetrieval=true&autoReconnect=true";
    private final String user = "serious";
    private final String pass = "game";

    public Database() {
        connect();
    }

    private void connect() {
        new Thread(() -> {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url, user, pass);
                if (con != null) {
                    Log.d(TAG, "Database connected successfully.");
                }
            } catch (Exception e) {
                Log.e(TAG, "Connection failed: " + e.getMessage());
            }
        }).start();
    }

    public boolean isConnected() {
        try {
            return con != null && !con.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    private void ensureConnection() {
        if (!isConnected()) {
            Log.d(TAG, "Connection lost, attempting to reconnect...");
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url, user, pass);
            } catch (Exception e) {
                Log.e(TAG, "Reconnection failed: " + e.getMessage());
            }
        }
    }

    public String getDatum(String table, String column_name, int id) {
        ensureConnection();
        if (!isConnected()) return "";

        String datum = "";
        String query = "SELECT " + column_name + " FROM " + table + " WHERE id = ?;";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    datum = rs.getString(1);
                }
            }
        } catch (SQLException e) {
            Log.e(TAG, "Error in getDatum: " + e.getMessage());
        }
        return datum;
    }

    public int getuserID(String email) {
        ensureConnection();
        if (!isConnected()) return -1;

        String query = "SELECT id FROM account WHERE email = ?;";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            Log.e(TAG, "Error in getuserID: " + e.getMessage());
        }
        return -1;
    }
    
    // Helper specifically for hydration data if needed in BaseActivity
    public String getTodayHydration(String date) {
        ensureConnection();
        if (!isConnected()) return "0";
        
        String query = "SELECT total_drank FROM daily_hydration WHERE account = ? AND date = ?;";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, BaseActivity.currentUser);
            ps.setString(2, date);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
        } catch (SQLException e) {
            Log.e(TAG, "Error getting hydration: " + e.getMessage());
        }
        return "0";
    }

    public void updateDailyReminder(int userId, String date, double amount) {
        ensureConnection();
        if (!isConnected()) return;

        String query = "INSERT INTO daily_hydration (account, date, total_drank) VALUES (?, ?, ?) " +
                       "ON DUPLICATE KEY UPDATE total_drank = ?;";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setString(2, date);
            ps.setDouble(3, amount);
            ps.setDouble(4, amount);
            ps.executeUpdate();
        } catch (SQLException e) {
            Log.e(TAG, "Error updating hydration: " + e.getMessage());
        }
    }

    public boolean validateUser(String email, String password) {
        ensureConnection();
        if (!isConnected()) return false;
        
        String query = "SELECT password FROM account WHERE email = ?;";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String stored = rs.getString(1);
                    return stored != null && stored.equals(password);
                }
            }
        } catch (SQLException e) {
            Log.e(TAG, "Validate error: " + e.getMessage());
        }
        return false;
    }
}
