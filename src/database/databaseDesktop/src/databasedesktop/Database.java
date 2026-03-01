package databasedesktop;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    Connection conn1 = null;
    
    public Database() {
        
        String url = "jdbc:mysql://smiguels.net:3306/serious_game";
        String user="serious";
        String pass="game";
        
        try {
            conn1 = DriverManager.getConnection(url, user, pass);
            if(conn1 != null) {
                System.out.println("Conectado a serious_game");
            }
        } catch (SQLException ex) {
            System.out.println("Error!! conectando con la base de datos");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Integer insertarAlumno(String id, String nombre, String apellido, String email) {
        Statement sta;
        try {
            sta = conn1.createStatement();
            sta.executeUpdate("INSERT INTO alumno VALUES('"+id+"', '"+nombre+"', '"+apellido+"', '"+email+"', NULL, NULL, NULL);");
            return 0;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return -1;
        }
    }
    
    public ResultSet selectTest(int id) {
        Statement sta;
        try {
            sta = conn1.createStatement();
            ResultSet rs = sta.executeQuery("SELECT * FROM cuenta WHERE '"+id+"' = 1");
            
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    
    public ResultSet generateList(int id) {
        Statement sta;
        try {
            sta = conn1.createStatement();
            ResultSet rs = sta.executeQuery("SELECT * FROM cuenta WHERE '"+id+"' = 1");
            
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    
    public int getAmountUsers() {
        Statement sta;
        int result = 0;
        
        try {
            sta = conn1.createStatement();
            ResultSet rs = sta.executeQuery("SELECT COUNT(id) FROM cuenta;");
            
            if (rs.next()) {
                result =  Integer.parseInt(rs.getString(1));
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        
        return result;
        
    }
    
    public String getUsersFromId(int id) {
        Statement sta;
        String result = "";
        
        try {
            sta = conn1.createStatement();
            ResultSet rs = sta.executeQuery("SELECT nombre FROM cuenta WHERE id = "+id+";");
            
            if (rs.next()) {
                result = rs.getString(1);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        
        return result;
        
    }
   
}
