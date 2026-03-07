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
        String query = null;
        try {
            sta = con.createStatement();
            ResultSet rs = sta.executeQuery("SELECT " + column_name + " FROM " + table + " WHERE id = " + id + ";");
            query = "SELECT " + column_name + " FROM " + table + " WHERE id = " + id + ";";
        }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        try {
            sta = con.prepareStatement(query);
            System.out.println(query);
            ResultSet rs = sta.executeQuery(query);

            if (rs.next()) {
                datum = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return datum;
    }

    /**
     * Executes a query inserted by the user
     * @param query query inserted by the user
     * @return datum asked by the query
     */
    public String query(String query) {
        Statement sta;
        String datum = "";
        try {
            sta = con.preparedStatement();
            ResultSet rs = sta.executeQuery(query);

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
     * @param table the table in which the specific piece of datum is inserted
     * @param column_name the column in which the specific piece of datum is inserted
     * @param value datum inserted
     * @return sta.executeUpdate() regarding database | -1 states an error
     */
    public int insertDatum(String table, String column_name, String value) {
        Statement sta;
        String query = "INSERT INTO "+table+" ("+column_name+") VALUES ('"+value+"');";
        try {
            sta = con.prepareStatement(query);
            System.out.println(query);
            return sta.executeUpdate(query);
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
     * @return sta.executeUpdate() regarding database | -1 states an error
     */
    public int updateDatum(String table, String column_name,String value,String id){
        Statement sta;
        String primary= null;
        String query = "UPDATE" +table+" "+ column_name + value+" WHERE " + id +"= id;";
        try {
            sta = con.prepareStatement(query);
            return sta.executeUpdate(query);
        } catch (SQLException e) {
            return -1;
        }
    }
}


