package databasedesktop;

public class UserListWindow extends javax.swing.JDialog {
    
    public static MainWindow mw;

    public UserListWindow(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        mw = (MainWindow)parent;
        initComponents();
        showList();
        jButtonSelect.setEnabled(false);
    }

    private void showList() {
        jListUsers.setVisibleRowCount(10);
        String[] users = new String[mw.db.getAmountUsers()];
                
        for (int i=0; i<users.length; i++) {
            users[i] = mw.db.getUsersFromId(i+1);
        }
        
        jListUsers.setListData(users);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPaneUserList = new javax.swing.JScrollPane();
        jListUsers = new javax.swing.JList<>();
        jButtonSelect = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jListUsers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListUsers.setMaximumSize(new java.awt.Dimension(150, 175));
        jListUsers.setMinimumSize(new java.awt.Dimension(150, 175));
        jListUsers.setPreferredSize(new java.awt.Dimension(150, 175));
        jListUsers.setVisibleRowCount(10);
        jListUsers.addListSelectionListener(this::jListUsersValueChanged);
        jScrollPaneUserList.setViewportView(jListUsers);

        jButtonSelect.setText("SELECT");
        jButtonSelect.addActionListener(this::jButtonSelectActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addComponent(jButtonSelect)
                .addGap(57, 57, 57))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneUserList, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneUserList, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonSelect)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSelectActionPerformed
        mw.currentUser = jListUsers.getSelectedIndex()+1;
        this.dispose();
    }//GEN-LAST:event_jButtonSelectActionPerformed

    private void jListUsersValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListUsersValueChanged
        jButtonSelect.setEnabled(true);
    }//GEN-LAST:event_jListUsersValueChanged

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            UserListWindow dialog = new UserListWindow(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSelect;
    private javax.swing.JList<String> jListUsers;
    private javax.swing.JScrollPane jScrollPaneUserList;
    // End of variables declaration//GEN-END:variables
}
