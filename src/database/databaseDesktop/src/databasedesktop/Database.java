/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package databasedesktop;
import java.sql.*;
/**
 *
 * @author LABORATORIOS
 */
public class Database{
    public String url = null;
    public String user= null;
    public String pswd = null;
    public Database(){ 
        url = "jdbc:mysql://localhost:3305/serious";
        user= "root";
        pswd = "root";
    }
    static Connection createConnection(String url, String user, String pswd){
    Connection con1 = null;
    try{
        con1 = DriverManager.getConnection(url, user, pswd);
        if(con1!=null){
            System.out.println("Conexion correcta");
        }
    }catch(SQLException ex){
        return null;        
    }
    return con1;
}
}
