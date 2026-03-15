package com.example.opiapp;

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

    /**
     * This method return a specific piece of data
     * from a specific table and column.
     * @param table Tells the table name
     * @param column_name Tells the column name
     * @param id Tells the user whose datum will be returned
     * @return Will return a String with the datum or "" if not found
     */
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

    /**
     * Very general query requester in case of very specific queries
     * @param query is the entire query
     * @return query result
     */
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

    /**
     * Updates a datum in a specified table and column
     * @param table table in which the datum is updated
     * @param column_name column in which the datum is updated
     * @param value new value assigned to the datum
     * @param id for sql syntax and conditional
     * @return ps.executeUpdate() regarding database output | -1 states an error
     */
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

    /**
     * Gets a tuple of data from the database.
     * @param table the table from which the data is selected
     * @param id    filtering for the correct data
     * @return String containing all the values of the query, or null on error
     */
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

    /**
     * Returns id from the user currently logged in
     * @param email email from the user (unique key in db)
     * @return id from the user
     */
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

    /**
     * Returns today's amount_drunk for the current user and date.
     * @param date today's date as String (LocalDate.now().toString())
     * @return amount_drunk as String, or "0" if not found
     */
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

    /**
     * Inserts or updates the daily hydration record for a given user and date.
     * @param userId    account id
     * @param date      date string
     * @param amount    total water drank today in litres
     */
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

    /**
     * Validates user credentials against the database.
     * @param email    user email
     * @param password user password
     * @return true if credentials match, false otherwise
     */
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
}