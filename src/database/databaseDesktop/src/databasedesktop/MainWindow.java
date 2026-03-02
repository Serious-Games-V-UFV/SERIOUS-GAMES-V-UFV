package databasedesktop;
import java.awt.event.ActionEvent;
import java.sql.*;

public class MainWindow extends javax.swing.JFrame {
    
    int currentUser = 0;
    Database db = null;
    
    public MainWindow() {
        initComponents();
        initWindow();
    }

    private void initWindow(){
        this.setSize(1280,520);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        connectToDatabase();
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        resultsWindow = new javax.swing.JScrollPane();
        results = new javax.swing.JTextArea();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenuCambiarUsuario = new javax.swing.JMenu();
        jMenuUsuario = new javax.swing.JMenu();
        jMenuBolso = new javax.swing.JMenu();
        jMenuEventos = new javax.swing.JMenu();
        jMenuRecuentoDiario = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        results.setColumns(20);
        results.setRows(5);
        resultsWindow.setViewportView(results);

        jMenuCambiarUsuario.setText("Cambiar Usuario");
        jMenuCambiarUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuCambiarUsuarioMouseClicked(evt);
            }
        });
        jMenuCambiarUsuario.addActionListener(this::jMenuCambiarUsuarioActionPerformed);
        jMenuBar2.add(jMenuCambiarUsuario);

        jMenuUsuario.setText("Usuario");
        jMenuUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuUsuarioMouseClicked(evt);
            }
        });
        jMenuBar2.add(jMenuUsuario);

        jMenuBolso.setText("Bolso");
        jMenuBolso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuBolsoMouseClicked(evt);
            }
        });
        jMenuBar2.add(jMenuBolso);

        jMenuEventos.setText("Eventos");
        jMenuEventos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuEventosMouseClicked(evt);
            }
        });
        jMenuBar2.add(jMenuEventos);

        jMenuRecuentoDiario.setText("Recuento Diario");
        jMenuRecuentoDiario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuRecuentoDiarioMouseClicked(evt);
            }
        });
        jMenuBar2.add(jMenuRecuentoDiario);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(resultsWindow, javax.swing.GroupLayout.PREFERRED_SIZE, 1122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 798, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(resultsWindow, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 578, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuCambiarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuCambiarUsuarioActionPerformed
        
    }//GEN-LAST:event_jMenuCambiarUsuarioActionPerformed

    private void jMenuCambiarUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuCambiarUsuarioMouseClicked
        results.setText("Cambio usuario");
    }//GEN-LAST:event_jMenuCambiarUsuarioMouseClicked

    private void jMenuUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuUsuarioMouseClicked
        results.setText("Usuario");
    }//GEN-LAST:event_jMenuUsuarioMouseClicked

    private void jMenuBolsoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBolsoMouseClicked
        results.setText("Bolso");
    }//GEN-LAST:event_jMenuBolsoMouseClicked

    private void jMenuEventosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuEventosMouseClicked
        results.setText("Eventos");
    }//GEN-LAST:event_jMenuEventosMouseClicked

    private void jMenuRecuentoDiarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuRecuentoDiarioMouseClicked
        results.setText("RecuentoDiario");
    }//GEN-LAST:event_jMenuRecuentoDiarioMouseClicked

    private void jButtonInsertActionPerformed(java.awt.event.ActionEvent evt){
        String [] insertion = insertionData();
        db.insert(insertion);
    }
    private void jButtonSelectActionPerformed(java.awt.event.ActionEvent evt){
        String selection = selectionData();
        String data = db.selection(selection);
        showData(data);
    }
   

    public void connectToDatabase() {
        db = new Database();
        
        if (db != null) {
            conStatus.setText("Conection created");
        } else {
            conStatus.setText("No Conection");
        }
    } 
     

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(() -> {
            new MainWindow().setVisible(true);
        
        });
    }
    
    
    public  void setStatus(Connection con1) {
        if(con1 != null){
            conStatus.setText("Conectado");
        }else{
            conStatus.setText("Error en la conexión");
        }
    }
    
    public String[] insertionData(){
        String id = null;
        String name = null;
        String surname1 = null;
        String surname2 = null;
        String phone = null;
        String email = null;
        String height = null;
        String weight = null;
        String birth = null;
        String water = null;
        String login = null;
        
        id = jTextFieldId.getText().trim();
        name = jTextFieldName.getText().trim();
        surname1 = jTextFieldSurname1.getText().trim();
        surname2 = jTextFieldSurname2.getText().trim();
        phone = jTextFieldPhone.getText().trim();
        email = jTextFieldEmail.getText().trim();
        height = jTextFieldHeight.getText().trim();
        weight = jTextFieldWeight.getText().trim();
        birth = jTextFieldBirth.getText().trim();
        water = jTextFieldWater.getText().trim();
        login = jTextFieldLogin.getText().trim();
        
        String[] insertion = new String[11];
        
        insertion[0] = id;
        insertion[1] = name;
        insertion[2] = surname1;
        insertion[3] = surname2;
        insertion[4] = phone;
        insertion[5] = email;
        insertion[6] = height;
        insertion[7] = weight;
        insertion[8] = birth;
        insertion[9] = water;
        insertion[10] = login;
        
        return insertion;
    }
    
    public String selectionData(){        
        return jTextFieldId.getText().trim();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenu jMenuBolso;
    private javax.swing.JMenu jMenuCambiarUsuario;
    private javax.swing.JMenu jMenuEventos;
    private javax.swing.JMenu jMenuRecuentoDiario;
    private javax.swing.JMenu jMenuUsuario;
    private javax.swing.JTextArea results;
    private javax.swing.JScrollPane resultsWindow;
    // End of variables declaration//GEN-END:variables

    
    private void eventsButtonActionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void starsButtonActionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private void showData(String data) {
        String[] info = data.split("\n");
        jTextFieldId.setText(info[0]);
        jTextFieldName.setText(info[1]);
        jTextFieldSurname1.setText(info[2]);
        jTextFieldSurname2.setText(info[3]);
        jTextFieldPhone.setText(info[4]);
        jTextFieldEmail.setText(info[5]);
        jTextFieldHeight.setText(info[6]);
        jTextFieldWeight.setText(info[7]);
        jTextFieldBirth.setText(info[8]);
        jTextFieldWater.setText(info[9]);
        jTextFieldLogin.setText(info[10]);
    }
    
}
