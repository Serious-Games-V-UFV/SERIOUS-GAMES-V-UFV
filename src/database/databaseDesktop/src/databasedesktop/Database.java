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
    
    public Integer insert(String[] data) {
        PreparedStatement ps;
        try {
            String sql = "INSERT INTO cuenta VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn1.prepareStatement(sql);
            
            for (int i = 0; i < data.length; i++) {
                ps.setString(i + 1, data[i]);
            }
            
            ps.executeUpdate();
            ps.close();
            return 0;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return -1;
        }
    }
    public String selection (String id){
         String sql = "SELECT id, nombre, apellido1, apellido2, telefono, email, " +
                 "altura, peso, fecha_nacimiento, agua_deseada, last_login " +
                 "FROM cuenta WHERE id = ?";
         PreparedStatement ps;
         ResultSet rs;
         StringBuilder sb = new StringBuilder();
         try{
             ps = conn1.prepareStatement(sql);
             ps.setInt(1,Integer.parseInt(id));
                 rs = ps.executeQuery();
                 if(rs.next()){
                        sb.append(rs.getInt("id")).append("\n");
                        sb.append(rs.getString("nombre")).append("\n");
                        sb.append(rs.getString("apellido1")).append("\n");
                        sb.append(rs.getString("apellido2")).append("\n");
                        sb.append(rs.getString("telefono")).append("\n");
                        sb.append(rs.getString("email")).append("\n");
                        sb.append(rs.getInt("altura")).append("\n");
                        sb.append(rs.getInt("peso")).append("\n");
                        sb.append(rs.getDate("fecha_nacimiento")).append("\n");
                        sb.append(rs.getInt("agua_deseada")).append("\n");
                        sb.append(rs.getDate("last_login")).append("\n");   
                 }else{
                     System.out.println("No existe la cuenta con id= " + id);
                 }
             }catch(SQLException ex){
                System.out.println("Error en la seleccion SQL" + ex.getMessage());
                    
         }
        return sb.toString();
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
