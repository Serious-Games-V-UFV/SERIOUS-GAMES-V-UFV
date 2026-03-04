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
    public ResultSet rsTest(int id){
        Statement sta;
        ResultSet result;
        
        try {
            sta = conn1.createStatement();
            result = sta.executeQuery("SELECT * FROM cuenta WHERE id = "+id+";");

         
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            result = null;
        }
        
        return result;
<<<<<<< HEAD
    }
   
<<<<<<< HEAD
    
    public ResultSet getAllUsers() {
        Statement sta;
        try {
            sta = conn1.createStatement();
            ResultSet rs = sta.executeQuery("SELECT * FROM cuenta");
            
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    
    
    public ResultSet getUser(int id) {
        Statement sta;
        try {
            sta = conn1.createStatement();
            ResultSet rs = sta.executeQuery("SELECT * FROM cuenta WHERE id = "+id+";");
            
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    
    
    public ResultSet getAllBags() {
        Statement sta;
        try {
            sta = conn1.createStatement();
            ResultSet rs = sta.executeQuery("SELECT * FROM bolso");
            
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    
    
    public ResultSet getBagsFromUser(int id) {
        Statement sta;
        try {
            sta = conn1.createStatement();
            ResultSet rs = sta.executeQuery("SELECT * FROM bolso WHERE id_cuenta = "+id+";");
            
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    
    
    public String getDatum(String table, String column_name,int id) {
        Statement sta;
        String datum = "";
        try {
            sta = conn1.createStatement();
            ResultSet rs = sta.executeQuery("SELECT "+column_name+" FROM "+table+" WHERE id = "+id+";");
            
            return datum;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return "";
        }
    }
    
    
    public int updateUserData(String column_name, int id) {
        Statement sta;
        int returnCode = 0;
        
        try {
            sta = conn1.createStatement();
            returnCode = sta.executeUpdate("UPDATE cuenta SET "+column_name+" = 0 WHERE id = "+id+";");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return -1;
        }
        
        return returnCode;
<<<<<<< HEAD
    }
    public ResultSet getAllEvents() {
        Statement sta;
        try {
            sta = conn1.createStatement();
            ResultSet rs = sta.executeQuery("SELECT * FROM evento");
            
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    public ResultSet getDailyCountFromUser(int id) {
        Statement sta;
        try {
            sta = conn1.createStatement();
            ResultSet rs = sta.executeQuery("SELECT * FROM recuerdo_diario WHERE id_cuenta = "+id+";");
            
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    public ResultSet getEventsFromUser(int id) {
        Statement sta;
        try {
            sta = conn1.createStatement();
            ResultSet rs = sta.executeQuery("SELECT * FROM evento WHERE id_bolso = "+id+";");
            
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
    public ResultSet getAllDailyCount() {
        Statement sta;
        try {
            sta = conn1.createStatement();
            ResultSet rs = sta.executeQuery("SELECT * FROM recuerdo_diario");
            
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return null;
        }
    }
   
    
=======
    }
>>>>>>> 562c41d29f3f7d049564b49e4eacee0c074fba72
=======
>>>>>>> parent of a3a1f05 (updated database desktop app)
=======
    }
    public String getEmail() {
    Statement sta;
    ResultSet rs = null;
    String resultado = "Error";
    String query = "SELECT email FROM serious_game.cuenta WHERE id = 1";

    try {
        sta = conn1.createStatement();
        rs = sta.executeQuery(query);

        if (rs.next()) {
            resultado = rs.getString("email");
        }

    } catch (SQLException ex) {
        System.out.println(ex.toString());
    }

    return resultado;
}
    public String getPassword(){
        Statement sta;
        ResultSet rs = null;
        String resultado = "";
        String query = "SELECT contrasena FROM serious_game.cuenta WHERE id = 1";
        try {
            sta = conn1.createStatement();
            rs = sta.executeQuery(query);
            if(rs.next()){
                resultado = rs.getString("contrasena");
            }
        }catch (SQLException ex){
            System.out.println(ex.toString());
        }
        return resultado;
    }
    public String obtenerNombre(int id){
        Statement sta;
        ResultSet rs = null;
        String resultado = "";
        String query = "SELECT nombre FROM serious_game.cuenta WHERE id = " + id;
        try {
            sta = conn1.createStatement();
            rs = sta.executeQuery(query);
            if(rs.next()){
                resultado = rs.getString("nombre");
            }
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }
        return resultado;
    }
   
>>>>>>> parent of 562c41d (Merge branch 'sw-develop' of https://github.com/Serious-Games-V-UFV/opi into sw-develop)
}
