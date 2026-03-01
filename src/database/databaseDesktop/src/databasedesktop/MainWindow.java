package databasedesktop;
import java.awt.Dimension;
import java.sql.*;
import javax.swing.JFrame;

public class MainWindow extends javax.swing.JFrame {
    
    Database db = null;
    
    public MainWindow() {
        initComponents();
        initWindow();
    }

    private void initWindow(){
        this.setSize(1280,720);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        connectToDatabase();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        idLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();
        heightLabel = new javax.swing.JLabel();
        birthLabel = new javax.swing.JLabel();
        waterLabel = new javax.swing.JLabel();
        loginLabel = new javax.swing.JLabel();
        loginTF = new javax.swing.JTextField();
        weightLabel = new javax.swing.JLabel();
        idTF = new javax.swing.JTextField();
        weightTF = new javax.swing.JTextField();
        birthTF = new javax.swing.JTextField();
        heightTF = new javax.swing.JTextField();
        waterTF = new javax.swing.JTextField();
        nameTF = new javax.swing.JTextField();
        phoneTF = new javax.swing.JTextField();
        bagButton = new javax.swing.JButton();
        eventsButton = new javax.swing.JButton();
        starsButton = new javax.swing.JButton();
        resultsWindow = new javax.swing.JScrollPane();
        results = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        conStatus = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1920, 1080));

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

        starsButton.setText("Recuento");
        starsButton.addActionListener(this::starsButtonActionPerformed);

        results.setColumns(20);
        results.setRows(5);
        resultsWindow.setViewportView(results);

        jLabel1.setText("DATOS");

        jMenuBar1.add(conStatus);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(nameLabel)
                                .addGap(18, 18, 18)
                                .addComponent(nameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(waterLabel)
                                .addGap(18, 18, 18)
                                .addComponent(waterTF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(heightLabel)
                                .addGap(18, 18, 18)
                                .addComponent(heightTF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(phoneLabel)
                                .addGap(18, 18, 18)
                                .addComponent(phoneTF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(weightLabel)
                                        .addGap(18, 18, 18)
                                        .addComponent(weightTF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(birthLabel)
                                        .addGap(18, 18, 18)
                                        .addComponent(birthTF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(144, 144, 144)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(loginLabel)
                            .addComponent(idLabel)))
                    .addComponent(resultsWindow, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(eventsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idTF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginTF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(starsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bagButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(754, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneLabel)
                    .addComponent(weightLabel)
                    .addComponent(waterLabel)
                    .addComponent(idLabel)
                    .addComponent(idTF, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(weightTF, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(waterTF, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneTF, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(heightLabel)
                    .addComponent(birthLabel)
                    .addComponent(loginLabel)
                    .addComponent(loginTF, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(birthTF, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(heightTF, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bagButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(eventsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(starsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resultsWindow, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(561, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void starsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_starsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_starsButtonActionPerformed

    private void eventsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventsButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eventsButtonActionPerformed

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
    private javax.swing.JButton bagButton;
    private javax.swing.JLabel birthLabel;
    private javax.swing.JTextField birthTF;
    private javax.swing.JMenu conStatus;
    private javax.swing.JButton eventsButton;
    private javax.swing.JLabel heightLabel;
    private javax.swing.JTextField heightTF;
    private javax.swing.JLabel idLabel;
    private javax.swing.JTextField idTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JTextField loginTF;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTF;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JTextField phoneTF;
    private javax.swing.JTextArea results;
    private javax.swing.JScrollPane resultsWindow;
    private javax.swing.JButton starsButton;
    private javax.swing.JLabel waterLabel;
    private javax.swing.JTextField waterTF;
    private javax.swing.JLabel weightLabel;
    private javax.swing.JTextField weightTF;
    // End of variables declaration//GEN-END:variables
}
