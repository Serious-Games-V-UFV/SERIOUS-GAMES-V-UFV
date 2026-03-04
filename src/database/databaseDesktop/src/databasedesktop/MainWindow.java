package databasedesktop;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MainWindow extends javax.swing.JFrame {
    
    int currentUser = 0;
    Database db = null;
    
    public MainWindow() {
        initComponents();
        initWindow();
        // ImageIcon icono = new ImageIcon(getClass().getResource("img/iconOPI.png"));
        // jLabel2.setIcon(icono);
    }

    private void initWindow(){
        //this.setSize(1280,720);
        this.setLocationRelativeTo(null);
        //this.setResizable(false);
        jTableAdUser.setDefaultEditor(Object.class, null);
        jTableAdUser.setSelectionMode(0);
        try {
            connectToDatabase();
            resultSetToTableModel(db.getAllUsers(), this.jTableAdUser);
            
        } catch (SQLException ex) {
            System.err.println("Couldn't fill table");
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelUsuario = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAdUser = new javax.swing.JTable();
        jTextFieldAdId = new javax.swing.JTextField();
        jTextFieldAdName = new javax.swing.JTextField();
        jTextFieldAdSurname1 = new javax.swing.JTextField();
        jTextFieldAdSurname2 = new javax.swing.JTextField();
        jTextFieldAdPhone = new javax.swing.JTextField();
        jTextFieldAdEmail = new javax.swing.JTextField();
        jTextFieldAdPassword = new javax.swing.JTextField();
        jTextFieldAdHeight = new javax.swing.JTextField();
        jTextFieldAdWeight = new javax.swing.JTextField();
        jTextFieldAdBirth = new javax.swing.JTextField();
        jTextFieldAdWater = new javax.swing.JTextField();
        jLabelAdId = new javax.swing.JLabel();
        jLabelAdName = new javax.swing.JLabel();
        jLabelAdSurname1 = new javax.swing.JLabel();
        jLabelAdSurname2 = new javax.swing.JLabel();
        jLabelAdPhone = new javax.swing.JLabel();
        jLabelAdEmail = new javax.swing.JLabel();
        jLabelAdPassword = new javax.swing.JLabel();
        jLabelAdHeight = new javax.swing.JLabel();
        jLabelAdWeight = new javax.swing.JLabel();
        jLabelAdBirth = new javax.swing.JLabel();
        jLabelAdWater = new javax.swing.JLabel();
        jButtonAdSearch = new javax.swing.JButton();
        jButtonAdInsert = new javax.swing.JButton();
        jButtonAdDelete = new javax.swing.JButton();
        jPanelBolso = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanelEventos = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanelRecuentoDiario = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableAdUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableAdUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAdUserMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableAdUser);

        jLabelAdId.setText("ID:");

        jLabelAdName.setText("Nombre:");

        jLabelAdSurname1.setText("Apellido 1:");

        jLabelAdSurname2.setText("Apellido 2:");

        jLabelAdPhone.setText("Teléfono");

        jLabelAdEmail.setText("Email");

        jLabelAdPassword.setText("Contraseña");

        jLabelAdHeight.setText("Altura");

        jLabelAdWeight.setText("Peso");

        jLabelAdBirth.setText("Fecha nacimiento");

        jLabelAdWater.setText("Agua deseada");

        jButtonAdSearch.setText("Buscar");
        jButtonAdSearch.addActionListener(this::jButtonAdSearchActionPerformed);

        jButtonAdInsert.setText("Insertar");
        jButtonAdInsert.addActionListener(this::jButtonAdInsertActionPerformed);

        jButtonAdDelete.setText("Borrar");
        jButtonAdDelete.addActionListener(this::jButtonAdDeleteActionPerformed);

        javax.swing.GroupLayout jPanelUsuarioLayout = new javax.swing.GroupLayout(jPanelUsuario);
        jPanelUsuario.setLayout(jPanelUsuarioLayout);
        jPanelUsuarioLayout.setHorizontalGroup(
            jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUsuarioLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabelAdId)
                        .addComponent(jLabelAdName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelAdSurname1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabelAdSurname2)
                    .addComponent(jLabelAdPhone)
                    .addComponent(jLabelAdEmail)
                    .addComponent(jLabelAdPassword)
                    .addComponent(jLabelAdHeight)
                    .addComponent(jLabelAdWeight)
                    .addComponent(jLabelAdBirth)
                    .addComponent(jLabelAdWater)
                    .addComponent(jButtonAdSearch))
                .addGroup(jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanelUsuarioLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jButtonAdInsert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(jButtonAdDelete))
                    .addGroup(jPanelUsuarioLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldAdId)
                            .addComponent(jTextFieldAdName)
                            .addComponent(jTextFieldAdSurname1)
                            .addComponent(jTextFieldAdSurname2)
                            .addComponent(jTextFieldAdPhone)
                            .addComponent(jTextFieldAdEmail)
                            .addComponent(jTextFieldAdHeight)
                            .addComponent(jTextFieldAdWeight)
                            .addComponent(jTextFieldAdBirth)
                            .addComponent(jTextFieldAdWater, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(jTextFieldAdPassword))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanelUsuarioLayout.setVerticalGroup(
            jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldAdId)
                    .addComponent(jLabelAdId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldAdName)
                    .addComponent(jLabelAdName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAdSurname1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAdSurname1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAdSurname2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAdSurname2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAdPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAdPhone))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAdEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAdEmail))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAdPassword)
                    .addComponent(jTextFieldAdPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAdHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAdHeight))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAdWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAdWeight))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAdBirth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAdBirth))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAdWater, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAdWater))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                .addGroup(jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdSearch)
                    .addComponent(jButtonAdInsert)
                    .addComponent(jButtonAdDelete))
                .addContainerGap())
            .addComponent(jScrollPane1)
        );

        jTabbedPane1.addTab("Usuario", jPanelUsuario);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanelBolsoLayout = new javax.swing.GroupLayout(jPanelBolso);
        jPanelBolso.setLayout(jPanelBolsoLayout);
        jPanelBolsoLayout.setHorizontalGroup(
            jPanelBolsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1038, Short.MAX_VALUE)
        );
        jPanelBolsoLayout.setVerticalGroup(
            jPanelBolsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBolsoLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Bolso", jPanelBolso);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(jTable4);

        javax.swing.GroupLayout jPanelEventosLayout = new javax.swing.GroupLayout(jPanelEventos);
        jPanelEventos.setLayout(jPanelEventosLayout);
        jPanelEventosLayout.setHorizontalGroup(
            jPanelEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1038, Short.MAX_VALUE)
        );
        jPanelEventosLayout.setVerticalGroup(
            jPanelEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEventosLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Eventos", jPanelEventos);

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(jTable5);

        javax.swing.GroupLayout jPanelRecuentoDiarioLayout = new javax.swing.GroupLayout(jPanelRecuentoDiario);
        jPanelRecuentoDiario.setLayout(jPanelRecuentoDiarioLayout);
        jPanelRecuentoDiarioLayout.setHorizontalGroup(
            jPanelRecuentoDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1038, Short.MAX_VALUE)
        );
        jPanelRecuentoDiarioLayout.setVerticalGroup(
            jPanelRecuentoDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRecuentoDiarioLayout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Recuento diario", jPanelRecuentoDiario);

        jLabel1.setText("OPI");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconOPI.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(63, 63, 63)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAdSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdSearchActionPerformed
        try {
            if (jTextFieldAdId.getText().isBlank() == false) {
                resultSetToTableModel(db.getUser(Integer.parseInt(jTextFieldAdId.getText())), this.jTableAdUser);
            } else {
                resultSetToTableModel(db.getAllUsers(), this.jTableAdUser);
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }//GEN-LAST:event_jButtonAdSearchActionPerformed

    private void jTableAdUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableAdUserMouseClicked
        if (jTableAdUser.getSelectedRow() != -1) {
            try {
                resultSetToTableModel(db.getUser((Integer)jTableAdUser.getValueAt(jTableAdUser.getSelectedRow(), 0)), this.jTableAdUser);
            } catch (SQLException ex) {
                System.err.println(ex.toString());
            }
        }
    }//GEN-LAST:event_jTableAdUserMouseClicked

    private void jButtonAdDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdDeleteActionPerformed
        int successCode;
        int confirmMessage;
        
        confirmMessage = JOptionPane.showConfirmDialog(this, "¿Quieres borrar este usuario?", "Delete user", JOptionPane.YES_NO_OPTION);
        
        if (confirmMessage == 0) {
            successCode = db.deleteUser(Integer.parseInt(jTextFieldAdId.getText()));    
        }
        
    }//GEN-LAST:event_jButtonAdDeleteActionPerformed

    private void jButtonAdInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdInsertActionPerformed
        Object[] userdata = new Object[jTableAdUser.getColumnCount()];
        
        userdata[0] = Integer.valueOf(jTextFieldAdId.getText());
        userdata[1] = jTextFieldAdName.getText();
        userdata[2] = jTextFieldAdSurname1.getText();
        userdata[3] = jTextFieldAdSurname2.getText();
        userdata[4] = jTextFieldAdPhone.getText();
        userdata[5] = jTextFieldAdEmail.getText();
        userdata[6] = jTextFieldAdPassword.getText();
        userdata[7] = Integer.valueOf(jTextFieldAdHeight.getText());
        userdata[8] = Integer.valueOf(jTextFieldAdWeight.getText());
        userdata[9] = jTextFieldAdBirth.getText();
        userdata[10] = Integer.valueOf(jTextFieldAdWater.getText());
        
        db.insertUser(userdata);
    }//GEN-LAST:event_jButtonAdInsertActionPerformed

    
    private void resultSetToTableModel(ResultSet rs, JTable jt) throws SQLException{
        DefaultTableModel tbm = new DefaultTableModel();
        ResultSetMetaData metaData =  rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        for ( int i = 1; i <= columnCount; i++){
            tbm.addColumn(metaData.getColumnLabel(i));
        }
        
        Object[] row = new Object[columnCount];
        
        while(rs.next()){
            for ( int i = 0; i<columnCount;i++){
                row[i] = rs.getObject(i+1);
            }
            tbm.addRow(row);
        }
        jt.setModel(tbm);
    }

    public void connectToDatabase() {
        db = new Database();          
    } 
   
        
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() -> {
            new MainWindow().setVisible(true);
        });
        
    }
    /*
    public  void setStatus(Connection con1) {
        if(con1 != null){
            conStatus.setText("Conectado");
        }else{
            conStatus.setText("Error en la conexión");
        }
    }
*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdDelete;
    private javax.swing.JButton jButtonAdInsert;
    private javax.swing.JButton jButtonAdSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelAdBirth;
    private javax.swing.JLabel jLabelAdEmail;
    private javax.swing.JLabel jLabelAdHeight;
    private javax.swing.JLabel jLabelAdId;
    private javax.swing.JLabel jLabelAdName;
    private javax.swing.JLabel jLabelAdPassword;
    private javax.swing.JLabel jLabelAdPhone;
    private javax.swing.JLabel jLabelAdSurname1;
    private javax.swing.JLabel jLabelAdSurname2;
    private javax.swing.JLabel jLabelAdWater;
    private javax.swing.JLabel jLabelAdWeight;
    private javax.swing.JPanel jPanelBolso;
    private javax.swing.JPanel jPanelEventos;
    private javax.swing.JPanel jPanelRecuentoDiario;
    private javax.swing.JPanel jPanelUsuario;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTableAdUser;
    private javax.swing.JTextField jTextFieldAdBirth;
    private javax.swing.JTextField jTextFieldAdEmail;
    private javax.swing.JTextField jTextFieldAdHeight;
    private javax.swing.JTextField jTextFieldAdId;
    private javax.swing.JTextField jTextFieldAdName;
    private javax.swing.JTextField jTextFieldAdPassword;
    private javax.swing.JTextField jTextFieldAdPhone;
    private javax.swing.JTextField jTextFieldAdSurname1;
    private javax.swing.JTextField jTextFieldAdSurname2;
    private javax.swing.JTextField jTextFieldAdWater;
    private javax.swing.JTextField jTextFieldAdWeight;
    // End of variables declaration//GEN-END:variables

    private void eventsButtonActionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void starsButtonActionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
