package databasedesktop;

import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author LABORATORIOS
 */

public class LoginDialog extends javax.swing.JDialog {
    private boolean logged = false;
    Database gestorBBDD;
    public MainWindow mw; 
    /**
     * Creates new form LoginDialog
     */
    public LoginDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        mw = (MainWindow)parent;
        initComponents();
         getContentPane().setBackground(new Color(21, 119, 216));
    ImageIcon icono = new ImageIcon(getClass().getResource("/img/iconOPI.png"));
    Image imagen = icono.getImage();
    Image imagenEscalada = imagen.getScaledInstance(
        jLabelLogo.getWidth(),
        jLabelLogo.getHeight(),
        Image.SCALE_SMOOTH
    );
    jLabelLogo.setIcon(new ImageIcon(imagenEscalada));
    }
private void iniciarSesion() {

        this.gestorBBDD = mw.db;


        String email = jTextFieldEmail.getText().trim();
        String password = jPasswordField.getText().trim();
        int identifier = gestorBBDD.getIdFromUser(email);

        String emailUser = gestorBBDD.getEmailFromUser(identifier);
        String passwordUser = gestorBBDD.getPasswordFromUser(identifier);

        System.out.println(gestorBBDD.getDatum("cuenta", "email", identifier));

        if(email.equals(emailUser) && password.equals(passwordUser)){
            JOptionPane.showMessageDialog(this, "Se ha logueado correctamente");
            logged = true;
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Datos incorrectos");
        }

        mw.currentUser = gestorBBDD.getIdFromUser(email);
}
    public boolean isLogged() {
        return logged;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelLogo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabelLogin = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jPasswordField = new javax.swing.JPasswordField();
        jLabelPassword = new javax.swing.JLabel();
        jButtonLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 255));

        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconOPI.png"))); // NOI18N
        jLabelLogo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabelLogo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabelLogin.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabelLogin.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLogin.setText("INICIAR SESIÓN");

        jLabelEmail.setForeground(new java.awt.Color(255, 255, 255));
        jLabelEmail.setText("EMAIL:");

        jTextFieldEmail.setForeground(new java.awt.Color(21, 119, 216));
        jTextFieldEmail.setText("\n");
        jTextFieldEmail.setSelectionColor(new java.awt.Color(255, 255, 255));
        jTextFieldEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEmailActionPerformed(evt);
            }
        });

        jLabelPassword.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPassword.setText("CONTRASEÑA:");

        jButtonLogin.setBackground(new java.awt.Color(2, 39, 172));
        jButtonLogin.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLogin.setText("INICIAR SESIÓN");
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jButtonLogin)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelPassword)
                            .addComponent(jLabelEmail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelLogin)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jLabelPassword))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43)
                .addComponent(jButtonLogin)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEmailActionPerformed

    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed
        iniciarSesion();
    }//GEN-LAST:event_jButtonLoginActionPerformed

    public static void main(String args[]) {

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LoginDialog dialog = new LoginDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelLogin;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelPassword;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldEmail;
    // End of variables declaration//GEN-END:variables
}
