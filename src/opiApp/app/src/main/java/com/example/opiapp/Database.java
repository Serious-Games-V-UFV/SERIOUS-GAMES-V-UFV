package com.example.opiapp;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for database handling operations
 */
public class Database {

//===========================ATTRIBUTES===========================//
    Connection con = null;


//===========================CONSTRUCTOR===========================//
    public Database() {

        String url = "jdbc:mysql://smiguels.net:3306/opi_backend";
        String user = "opi_code";
        String pass = "admin";

        try {
            con = DriverManager.getConnection(url, user, pass);
            if (con != null) {
                System.out.println("Connected to opi_backend at smiguels.net");
            }
        } catch (SQLException ex) {
            System.out.println("Cannot connect to opi_backend at smiguels.net");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** Check if connection is available before running queries */
    private boolean isConnected() {
        try {
            return con != null && !con.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

//===========================METHODS===========================//

    /**
     * This method return a specific piece of data
     * from a specific table and column.
     * @param table Tells the table name
     * @param column_name Tells the column name
     * @param id Tells the user whose datum will be returned
     * @return Will return a String with the datum or "" if the
     */
    public String getDatum(String table, String column_name, int id) {
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
            System.out.println(e.getMessage());
        }
        return datum;
    }

    /**
     * Very general query requester in case of very specific queries
     * @param query is the entire query
     * @return query result
     */
    public String generalQuery(String query) {
        if (!isConnected()) return "";
        String datum = "";
        try (PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                datum = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return datum;
    }

    /**
     * inserts a specific datum into a selected table and column
     *
     * @param table the table in which the specific piece of datum is inserted
     * @param column_name the column in which the specific piece of datum is inserted
     * @param value datum inserted
     * @return ps.executeUpdate() regarding database | -1 states an error
     */
    public int insertDatum(String table, String column_name, String value) {
        if (!isConnected()) return -1;
        String query = "INSERT INTO " + table + " (" + column_name + ") VALUES (?);";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, value);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }


    /**
     * Updates a datum in an specified table and column
     * @param table table in which the datum is updated
     * @param column_name column in which the datum is updated
     * @param value new value assigned to the datum
     * @param id for sql syntax and conditional
     * @return ps.executeUpdate() regarding database output | -1 states an error
     */
    public int updateDatum(String table, String column_name, String value, int id) {
        if (!isConnected()) return -1;
        String query = "UPDATE " + table + " SET " + column_name + " = ? WHERE id = ?;";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, value);
            ps.setInt(2, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            return -1;
        }
    }

    /**
     * Gets a tuple of data from the database, using try-with resources you ensure all resources
     * are closed if an exception is thrown.
     * @param table the table from which the data is selected
     * @param id    filtering for the correct data
     * @return result.toString() | String containing all the values of the query
     */
    public String getTuple(String table, int id) {
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
            System.err.println("Error al obtener tupla: " + e.getMessage());
            return null;
        }
    }

    /**
     * Returns id from the user currently logged in
     * @param email email from the user (unique key in db)
     * @return id from the user
     */
    public int getuserID(String email) {
        if (!isConnected()) return -1;
        String query = "SELECT id FROM account" + " WHERE email = ?;";
        int queryResult = 0;
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    queryResult = rs.getInt(1);
                }
                return queryResult;
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener email: " + e.getMessage());
            return -1;
        }
    }

    /**
     * Returns today's total_drank for the given date (used in BaseActivity to load state).
     * Uses PreparedStatement to avoid SQL injection.
     * @param date today's date as String (LocalDate.now().toString())
     * @return total_drank as String, or "" if not found
     */
    public String getTodayHydration(String date) {
        if (!isConnected()) return "";
        // NOTE: account filter pending until login/session is implemented
        String query = "SELECT total_drank FROM daily_hydration WHERE date = ? LIMIT 1;";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, date);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    /**
     * Inserts or updates the daily hydration record for a given user and date.
     * Replaces the generalQuery(INSERT...) call in MainActivity to avoid crashes
     * (generalQuery uses executeQuery which fails on INSERT statements).
     * @param userId account id
     * @param date   date string
     * @param totalDrank total water drank today in litres
     * @return rows affected, or -1 on error
     */
    public int upsertDailyHydration(int userId, String date, double totalDrank) {
        if (!isConnected()) return -1;
        String query = "INSERT INTO daily_hydration (account, date, total_drank) VALUES (?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE total_drank = ?;";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setString(2, date);
            ps.setDouble(3, totalDrank);
            ps.setDouble(4, totalDrank);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
}
