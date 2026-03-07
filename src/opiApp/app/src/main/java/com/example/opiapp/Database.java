package com.example.opiapp;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    Connection conn1 = null;

    public Database() {

        String url = "jdbc:mysql://smiguels.net:3306/opi_backend";
        String user = "opi_code";
        String pass = "admin";

        try {
            conn1 = DriverManager.getConnection(url, user, pass);
            if (conn1 != null) {
                System.out.println("Conectado a backend opi");
            }
        } catch (SQLException ex) {
            System.out.println("Error!! conectando con la base de datos");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

