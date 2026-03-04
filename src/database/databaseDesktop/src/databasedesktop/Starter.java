/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package databasedesktop;

/**
 *
 * @author LABORATORIOS
 */
public class Starter {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

            LoginDialog login = new LoginDialog(null, true);
            login.setLocationRelativeTo(null);
            login.setTitle("Login - Opi");

            login.setVisible(true);

            if (login.isLogged()) {
                MainWindow vpp = new MainWindow();
                vpp.setLocationRelativeTo(null);
                vpp.setVisible(true);
            } else {
                System.exit(0);
            }

    }      
}         
