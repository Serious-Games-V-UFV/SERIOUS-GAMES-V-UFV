package com.example.opiapp;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Class for database handling operations
 */
public class Database {

    //===========================ATTRIBUTES===========================//
    private Connection con = null;
    private static final String TAG = "Database";
    private final String url = "jdbc:mysql://smiguels.net:3306/opi_backend?useSSL=false&allowPublicKeyRetrieval=true&autoReconnect=true";
    private final String user = "serious";
    private final String pass = "game";

    //===========================CONSTRUCTOR===========================//
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

    private synchronized void ensureConnection() {
        if (!isConnected()) {
            Log.d(TAG, "Connection lost, attempting to reconnect...");
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url, user, pass);
                Log.d(TAG, "Database connected successfully.");
            } catch (Exception e) {
                Log.e(TAG, "Reconnection failed: " + e.getMessage());
            }
        }
    }

//===========================METHODS===========================//

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

    public String generalQuery(String query) {
        ensureConnection();
        if (!isConnected()) return "";

        String datum = "";
        try (PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                datum = rs.getString(1);
            }
        } catch (SQLException e) {
            Log.e(TAG, "Error in generalQuery: " + e.getMessage());
        }
        return datum;
    }

    public int updateDatum(String table, String column_name, String value, int id) {
        ensureConnection();
        if (!isConnected()) return -1;

        String query = "UPDATE " + table + " SET " + column_name + " = ? WHERE id = ?;";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, value);
            ps.setInt(2, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            Log.e(TAG, "Error in updateDatum: " + e.getMessage());
            return -1;
        }
    }

    public String getTuple(String table, int id) {
        ensureConnection();
        if (!isConnected()) return null;

        StringBuilder result = new StringBuilder();
        String query = "SELECT * FROM " + table + " WHERE id = ?;";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ResultSetMetaData metaData = rs.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    for (int i = 1; i <= columnCount; i++) {
                        result.append(metaData.getColumnName(i))
                                .append(": ")
                                .append(rs.getString(i))
                                .append(", ");
                    }
                }
                return result.toString();
            }
        } catch (SQLException e) {
            Log.e(TAG, "Error in getTuple: " + e.getMessage());
            return null;
        }
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

    public String getTodayHydration(String date) {
        ensureConnection();
        if (!isConnected()) return "0";

        String query = "SELECT amount_drunk FROM daily_reminder WHERE account_id = ? AND date = ?;";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, BaseActivity.currentUser);
            ps.setString(2, date);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
        } catch (SQLException e) {
            Log.e(TAG, "Error in getTodayHydration: " + e.getMessage());
        }
        return "0";
    }

    public void updateDailyReminder(int userId, String date, double amount) {
        ensureConnection();
        if (!isConnected()) return;

        String query = "INSERT INTO daily_reminder (account_id, date, amount_drunk) VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE amount_drunk = ?;";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setString(2, date);
            ps.setDouble(3, amount);
            ps.setDouble(4, amount);
            ps.executeUpdate();
        } catch (SQLException e) {
            Log.e(TAG, "Error in updateDailyReminder: " + e.getMessage());
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
            Log.e(TAG, "Error in validateUser: " + e.getMessage());
        }
        return false;
    }

    public int getUserStreak(String user) {
        ensureConnection();
        if (!isConnected()) return -1;

        String query = "WITH days AS (SELECT date FROM daily_reminder WHERE account_id = ? AND amount_drunk > 0), ranked AS (SELECT date, ROW_NUMBER() OVER (ORDER BY date DESC) AS rn FROM days), grouped AS (SELECT date, DATE_ADD(date, INTERVAL rn DAY) AS grp FROM ranked) SELECT COUNT(*) AS streak FROM grouped WHERE grp = (SELECT grp FROM grouped ORDER BY date DESC LIMIT 1);";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, user);
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

    /**
     * Inserts a new user into the account table.
     */
    public boolean registerUser(String firstName, String lastName1, String lastName2, String phone, String email, 
                               int height, int weight, String birthDate, int desiredWater, String password) {
        ensureConnection();
        if (!isConnected()) return false;

        String query = "INSERT INTO account (first_name, last_name1, last_name2, phone, email, height, weight, birth_date, desired_water, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, firstName);
            ps.setString(2, lastName1);
            ps.setString(3, lastName2);
            ps.setString(4, phone);
            ps.setString(5, email);
            ps.setInt(6, height);
            ps.setInt(7, weight);
            ps.setString(8, birthDate);
            ps.setInt(9, desiredWater);
            ps.setString(10, password);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            Log.e(TAG, "Error al registrar el usuario: " + e.getMessage());
            return false;
        }
    }
}
