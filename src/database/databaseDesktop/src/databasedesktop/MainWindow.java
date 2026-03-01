package databasedesktop;
import java.awt.Dimension;
import java.sql.*;
import javax.swing.JFrame;

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

        jMenuItem1 = new javax.swing.JMenuItem();
        jLabelStatus = new javax.swing.JLabel();
        jButtonTest = new javax.swing.JButton();
        jScrollPaneTest = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        idLabel.setText("ID usuario");

        nameLabel.setText("Nombre");

        phoneLabel.setText("Teléfono");

        heightLabel.setText("Altura");

        birthLabel.setText("Fecha nacimiento");

        waterLabel.setText("Agua deseada");

        loginLabel.setText("Ultimo login");

        loginTF.setText(" ");
        loginTF.setMaximumSize(new java.awt.Dimension(100, 16));

        weightLabel.setText("Peso");

        idTF.setText(" ");
        idTF.setMaximumSize(new java.awt.Dimension(100, 16));

        weightTF.setText(" ");
        weightTF.setMaximumSize(new java.awt.Dimension(100, 16));

        birthTF.setText(" ");
        birthTF.setMaximumSize(new java.awt.Dimension(100, 16));

        heightTF.setText(" ");
        heightTF.setMaximumSize(new java.awt.Dimension(100, 16));

        waterTF.setMaximumSize(new java.awt.Dimension(100, 16));

        nameTF.setText(" ");
        nameTF.setMaximumSize(new java.awt.Dimension(100, 16));

        phoneTF.setText(" ");
        phoneTF.setMaximumSize(new java.awt.Dimension(100, 16));

        bagButton.setText("Bolso");

        eventsButton.setText("Eventos");
        eventsButton.addActionListener(this::eventsButtonActionPerformed);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPaneTest.setViewportView(jTextArea1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonTest)
                    .addComponent(jScrollPaneTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelStatus))
                .addContainerGap(488, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabelStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addComponent(jButtonTest)
                .addGap(58, 58, 58)
                .addComponent(jScrollPaneTest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(158, 158, 158))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTestActionPerformed
        db.selectTest(1);
    }//GEN-LAST:event_jButtonTestActionPerformed

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonTest;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPaneTest;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
