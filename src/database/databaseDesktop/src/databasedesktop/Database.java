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
    
    public String getUserFromId(int id) {
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
    
    public String getEmailFromUser(int id) {
        Statement sta;
        String result = "";
        
        try {
            sta = conn1.createStatement();
            ResultSet rs = sta.executeQuery("SELECT email FROM cuenta WHERE id = "+id+";");
            
            if (rs.next()) {
                result = rs.getString(1);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        
        return result;
        
    }
    
    public String getPasswordFromUser(int id) {
        Statement sta;
        String result = "";
        
        try {
            sta = conn1.createStatement();
            ResultSet rs = sta.executeQuery("SELECT contrasena FROM cuenta WHERE id = "+id+";");
            
            if (rs.next()) {
                result = rs.getString(1);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        
        return result;
        
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
    }
    
    
    public int deleteUser(int id) {
        Statement sta;
        int returnCode = 0;
        
        try {
            sta = conn1.createStatement();
            returnCode = sta.executeUpdate("DELETE FROM cuenta WHERE id = "+id+";");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return -1;
        }
        
        return returnCode;
    }
    public int deleteBags(int id) {
        Statement sta;
        int returnCode = 0;
        
        try {
            sta = conn1.createStatement();
            returnCode = sta.executeUpdate("DELETE FROM bolso WHERE id = "+id+";");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            return -1;
        }
        
        return returnCode;
    }
    public int insertUser(Object[] userdata) {
        Statement sta;
        int returnCode = 0;
        
        try {
            sta = conn1.createStatement();
            System.out.println("INSERT INTO cuenta VALUES("+userdata[0]+",'"+userdata[1]+"','"+userdata[2]+"','"+userdata[3]+"','"+userdata[4]+"','"+userdata[5]+"','"+userdata[6]+"',"+userdata[7]+","+userdata[8]+",'"+userdata[9]+"',"+userdata[10]+", NULL)");
            returnCode = sta.executeUpdate("INSERT INTO cuenta VALUES("+userdata[0]+",'"+userdata[1]+"','"+userdata[2]+"','"+userdata[3]+"','"+userdata[4]+"','"+userdata[5]+"','"+userdata[6]+"',"+userdata[7]+","+userdata[8]+",'"+userdata[9]+"',"+userdata[10]+", NULL)");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            returnCode = -1;
        }
        
        return returnCode;
    }
    public int insertBags(Object[] userdata) {
        Statement sta;
        int returnCode = 0;
        
        try {
            sta = conn1.createStatement();
            System.out.println("INSERT INTO bolso VALUES("+userdata[0]+",'"+userdata[1]+"','"+userdata[2]+"','"+ "NULL" +"','"+userdata[3] + ")" );
            returnCode = sta.executeUpdate("INSERT INTO bolso VALUES("+userdata[0]+",'"+userdata[1]+"','"+userdata[2]+"',"+ "NULL" +","+userdata[3]+ ")");
        } catch (SQLException ex) {
            System.out.println(ex.toString());
            returnCode = -1;
        }
        
        return returnCode;
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
    public ResultSet getEventsFromBags(int id) {
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
}
