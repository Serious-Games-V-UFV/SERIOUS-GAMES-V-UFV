package com.example.opiapp;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    // Attributes
    Connection con = null;


    // Constructor
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

    // Methods

    /**
     * This method return a specific piece of data
     * from a specific table and column.
     *
     * @param table Tells the table name
     * @param column_name Tells the column name
     * @param id Tells the user whose datum will be returned
     * @return Will return a String with the datum or "" if the
     */
    public String getDatum(String table, String column_name,int id) {
        Statement sta;
        String datum = "";
        try {
            sta = con.createStatement();
            ResultSet rs = sta.executeQuery("SELECT "+column_name+" FROM "+table+" WHERE id = "+id+";");

            if (rs.next()) {
                datum = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return datum;
    }

    public String query(String query) {
        Statement sta;
        String datum = "";
        try {
            sta = con.createStatement();
            ResultSet rs = sta.executeQuery(query);

            if (rs.next()) {
                datum = rs.getString(1);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return datum;
    }
}
