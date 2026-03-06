package databasedesktop;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MainWindow extends javax.swing.JFrame {
    
    public int currentUser = 0;
    Database db = new Database();
    
    public MainWindow() {
        initComponents();
        setProfileInfo();
        hideWindow();
        // ImageIcon icono = new ImageIcon(getClass().getResource("img/iconOPI.png"));
        // jLabel2.setIcon(icono);
        
    
    }
    private void hideWindow(){
        try {
            connectToDatabase();
            resultSetToTableModel(db.getAllUsers(), this.jTableAdUser);
            resultSetToTableModel(db.getAllBags(), this.jTableAdBolso);
            resultSetToTableModel(db.getAllEvents(), this.jTableAdEventos);
            resultSetToTableModel(db.getAllDailyCount(), this.jTableAdRecuentoDiario);
            
        } catch (SQLException ex) {
            System.err.println("Couldn't fill table");
        }
        System.out.println(db.getDatum("cuenta", "email", 1));
        this.setVisible(false);
        LoginDialog login = new LoginDialog(this, true);
            login.setLocationRelativeTo(null);
            login.setTitle("Login - Opi");

            login.setVisible(true);

            if (login.isLogged()) {
                initWindow();
            } else {
                System.exit(0);
            }
    }
    private void initWindow(){
        setProfileInfo();
        try {
            //this.setSize(1280,720);
            this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
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
            
            jScrollPaneBolso.getViewport().setBackground(new Color(21, 87, 218));
            jScrollPaneBolso.setBorder(null);
            jScrollPaneBolsoUsuario.getViewport().setBackground(new Color(21, 87, 218));
            jScrollPaneEventos.getViewport().setBackground(new Color(21, 87, 218));
            jScrollPaneEventos.setBorder(null);
            jScrollPaneRecuentoDiario.setBorder(null);
            jScrollPaneRecuentoDiario.getViewport().setBackground(new Color(21, 87, 218));
            jScrollPaneRecuentoDiarioUsuario.getViewport().setBackground(new Color(21, 87, 218));
            jScrollPaneUsuario.getViewport().setBackground(new Color(21, 87, 218));
            jScrollPaneUsuario.setBorder(null);
            
            getContentPane().setBackground(new java.awt.Color(255, 255, 255));
            
            jTabbedPanePerfilUs.setBorder(null);
            
            jTableAdBolso.getTableHeader().setOpaque(false);
            jTableAdBolso.getTableHeader().setBackground(new Color(255, 255, 255));
            jTableAdBolso.getTableHeader().setFont(new Font("sansserif", Font.BOLD, 13));
            
            jTableAdEventos.getTableHeader().setOpaque(false);
            jTableAdEventos.getTableHeader().setBackground(new Color(255, 255, 255));
            jTableAdEventos.getTableHeader().setFont(new Font("sansserif", Font.BOLD, 13));
            
            jTableAdUser.getTableHeader().setOpaque(false);
            jTableAdUser.getTableHeader().setBackground(new Color(255, 255, 255));
            jTableAdUser.getTableHeader().setFont(new Font("sansserif", Font.BOLD, 13));
            
            jTableBolsoUsuario.getTableHeader().setOpaque(false);
            jTableBolsoUsuario.getTableHeader().setBackground(new Color(255, 255, 255));
            jTableBolsoUsuario.getTableHeader().setFont(new Font("sansserif", Font.BOLD, 13));
            
            jTableRecuentoDiarioUsuario.getTableHeader().setOpaque(false);
            jTableRecuentoDiarioUsuario.getTableHeader().setBackground(new Color(255, 255, 255));
            jTableRecuentoDiarioUsuario.getTableHeader().setFont(new Font("sansserif", Font.BOLD, 13));
            
            jTableAdRecuentoDiario.getTableHeader().setOpaque(false);
            jTableAdRecuentoDiario.getTableHeader().setBackground(new Color(255, 255, 255));
            jTableAdRecuentoDiario.getTableHeader().setFont(new Font("sansserif", Font.BOLD, 13));
            
            ImageIcon icono = new ImageIcon(getClass().getResource("/img/iconOPI.png"));
            Image imagen = icono.getImage();
            Image imagenEscalada = imagen.getScaledInstance(
                    jLabelIcon.getWidth(),
                    jLabelIcon.getHeight(),
                    Image.SCALE_SMOOTH
            );
            jLabelIcon.setIcon(new ImageIcon(imagenEscalada));
            
            System.out.println(currentUser);
            resultSetToTableModel(db.getDailyCountFromUser(currentUser), this.jTableRecuentoDiarioUsuario);
            resultSetToTableModel(db.getBagsFromUser(currentUser), this.jTableBolsoUsuario);
            
            jLabelWelcome.setText(jLabelWelcome.getText() + db.getUserFromId(currentUser));
            
        } catch (SQLException ex) {
            System.getLogger(MainWindow.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPanePerfilUs = new javax.swing.JTabbedPane();
        jPanelProfile = new javax.swing.JPanel();
        jLabelPerfilNombre = new javax.swing.JLabel();
        jLabelPerfilApellido = new javax.swing.JLabel();
        jLabelPerfilEmail = new javax.swing.JLabel();
        jLabelPerfilTelefono = new javax.swing.JLabel();
        jLabelPerfilApellido2 = new javax.swing.JLabel();
        jLabelPerfilfechaNacim = new javax.swing.JLabel();
        jLabelPerfilAltura = new javax.swing.JLabel();
        jTextFieldAltura = new javax.swing.JTextField();
        jLabelPerfilPeso = new javax.swing.JLabel();
        jTextFieldPeso = new javax.swing.JTextField();
        jLabelPerfilPeso1 = new javax.swing.JLabel();
        jTextFieldAguaDeseada = new javax.swing.JTextField();
        jButtonActualizar = new javax.swing.JButton();
        jLabelIconoInformaticos = new javax.swing.JLabel();
        jPanelUserBolso = new javax.swing.JPanel();
        jScrollPaneBolsoUsuario = new javax.swing.JScrollPane();
        jTableBolsoUsuario = new javax.swing.JTable();
        jPanelRecuentoDiarioUsuario = new javax.swing.JPanel();
        jScrollPaneRecuentoDiarioUsuario = new javax.swing.JScrollPane();
        jTableRecuentoDiarioUsuario = new javax.swing.JTable();
        jPanelUsuario = new javax.swing.JPanel();
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
        jScrollPaneUsuario = new javax.swing.JScrollPane();
        jTableAdUser = new javax.swing.JTable();
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
        jButtonAdUserLimpiar = new javax.swing.JButton();
        jPanelBolso = new javax.swing.JPanel();
        jScrollPaneBolso = new javax.swing.JScrollPane();
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
        jButtonAdInsert2 = new javax.swing.JButton();
        jPanelEventos = new javax.swing.JPanel();
        jScrollPaneEventos = new javax.swing.JScrollPane();
        jTableAdEventos = new javax.swing.JTable();
        jLabelAdId3 = new javax.swing.JLabel();
        jTextFieldAdIdEvent = new javax.swing.JTextField();
        jButtonAdSearchEvents = new javax.swing.JButton();
        jPanelRecuentoDiario = new javax.swing.JPanel();
        jScrollPaneRecuentoDiario = new javax.swing.JScrollPane();
        jTableAdRecuentoDiario = new javax.swing.JTable();
        jLabelAdId2 = new javax.swing.JLabel();
        jTextFieldAdIdDailyCount = new javax.swing.JTextField();
        jButtonAdSearchDailyCount = new javax.swing.JButton();
        jLabelTitle = new javax.swing.JLabel();
        jLabelIcon = new javax.swing.JLabel();
        jLabelWelcome = new javax.swing.JLabel();
        jButtonExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(21, 87, 218));

        jTabbedPanePerfilUs.setBackground(new java.awt.Color(255, 255, 254));
        jTabbedPanePerfilUs.setForeground(new java.awt.Color(2, 39, 172));

        jPanelProfile.setBackground(new java.awt.Color(21, 87, 218));

        jLabelPerfilNombre.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabelPerfilNombre.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPerfilNombre.setText("Nombre:");

        jLabelPerfilApellido.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabelPerfilApellido.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPerfilApellido.setText("Apellido 1: ");

        jLabelPerfilEmail.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabelPerfilEmail.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPerfilEmail.setText("Email: ");

        jLabelPerfilTelefono.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabelPerfilTelefono.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPerfilTelefono.setText("Teléfono: ");

        jLabelPerfilApellido2.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabelPerfilApellido2.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPerfilApellido2.setText("Apellido 2: ");

        jLabelPerfilfechaNacim.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabelPerfilfechaNacim.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPerfilfechaNacim.setText("Fecha de nacimiento:");

        jLabelPerfilAltura.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabelPerfilAltura.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPerfilAltura.setText("Altura:");

        jLabelPerfilPeso.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabelPerfilPeso.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPerfilPeso.setText("Peso:");

        jLabelPerfilPeso1.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabelPerfilPeso1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPerfilPeso1.setText("Agua deseada:");

        jButtonActualizar.setBackground(new java.awt.Color(254, 254, 254));
        jButtonActualizar.setText("Actualizar");
        jButtonActualizar.addActionListener(this::jButtonActualizarActionPerformed);

        jLabelIconoInformaticos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconoDAM.png"))); // NOI18N

        javax.swing.GroupLayout jPanelProfileLayout = new javax.swing.GroupLayout(jPanelProfile);
        jPanelProfile.setLayout(jPanelProfileLayout);
        jPanelProfileLayout.setHorizontalGroup(
            jPanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProfileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelIconoInformaticos, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelPerfilfechaNacim, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelProfileLayout.createSequentialGroup()
                        .addGroup(jPanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabelPerfilNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelPerfilApellido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelPerfilTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                                .addComponent(jLabelPerfilApellido2, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                            .addComponent(jLabelPerfilEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(238, 238, 238)
                        .addGroup(jPanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelPerfilPeso1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelPerfilPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelPerfilAltura, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldAguaDeseada)
                            .addComponent(jTextFieldPeso)
                            .addComponent(jTextFieldAltura)
                            .addComponent(jButtonActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(961, Short.MAX_VALUE))
        );
        jPanelProfileLayout.setVerticalGroup(
            jPanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelProfileLayout.createSequentialGroup()
                .addGroup(jPanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelProfileLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelPerfilNombre)
                            .addComponent(jLabelPerfilAltura, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldAltura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelPerfilApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextFieldPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelPerfilPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelPerfilPeso1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldAguaDeseada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelPerfilApellido2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelPerfilEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonActualizar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelPerfilTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelPerfilfechaNacim, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelProfileLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelIconoInformaticos)))
                .addContainerGap(207, Short.MAX_VALUE))
        );

        jTabbedPanePerfilUs.addTab("Perfil", jPanelProfile);

        jPanelUserBolso.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPaneBolsoUsuario.setBackground(new java.awt.Color(255, 255, 255));

        jTableBolsoUsuario.setBackground(new java.awt.Color(255, 255, 254));
        jTableBolsoUsuario.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableBolsoUsuario.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPaneBolsoUsuario.setViewportView(jTableBolsoUsuario);

        javax.swing.GroupLayout jPanelUserBolsoLayout = new javax.swing.GroupLayout(jPanelUserBolso);
        jPanelUserBolso.setLayout(jPanelUserBolsoLayout);
        jPanelUserBolsoLayout.setHorizontalGroup(
            jPanelUserBolsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneBolsoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 2016, Short.MAX_VALUE)
        );
        jPanelUserBolsoLayout.setVerticalGroup(
            jPanelUserBolsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneBolsoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
        );

        jTabbedPanePerfilUs.addTab("Tus Bolsos", jPanelUserBolso);

        jPanelRecuentoDiarioUsuario.setBackground(new java.awt.Color(21, 87, 218));

        jTableRecuentoDiarioUsuario.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableRecuentoDiarioUsuario.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPaneRecuentoDiarioUsuario.setViewportView(jTableRecuentoDiarioUsuario);

        javax.swing.GroupLayout jPanelRecuentoDiarioUsuarioLayout = new javax.swing.GroupLayout(jPanelRecuentoDiarioUsuario);
        jPanelRecuentoDiarioUsuario.setLayout(jPanelRecuentoDiarioUsuarioLayout);
        jPanelRecuentoDiarioUsuarioLayout.setHorizontalGroup(
            jPanelRecuentoDiarioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneRecuentoDiarioUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 2016, Short.MAX_VALUE)
        );
        jPanelRecuentoDiarioUsuarioLayout.setVerticalGroup(
            jPanelRecuentoDiarioUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneRecuentoDiarioUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
        );

        jTabbedPanePerfilUs.addTab("Tu Recuento Diario", jPanelRecuentoDiarioUsuario);

        jPanelUsuario.setBackground(new java.awt.Color(21, 87, 218));

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
        jTableAdUser.setGridColor(new java.awt.Color(0, 0, 0));
        jTableAdUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableAdUserMouseClicked(evt);
            }
        });
        jScrollPaneUsuario.setViewportView(jTableAdUser);

        jLabelAdId.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabelAdId.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAdId.setText("ID:");

        jLabelAdName.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabelAdName.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAdName.setText("Nombre:");

        jLabelAdSurname1.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabelAdSurname1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAdSurname1.setText("Apellido 1:");

        jLabelAdSurname2.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabelAdSurname2.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAdSurname2.setText("Apellido 2:");

        jLabelAdPhone.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabelAdPhone.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAdPhone.setText("Teléfono:");

        jLabelAdEmail.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabelAdEmail.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAdEmail.setText("Email:");

        jLabelAdPassword.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabelAdPassword.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAdPassword.setText("Contraseña:");

        jLabelAdHeight.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabelAdHeight.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAdHeight.setText("Altura:");

        jLabelAdWeight.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabelAdWeight.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAdWeight.setText("Peso:");

        jLabelAdBirth.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabelAdBirth.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAdBirth.setText("Fecha nacimiento:");

        jLabelAdWater.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabelAdWater.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAdWater.setText("Agua deseada:");

        jButtonAdSearch.setBackground(new java.awt.Color(254, 254, 254));
        jButtonAdSearch.setText("Buscar");
        jButtonAdSearch.addActionListener(this::jButtonAdSearchActionPerformed);

        jButtonAdInsert.setBackground(new java.awt.Color(254, 254, 254));
        jButtonAdInsert.setText("Insertar");
        jButtonAdInsert.addActionListener(this::jButtonAdInsertActionPerformed);

        jButtonAdDeleteUser.setBackground(new java.awt.Color(254, 254, 254));
        jButtonAdDeleteUser.setText("Borrar");
        jButtonAdDeleteUser.addActionListener(this::jButtonAdDeleteUserActionPerformed);

        jButtonAdUserLimpiar.setBackground(new java.awt.Color(254, 254, 254));
        jButtonAdUserLimpiar.setText("Limpiar");
        jButtonAdUserLimpiar.addActionListener(this::jButtonAdUserLimpiarActionPerformed);

        javax.swing.GroupLayout jPanelUsuarioLayout = new javax.swing.GroupLayout(jPanelUsuario);
        jPanelUsuario.setLayout(jPanelUsuarioLayout);
        jPanelUsuarioLayout.setHorizontalGroup(
            jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUsuarioLayout.createSequentialGroup()
                .addComponent(jScrollPaneUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelUsuarioLayout.createSequentialGroup()
                        .addGroup(jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAdPassword)
                            .addComponent(jLabelAdId)
                            .addComponent(jLabelAdSurname2)
                            .addComponent(jLabelAdPhone)
                            .addComponent(jLabelAdEmail)
                            .addComponent(jLabelAdHeight)
                            .addComponent(jLabelAdWeight)
                            .addComponent(jLabelAdWater)
                            .addComponent(jLabelAdBirth)
                            .addComponent(jLabelAdName)
                            .addComponent(jLabelAdSurname1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
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
                            .addComponent(jTextFieldAdPassword)))
                    .addGroup(jPanelUsuarioLayout.createSequentialGroup()
                        .addGroup(jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButtonAdInsert, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                            .addComponent(jButtonAdSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonAdDeleteUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonAdUserLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdSearch)
                    .addComponent(jButtonAdDeleteUser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdInsert)
                    .addComponent(jButtonAdUserLimpiar))
                .addGap(227, 227, 227))
            .addComponent(jScrollPaneUsuario)
        );

        jTabbedPanePerfilUs.addTab("Usuarios", jPanelUsuario);

        jPanelBolso.setBackground(new java.awt.Color(21, 87, 218));

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
        jTableAdBolso.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPaneBolso.setViewportView(jTableAdBolso);

        jLabelAdId1.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabelAdId1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAdId1.setText("ID:");

        jLabelAdName1.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabelAdName1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAdName1.setText("Tipo:");

        jLabelAdSurname3.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabelAdSurname3.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAdSurname3.setText("Color:");

        jLabelAdSurname4.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabelAdSurname4.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAdSurname4.setText("Primera_conexion:");

        jLabelAdPhone1.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabelAdPhone1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAdPhone1.setText("Id_cuenta:");

        jButtonAdSearchBags.setBackground(new java.awt.Color(254, 254, 254));
        jButtonAdSearchBags.setText("Buscar");
        jButtonAdSearchBags.addActionListener(this::jButtonAdSearchBagsActionPerformed);

        jButtonAdInsert1.setBackground(new java.awt.Color(254, 254, 254));
        jButtonAdInsert1.setText("Insertar");
        jButtonAdInsert1.addActionListener(this::jButtonAdInsert1ActionPerformed);

        jButtonAdDeleteBags.setBackground(new java.awt.Color(254, 254, 254));
        jButtonAdDeleteBags.setText("Borrar");
        jButtonAdDeleteBags.addActionListener(this::jButtonAdDeleteBagsActionPerformed);

        jButtonAdInsert2.setBackground(new java.awt.Color(254, 254, 254));
        jButtonAdInsert2.setText("Limpiar");
        jButtonAdInsert2.addActionListener(this::jButtonAdInsert2ActionPerformed);

        javax.swing.GroupLayout jPanelBolsoLayout = new javax.swing.GroupLayout(jPanelBolso);
        jPanelBolso.setLayout(jPanelBolsoLayout);
        jPanelBolsoLayout.setHorizontalGroup(
            jPanelBolsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBolsoLayout.createSequentialGroup()
                .addComponent(jScrollPaneBolso, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addGap(51, 51, 51)
                        .addGroup(jPanelBolsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldAdTipoBag, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(jTextFieldAdColorBag)
                            .addComponent(jTextFieldAdPrimeraConexionBag)
                            .addComponent(jTextFieldAdIdCuentaBag)
                            .addComponent(jTextFieldAdIdBag)))
                    .addGroup(jPanelBolsoLayout.createSequentialGroup()
                        .addComponent(jButtonAdSearchBags, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAdDeleteBags, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelBolsoLayout.createSequentialGroup()
                        .addComponent(jButtonAdInsert1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAdInsert2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(738, Short.MAX_VALUE))
        );
        jPanelBolsoLayout.setVerticalGroup(
            jPanelBolsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBolsoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelBolsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAdId1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldAdIdBag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBolsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAdTipoBag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAdName1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBolsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAdColorBag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAdSurname3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBolsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextFieldAdPrimeraConexionBag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAdSurname4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBolsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldAdIdCuentaBag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAdPhone1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelBolsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdSearchBags)
                    .addComponent(jButtonAdDeleteBags))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBolsoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdInsert1)
                    .addComponent(jButtonAdInsert2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPaneBolso, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
        );

        jTabbedPanePerfilUs.addTab("Bolsos", jPanelBolso);

        jPanelEventos.setBackground(new java.awt.Color(21, 87, 218));

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
        jTableAdEventos.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPaneEventos.setViewportView(jTableAdEventos);

        jLabelAdId3.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabelAdId3.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAdId3.setText("ID de bolso:");

        jButtonAdSearchEvents.setText("Buscar");
        jButtonAdSearchEvents.addActionListener(this::jButtonAdSearchEventsActionPerformed);

        javax.swing.GroupLayout jPanelEventosLayout = new javax.swing.GroupLayout(jPanelEventos);
        jPanelEventos.setLayout(jPanelEventosLayout);
        jPanelEventosLayout.setHorizontalGroup(
            jPanelEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEventosLayout.createSequentialGroup()
                .addComponent(jScrollPaneEventos, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelEventosLayout.createSequentialGroup()
                        .addComponent(jLabelAdId3)
                        .addGap(96, 96, 96)
                        .addComponent(jTextFieldAdIdEvent, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonAdSearchEvents, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(738, Short.MAX_VALUE))
        );
        jPanelEventosLayout.setVerticalGroup(
            jPanelEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEventosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelEventosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAdId3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldAdIdEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAdSearchEvents)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPaneEventos, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
        );

        jTabbedPanePerfilUs.addTab("Eventos", jPanelEventos);

        jPanelRecuentoDiario.setBackground(new java.awt.Color(21, 87, 218));

        jScrollPaneRecuentoDiario.setBackground(new java.awt.Color(21, 87, 218));

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
        jTableAdRecuentoDiario.setGridColor(new java.awt.Color(0, 0, 0));
        jScrollPaneRecuentoDiario.setViewportView(jTableAdRecuentoDiario);

        jLabelAdId2.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jLabelAdId2.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAdId2.setText("ID de usuario:");

        jButtonAdSearchDailyCount.setText("Buscar");
        jButtonAdSearchDailyCount.addActionListener(this::jButtonAdSearchDailyCountActionPerformed);

        javax.swing.GroupLayout jPanelRecuentoDiarioLayout = new javax.swing.GroupLayout(jPanelRecuentoDiario);
        jPanelRecuentoDiario.setLayout(jPanelRecuentoDiarioLayout);
        jPanelRecuentoDiarioLayout.setHorizontalGroup(
            jPanelRecuentoDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRecuentoDiarioLayout.createSequentialGroup()
                .addComponent(jScrollPaneRecuentoDiario, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelRecuentoDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelRecuentoDiarioLayout.createSequentialGroup()
                        .addComponent(jLabelAdId2)
                        .addGap(82, 82, 82)
                        .addComponent(jTextFieldAdIdDailyCount, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonAdSearchDailyCount, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(738, Short.MAX_VALUE))
        );
        jPanelRecuentoDiarioLayout.setVerticalGroup(
            jPanelRecuentoDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRecuentoDiarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelRecuentoDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelAdId2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldAdIdDailyCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAdSearchDailyCount)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPaneRecuentoDiario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
        );

        jTabbedPanePerfilUs.addTab("Recuentos Diarios", jPanelRecuentoDiario);

        jLabelTitle.setFont(new java.awt.Font("Noto Sans ExtraBold", 1, 48)); // NOI18N
        jLabelTitle.setForeground(new java.awt.Color(255, 153, 51));
        jLabelTitle.setText("OPI");

        jLabelIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/iconOPI.png"))); // NOI18N

        jLabelWelcome.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabelWelcome.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelWelcome.setText("Hola, ");

        jButtonExit.setBackground(new java.awt.Color(21, 87, 218));
        jButtonExit.setFont(new java.awt.Font("sansserif", 1, 13)); // NOI18N
        jButtonExit.setForeground(new java.awt.Color(255, 255, 255));
        jButtonExit.setText("Acabar sesión");
        jButtonExit.addActionListener(this::jButtonExitActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jTabbedPanePerfilUs)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButtonExit, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelWelcome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelIcon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jTabbedPanePerfilUs))
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
        
        try {
            resultSetToTableModel(db.getAllUsers(), this.jTableAdUser);
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
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
                resultSetToTableModel(db.getAllEvents(), this.jTableAdEventos);
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }//GEN-LAST:event_jButtonAdSearchEventsActionPerformed

    private void jButtonAdSearchDailyCountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdSearchDailyCountActionPerformed
        try {
            if (jTextFieldAdIdDailyCount.getText().isBlank() == false) {
                resultSetToTableModel(db.getDailyCountFromUser(Integer.parseInt(jTextFieldAdIdDailyCount.getText())), this.jTableAdRecuentoDiario);
            } else {
                resultSetToTableModel(db.getAllDailyCount(), this.jTableAdRecuentoDiario);
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }//GEN-LAST:event_jButtonAdSearchDailyCountActionPerformed

    private void jButtonAdUserLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdUserLimpiarActionPerformed
        jTextFieldAdIdUser.setText("");
        jTextFieldAdName.setText("");
        jTextFieldAdSurname1.setText("");
        jTextFieldAdSurname2.setText("");
        jTextFieldAdPhone.setText("");
        jTextFieldAdEmail.setText("");
        jTextFieldAdPassword.setText("");
        jTextFieldAdHeight.setText("");
        jTextFieldAdWeight.setText("");
        jTextFieldAdBirth.setText("");
        jTextFieldAdWater.setText("");
    }//GEN-LAST:event_jButtonAdUserLimpiarActionPerformed

    private void jButtonAdInsert2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdInsert2ActionPerformed
        jTextFieldAdIdBag.setText("");
        jTextFieldAdTipoBag.setText("");
        jTextFieldAdColorBag.setText("");
        jTextFieldAdPrimeraConexionBag.setText("");
        jTextFieldAdIdCuentaBag.setText("");
    }//GEN-LAST:event_jButtonAdInsert2ActionPerformed

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButtonExitActionPerformed

    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarActionPerformed
        int code = 0;
        int result = 0;
        if(jTextFieldAltura.getText()!="0"){
            code = 1;
            result = db.updateProfile(jTextFieldAltura.getText(),code,currentUser);
        }
        if(jTextFieldPeso.getText()!="0"){
            code = 2;
            result = db.updateProfile(jTextFieldPeso.getText(),code,currentUser);
        }
        
        if(jTextFieldAguaDeseada.getText()!="0"){
            code = 3;
            result = db.updateProfile(jTextFieldAguaDeseada.getText(),code,currentUser);
        }
    }//GEN-LAST:event_jButtonActualizarActionPerformed
    
    private void setProfileInfo() {
    
    jLabelPerfilNombre.setText(db.getDatum("cuenta", "nombre", currentUser));
    jLabelPerfilApellido.setText(db.getDatum("cuenta", "apellido1", currentUser));
    jLabelPerfilApellido2.setText(db.getDatum("cuenta", "apellido2", currentUser));
    jLabelPerfilEmail.setText(db.getDatum("cuenta", "email", currentUser));
    jLabelPerfilTelefono.setText(db.getDatum("cuenta", "telefono", currentUser));
    jLabelPerfilfechaNacim.setText(db.getDatum("cuenta", "fecha_nacimiento", currentUser));

    // Campos editables (altura, peso, agua)
    jTextFieldAltura.setText(db.getDatum("cuenta", "altura", currentUser));
    jTextFieldPeso.setText(db.getDatum("cuenta", "peso", currentUser));
    jTextFieldAguaDeseada.setText(db.getDatum("cuenta", "agua_deseada", currentUser));
}
    
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
        System.out.println(db.getEmailFromUser(1));
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
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JButton jButtonAdDeleteBags;
    private javax.swing.JButton jButtonAdDeleteUser;
    private javax.swing.JButton jButtonAdInsert;
    private javax.swing.JButton jButtonAdInsert1;
    private javax.swing.JButton jButtonAdInsert2;
    private javax.swing.JButton jButtonAdSearch;
    private javax.swing.JButton jButtonAdSearchBags;
    private javax.swing.JButton jButtonAdSearchDailyCount;
    private javax.swing.JButton jButtonAdSearchEvents;
    private javax.swing.JButton jButtonAdUserLimpiar;
    private javax.swing.JButton jButtonExit;
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
    private javax.swing.JLabel jLabelIcon;
    private javax.swing.JLabel jLabelIconoInformaticos;
    private javax.swing.JLabel jLabelPerfilAltura;
    private javax.swing.JLabel jLabelPerfilApellido;
    private javax.swing.JLabel jLabelPerfilApellido2;
    private javax.swing.JLabel jLabelPerfilEmail;
    private javax.swing.JLabel jLabelPerfilNombre;
    private javax.swing.JLabel jLabelPerfilPeso;
    private javax.swing.JLabel jLabelPerfilPeso1;
    private javax.swing.JLabel jLabelPerfilTelefono;
    private javax.swing.JLabel jLabelPerfilfechaNacim;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelWelcome;
    private javax.swing.JPanel jPanelBolso;
    private javax.swing.JPanel jPanelEventos;
    private javax.swing.JPanel jPanelProfile;
    private javax.swing.JPanel jPanelRecuentoDiario;
    private javax.swing.JPanel jPanelRecuentoDiarioUsuario;
    private javax.swing.JPanel jPanelUserBolso;
    private javax.swing.JPanel jPanelUsuario;
    private javax.swing.JScrollPane jScrollPaneBolso;
    private javax.swing.JScrollPane jScrollPaneBolsoUsuario;
    private javax.swing.JScrollPane jScrollPaneEventos;
    private javax.swing.JScrollPane jScrollPaneRecuentoDiario;
    private javax.swing.JScrollPane jScrollPaneRecuentoDiarioUsuario;
    private javax.swing.JScrollPane jScrollPaneUsuario;
    private javax.swing.JTabbedPane jTabbedPanePerfilUs;
    private javax.swing.JTable jTableAdBolso;
    private javax.swing.JTable jTableAdEventos;
    private javax.swing.JTable jTableAdRecuentoDiario;
    private javax.swing.JTable jTableAdUser;
    private javax.swing.JTable jTableBolsoUsuario;
    private javax.swing.JTable jTableRecuentoDiarioUsuario;
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
    private javax.swing.JTextField jTextFieldAguaDeseada;
    private javax.swing.JTextField jTextFieldAltura;
    private javax.swing.JTextField jTextFieldPeso;
    // End of variables declaration//GEN-END:variables

    // No se que hace esto, pero no se toca 
    private void eventsButtonActionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void starsButtonActionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
