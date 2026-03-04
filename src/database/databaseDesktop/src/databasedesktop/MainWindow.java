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
        jTableAdBolso.setDefaultEditor(Object.class, null);
        jTableAdBolso.setSelectionMode(0);
        jTableAdEventos.setDefaultEditor(Object.class, null);
        jTableAdEventos.setSelectionMode(0);
        jTableAdRecuentoDiario.setDefaultEditor(Object.class, null);
        jTableAdRecuentoDiario.setSelectionMode(0);
        try {
            connectToDatabase();
            resultSetToTableModel(db.getAllUsers(), this.jTableAdUser);
            resultSetToTableModel(db.getAllBags(), this.jTableAdBolso);
            resultSetToTableModel(db.getAllBags(), this.jTableAdEventos);
            resultSetToTableModel(db.getAllBags(), this.jTableAdRecuentoDiario);
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
        jTextFieldAdIdUser = new javax.swing.JTextField();
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
        jButtonAdDeleteUser = new javax.swing.JButton();
        jPanelBolso = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableAdBolso = new javax.swing.JTable();
        jLabelAdId1 = new javax.swing.JLabel();
        jLabelAdName1 = new javax.swing.JLabel();
        jLabelAdSurname3 = new javax.swing.JLabel();
        jLabelAdSurname4 = new javax.swing.JLabel();
        jLabelAdPhone1 = new javax.swing.JLabel();
        jTextFieldAdIdBag = new javax.swing.JTextField();
        jTextFieldAdTipoBag = new javax.swing.JTextField();
        jTextFieldAdColorBag = new javax.swing.JTextField();
        jTextFieldAdPrimeraConexionBag = new javax.swing.JTextField();
        jTextFieldAdIdCuentaBag = new javax.swing.JTextField();
        jButtonAdSearchBags = new javax.swing.JButton();
        jButtonAdInsert1 = new javax.swing.JButton();
        jButtonAdDeleteBags = new javax.swing.JButton();
        jPanelEventos = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableAdEventos = new javax.swing.JTable();
        jLabelAdId3 = new javax.swing.JLabel();
        jTextFieldAdIdEvent = new javax.swing.JTextField();
        jButtonAdSearchEvents = new javax.swing.JButton();
        jPanelRecuentoDiario = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableAdRecuentoDiario = new javax.swing.JTable();
        jLabelAdId2 = new javax.swing.JLabel();
        jTextFieldAdIdDailyCount = new javax.swing.JTextField();
        jButtonAdSearchDailyCount = new javax.swing.JButton();
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

        jButtonAdDeleteUser.setText("Borrar");
        jButtonAdDeleteUser.addActionListener(this::jButtonAdDeleteUserActionPerformed);

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
                        .addComponent(jButtonAdDeleteUser))
                    .addGroup(jPanelUsuarioLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldAdIdUser)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelUsuarioLayout.setVerticalGroup(
            jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUsuarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldAdIdUser)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 240, Short.MAX_VALUE)
                .addGroup(jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdSearch)
                    .addComponent(jButtonAdInsert)
                    .addComponent(jButtonAdDeleteUser))
                .addContainerGap())
            .addComponent(jScrollPane1)
        );

        jTabbedPane1.addTab("Usuario", jPanelUsuario);

        jTableAdBolso.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTableAdBolso);

        jLabelAdId1.setText("ID:");

        jLabelAdName1.setText("Tipo:");

        jLabelAdSurname3.setText("Color:");

        jLabelAdSurname4.setText("Primera_conexion:");

        jLabelAdPhone1.setText("Id_cuenta:");

        jButtonAdSearchBags.setText("Buscar");
        jButtonAdSearchBags.addActionListener(this::jButtonAdSearchBagsActionPerformed);

        jButtonAdInsert1.setText("Insertar");
        jButtonAdInsert1.addActionListener(this::jButtonAdInsert1ActionPerformed);

        jButtonAdDeleteBags.setText("Borrar");
        jButtonAdDeleteBags.addActionListener(this::jButtonAdDeleteBagsActionPerformed);

        javax.swing.GroupLayout jPanelBolsoLayout = new javax.swing.GroupLayout(jPanelBolso);
        jPanelBolso.setLayout(jPanelBolsoLayout);
        jPanelBolsoLayout.setHorizontalGroup(
            jPanelBolsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBolsoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBolsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelBolsoLayout.createSequentialGroup()
                        .addGroup(jPanelBolsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelBolsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabelAdId1)
                                .addComponent(jLabelAdName1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelAdSurname3))
                            .addComponent(jLabelAdSurname4)
                            .addComponent(jLabelAdPhone1))
                        .addGap(112, 112, 112)
                        .addGroup(jPanelBolsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldAdIdBag, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(jTextFieldAdTipoBag)
                            .addComponent(jTextFieldAdColorBag)
                            .addComponent(jTextFieldAdPrimeraConexionBag)
                            .addComponent(jTextFieldAdIdCuentaBag))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelBolsoLayout.createSequentialGroup()
                        .addComponent(jButtonAdSearchBags)
                        .addGap(4, 4, 4)
                        .addComponent(jButtonAdInsert1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                        .addComponent(jButtonAdDeleteBags)))
                .addContainerGap())
        );
        jPanelBolsoLayout.setVerticalGroup(
            jPanelBolsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBolsoLayout.createSequentialGroup()
                .addGroup(jPanelBolsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanelBolsoLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanelBolsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldAdIdBag)
                            .addComponent(jLabelAdId1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelBolsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldAdTipoBag)
                            .addComponent(jLabelAdName1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelBolsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldAdColorBag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelAdSurname3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelBolsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldAdPrimeraConexionBag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelAdSurname4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelBolsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldAdIdCuentaBag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelAdPhone1))
                        .addGap(368, 368, 368)
                        .addGroup(jPanelBolsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonAdSearchBags)
                            .addComponent(jButtonAdInsert1)
                            .addComponent(jButtonAdDeleteBags))
                        .addGap(9, 9, 9)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Bolso", jPanelBolso);

        jTableAdEventos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTableAdEventos);

        jLabelAdId3.setText("ID:");

        jButtonAdSearchEvents.setText("Buscar");
        jButtonAdSearchEvents.addActionListener(this::jButtonAdSearchEventsActionPerformed);

        javax.swing.GroupLayout jPanelEventosLayout = new javax.swing.GroupLayout(jPanelEventos);
        jPanelEventos.setLayout(jPanelEventosLayout);
        jPanelEventosLayout.setHorizontalGroup(
            jPanelEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEventosLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 784, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelEventosLayout.createSequentialGroup()
                        .addComponent(jLabelAdId3)
                        .addGap(72, 72, 72)
                        .addComponent(jTextFieldAdIdEvent, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonAdSearchEvents))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        jPanelEventosLayout.setVerticalGroup(
            jPanelEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelEventosLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanelEventosLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAdIdEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAdId3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonAdSearchEvents)
                .addGap(61, 61, 61))
        );

        jTabbedPane1.addTab("Eventos", jPanelEventos);

        jTableAdRecuentoDiario.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(jTableAdRecuentoDiario);

        jLabelAdId2.setText("ID:");

        jButtonAdSearchDailyCount.setText("Buscar");
        jButtonAdSearchDailyCount.addActionListener(this::jButtonAdSearchDailyCountActionPerformed);

        javax.swing.GroupLayout jPanelRecuentoDiarioLayout = new javax.swing.GroupLayout(jPanelRecuentoDiario);
        jPanelRecuentoDiario.setLayout(jPanelRecuentoDiarioLayout);
        jPanelRecuentoDiarioLayout.setHorizontalGroup(
            jPanelRecuentoDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRecuentoDiarioLayout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelRecuentoDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRecuentoDiarioLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelAdId2)
                        .addGap(104, 104, 104)
                        .addComponent(jTextFieldAdIdDailyCount, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelRecuentoDiarioLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jButtonAdSearchDailyCount)))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        jPanelRecuentoDiarioLayout.setVerticalGroup(
            jPanelRecuentoDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRecuentoDiarioLayout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 571, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanelRecuentoDiarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRecuentoDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAdId2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldAdIdDailyCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonAdSearchDailyCount)
                .addGap(72, 72, 72))
        );

        jTabbedPane1.addTab("Recuento diario", jPanelRecuentoDiario);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel1.setText("OPI");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconOPI.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(26, 26, 26)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAdSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdSearchActionPerformed
        try {
            if (jTextFieldAdIdUser.getText().isBlank() == false) {
                resultSetToTableModel(db.getUser(Integer.parseInt(jTextFieldAdIdUser.getText())), this.jTableAdUser);
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

    private void jButtonAdDeleteUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdDeleteUserActionPerformed
        int successCode;
        int confirmMessage;
        
        confirmMessage = JOptionPane.showConfirmDialog(this, "¿Quieres borrar este usuario?", "Delete user", JOptionPane.YES_NO_OPTION);
        
        if (confirmMessage == 0) {
            successCode = db.deleteUser(Integer.parseInt(jTextFieldAdIdUser.getText()));    
        }
        
    }//GEN-LAST:event_jButtonAdDeleteUserActionPerformed

    private void jButtonAdInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdInsertActionPerformed
        Object[] userdata = new Object[jTableAdUser.getColumnCount()];
        
        userdata[0] = Integer.valueOf(jTextFieldAdIdUser.getText());
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

    private void jButtonAdSearchBagsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdSearchBagsActionPerformed
        try {
            if (jTextFieldAdIdBag.getText().isBlank() == false) {
                resultSetToTableModel(db.getBagsFromUser(Integer.parseInt(jTextFieldAdIdBag.getText())), this.jTableAdBolso);
            } else {
                resultSetToTableModel(db.getAllBags(), this.jTableAdBolso);
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }//GEN-LAST:event_jButtonAdSearchBagsActionPerformed

    private void jButtonAdInsert1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdInsert1ActionPerformed
        Object[] userdata = new Object[jTableAdBolso.getColumnCount()];
        
        userdata[0] = Integer.valueOf(jTextFieldAdIdBag.getText());
        userdata[1] = jTextFieldAdTipoBag.getText();
        userdata[2] = jTextFieldAdColorBag.getText();
        userdata[3] = jTextFieldAdIdCuentaBag.getText();
        
        db.insertUser(userdata);
    }//GEN-LAST:event_jButtonAdInsert1ActionPerformed

    private void jButtonAdDeleteBagsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdDeleteBagsActionPerformed
        int successCode;
        int confirmMessage;
        
        confirmMessage = JOptionPane.showConfirmDialog(this, "¿Quieres borrar este bolso?", "Delete bag", JOptionPane.YES_NO_OPTION);
        
        if (confirmMessage == 0) {
            successCode = db.deleteBags(Integer.parseInt(jTextFieldAdIdBag.getText()));    
        }
        
    }//GEN-LAST:event_jButtonAdDeleteBagsActionPerformed

    private void jButtonAdSearchEventsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdSearchEventsActionPerformed
        try {
            if (jTextFieldAdIdEvent.getText().isBlank() == false) {
                resultSetToTableModel(db.getEventsFromBags(Integer.parseInt(jTextFieldAdIdEvent.getText())), this.jTableAdEventos);
            } else {
                resultSetToTableModel(db.getAllBags(), this.jTableAdEventos);
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }//GEN-LAST:event_jButtonAdSearchEventsActionPerformed

    private void jButtonAdSearchDailyCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdSearchDailyCountActionPerformed
        try {
            if (jTextFieldAdIdDailyCount.getText().isBlank() == false) {
                resultSetToTableModel(db.getEventsFromBags(Integer.parseInt(jTextFieldAdIdDailyCount.getText())), this.jTableAdRecuentoDiario);
            } else {
                resultSetToTableModel(db.getAllBags(), this.jTableAdRecuentoDiario);
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }//GEN-LAST:event_jButtonAdSearchDailyCountActionPerformed

    
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
    private javax.swing.JButton jButtonAdDeleteBags;
    private javax.swing.JButton jButtonAdDeleteUser;
    private javax.swing.JButton jButtonAdInsert;
    private javax.swing.JButton jButtonAdInsert1;
    private javax.swing.JButton jButtonAdSearch;
    private javax.swing.JButton jButtonAdSearchBags;
    private javax.swing.JButton jButtonAdSearchDailyCount;
    private javax.swing.JButton jButtonAdSearchEvents;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelAdBirth;
    private javax.swing.JLabel jLabelAdEmail;
    private javax.swing.JLabel jLabelAdHeight;
    private javax.swing.JLabel jLabelAdId;
    private javax.swing.JLabel jLabelAdId1;
    private javax.swing.JLabel jLabelAdId2;
    private javax.swing.JLabel jLabelAdId3;
    private javax.swing.JLabel jLabelAdName;
    private javax.swing.JLabel jLabelAdName1;
    private javax.swing.JLabel jLabelAdPassword;
    private javax.swing.JLabel jLabelAdPhone;
    private javax.swing.JLabel jLabelAdPhone1;
    private javax.swing.JLabel jLabelAdSurname1;
    private javax.swing.JLabel jLabelAdSurname2;
    private javax.swing.JLabel jLabelAdSurname3;
    private javax.swing.JLabel jLabelAdSurname4;
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
    private javax.swing.JTable jTableAdBolso;
    private javax.swing.JTable jTableAdEventos;
    private javax.swing.JTable jTableAdRecuentoDiario;
    private javax.swing.JTable jTableAdUser;
    private javax.swing.JTextField jTextFieldAdBirth;
    private javax.swing.JTextField jTextFieldAdColorBag;
    private javax.swing.JTextField jTextFieldAdEmail;
    private javax.swing.JTextField jTextFieldAdHeight;
    private javax.swing.JTextField jTextFieldAdIdBag;
    private javax.swing.JTextField jTextFieldAdIdCuentaBag;
    private javax.swing.JTextField jTextFieldAdIdDailyCount;
    private javax.swing.JTextField jTextFieldAdIdEvent;
    private javax.swing.JTextField jTextFieldAdIdUser;
    private javax.swing.JTextField jTextFieldAdName;
    private javax.swing.JTextField jTextFieldAdPassword;
    private javax.swing.JTextField jTextFieldAdPhone;
    private javax.swing.JTextField jTextFieldAdPrimeraConexionBag;
    private javax.swing.JTextField jTextFieldAdSurname1;
    private javax.swing.JTextField jTextFieldAdSurname2;
    private javax.swing.JTextField jTextFieldAdTipoBag;
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
