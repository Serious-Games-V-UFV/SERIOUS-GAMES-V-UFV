package databasedesktop;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MainWindow extends javax.swing.JFrame {
    
    int currentUser = 0;
    Database db = null;
    
    public MainWindow() {
        initComponents();
        initWindow();
        
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/iconOPI.png"));
        Image img = icon.getImage();
        Image imgEscalada = img.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
        ImageIcon iconPequeno = new ImageIcon(imgEscalada);
        jLabel2.setIcon(iconPequeno);
    }

    private void initWindow(){
        this.setSize(1280,520);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        jTableEventos.setDefaultEditor(Object.class, null);
        jTableEventos.setSelectionMode(0);
        try{
            connectToDatabase();
            resultSetToTableModel(db.getAllEvents(), this.jTableEventos);
        }catch(SQLException e){
            System.err.println("");
        }
        jTableRecuentoDiario.setDefaultEditor(Object.class, null);
        jTableRecuentoDiario.setSelectionMode(0);
        try{
            connectToDatabase();
            resultSetToTableModel(db.getAllDailyCount(), this.jTableRecuentoDiario);
        }catch(SQLException e){
            System.err.println("");
        }
       // connectToDatabase();
        /*try {
            resultsetToTableModel(db.rsTest(1));
        } catch (SQLException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelUsuario = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanelBolso = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanelEventos = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableEventos = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldEventos = new javax.swing.JTextField();
        jButtonBuscarEventos = new javax.swing.JButton();
        jPanelRecuentoDiario = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableRecuentoDiario = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldRecuentoDiarioID = new javax.swing.JTextField();
        jButtonBuscarRecuentoDiario = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanelUsuarioLayout = new javax.swing.GroupLayout(jPanelUsuario);
        jPanelUsuario.setLayout(jPanelUsuarioLayout);
        jPanelUsuarioLayout.setHorizontalGroup(
            jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
        );
        jPanelUsuarioLayout.setVerticalGroup(
            jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUsuarioLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
                .addContainerGap())
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
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
        );
        jPanelBolsoLayout.setVerticalGroup(
            jPanelBolsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBolsoLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Bolso", jPanelBolso);

        jTableEventos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "tipo", "dato", "fecha", "id_bolso"
            }
        ));
        jScrollPane4.setViewportView(jTableEventos);

        jLabel4.setText("ID:");

        jButtonBuscarEventos.setText("Buscar");
        jButtonBuscarEventos.addActionListener(this::jButtonBuscarEventosActionPerformed);

        javax.swing.GroupLayout jPanelEventosLayout = new javax.swing.GroupLayout(jPanelEventos);
        jPanelEventos.setLayout(jPanelEventosLayout);
        jPanelEventosLayout.setHorizontalGroup(
            jPanelEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEventosLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelEventosLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldEventos, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelEventosLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jButtonBuscarEventos)))
                .addGap(0, 30, Short.MAX_VALUE))
        );
        jPanelEventosLayout.setVerticalGroup(
            jPanelEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEventosLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanelEventosLayout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanelEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldEventos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(153, 153, 153)
                .addComponent(jButtonBuscarEventos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Eventos", jPanelEventos);

        jTableRecuentoDiario.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(jTableRecuentoDiario);

        jLabel3.setText("ID:");

        jButtonBuscarRecuentoDiario.setText("Buscar");
        jButtonBuscarRecuentoDiario.addActionListener(this::jButtonBuscarRecuentoDiarioActionPerformed);

        javax.swing.GroupLayout jPanelRecuentoDiarioLayout = new javax.swing.GroupLayout(jPanelRecuentoDiario);
        jPanelRecuentoDiario.setLayout(jPanelRecuentoDiarioLayout);
        jPanelRecuentoDiarioLayout.setHorizontalGroup(
            jPanelRecuentoDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRecuentoDiarioLayout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelRecuentoDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRecuentoDiarioLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldRecuentoDiarioID, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelRecuentoDiarioLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jButtonBuscarRecuentoDiario)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanelRecuentoDiarioLayout.setVerticalGroup(
            jPanelRecuentoDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRecuentoDiarioLayout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanelRecuentoDiarioLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanelRecuentoDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldRecuentoDiarioID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(153, 153, 153)
                .addComponent(jButtonBuscarRecuentoDiario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Recuento diario", jPanelRecuentoDiario);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 165, 0));
        jLabel1.setText("OPI");
        jLabel1.setToolTipText("");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconOPI.png"))); // NOI18N
        jLabel2.setMaximumSize(new java.awt.Dimension(179, 169));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBuscarRecuentoDiarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarRecuentoDiarioActionPerformed
        try{
            resultSetToTableModel(db.getDailyCountFromUser(Integer.parseInt(jTextFieldRecuentoDiarioID.getText())), this.jTableRecuentoDiario);
        }catch(SQLException e){
            System.err.println("ERROR en jButtonBuscar");
        }
    }//GEN-LAST:event_jButtonBuscarRecuentoDiarioActionPerformed

    private void jButtonBuscarEventosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarEventosActionPerformed
        try{
            resultSetToTableModel(db.getEventsFromUser(Integer.parseInt(jTextFieldEventos.getText())), this.jTableEventos);
            if (jTextFieldEventos.getText() == null){
                resultSetToTableModel(db.getAllEvents(), this.jTableEventos);
            }else{
                resultSetToTableModel(db.getEventsFromUser(Integer.parseInt(jTextFieldEventos.getText())), this.jTableEventos);
            }
        }catch(SQLException e){
            System.err.println("ERROR en jButtonBuscar");
        }
    }//GEN-LAST:event_jButtonBuscarEventosActionPerformed
    
    
    
    private void resultsetToTableModel(ResultSet rs) throws SQLException{
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
        jTable1.setModel(tbm);
    }
    private void resultSetToTableModel(ResultSet rs, JTable jt) throws SQLException{
        DefaultTableModel tbm = new DefaultTableModel();
        ResultSetMetaData metaData = rs.getMetaData();
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
    
    public void cargarEventos() {
   
    DefaultTableModel modelo = (DefaultTableModel) jTableEventos.getModel();
    
    modelo.setRowCount(0);
    
    try {
        
        Database db = new Database();
        ResultSet rs = db.getAllEvents();
        
        while (rs.next()) {
            Object[] fila = new Object[3]; // Ajusta el tamaño según tus columnas
            fila[0] = rs.getInt("id");
            fila[1] = rs.getString("tipo");
            fila[2] = rs.getInt("dato");
            fila[3] = rs.getDate("fecha");
            fila[4] = rs.getInt("id_bolso");
            
            modelo.addRow(fila);
        }
    } catch (SQLException e) {
        System.out.println("Error al cargar tabla: " + e.getMessage());
    }
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
    private javax.swing.JButton jButtonBuscarEventos;
    private javax.swing.JButton jButtonBuscarRecuentoDiario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanelBolso;
    private javax.swing.JPanel jPanelEventos;
    private javax.swing.JPanel jPanelRecuentoDiario;
    private javax.swing.JPanel jPanelUsuario;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTableEventos;
    private javax.swing.JTable jTableRecuentoDiario;
    private javax.swing.JTextField jTextFieldEventos;
    private javax.swing.JTextField jTextFieldRecuentoDiarioID;
    // End of variables declaration//GEN-END:variables

    private void eventsButtonActionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void starsButtonActionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
