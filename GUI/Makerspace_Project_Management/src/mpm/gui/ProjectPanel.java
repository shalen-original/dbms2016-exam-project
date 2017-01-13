/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpm.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import mpm.main.MPM;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.Border;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import mpm.data.dao.DAOs;
import mpm.data.entities.Participation;
import mpm.data.entities.ProjectRole;
import mpm.data.entities.User;
import mpm.data.logic.Pair;

/**
 *
 * @author remo & Mikel
 */
public class ProjectPanel extends javax.swing.JPanel {
    
    /**
     * Creates new form projectPanel
     */
    public ProjectPanel() {
        
        initComponents();
        
        ImageIcon overviewIcon = new ImageIcon(new ImageIcon(getClass()
                .getResource("/mpm/res/overviewLogo.png"))
                .getImage().getScaledInstance(
                32, 32, Image.SCALE_DEFAULT));
        
        ImageIcon personsIcon = new ImageIcon(new ImageIcon(getClass()
                .getResource("/mpm/res/personLogo.png"))
                .getImage().getScaledInstance(
                32, 32, Image.SCALE_DEFAULT));
        
        ImageIcon settingsIcon = new ImageIcon(new ImageIcon(getClass()
                .getResource("/mpm/res/settingsLogo.png"))
                .getImage().getScaledInstance(
                32, 32, Image.SCALE_DEFAULT));
        
        ImageIcon buyIcon = new ImageIcon(new ImageIcon(getClass()
                .getResource("/mpm/res/buy.png"))
                .getImage().getScaledInstance(
                32, 32, Image.SCALE_DEFAULT));
        
        ImageIcon reqIcon = new ImageIcon(new ImageIcon(getClass()
                .getResource("/mpm/res/request.png"))
                .getImage().getScaledInstance(
                32, 32, Image.SCALE_DEFAULT));
        
        ImageIcon addUserIcon = new ImageIcon(new ImageIcon(getClass()
                .getResource("/mpm/res/addPersonLogo.png"))
                .getImage().getScaledInstance(
                32, 32, Image.SCALE_DEFAULT));
        
        ImageIcon bookingIcon = new ImageIcon(new ImageIcon(getClass()
                .getResource("/mpm/res/booking.png"))
                .getImage().getScaledInstance(
                32, 32, Image.SCALE_DEFAULT));
                
        projectTabbedPane.setIconAt(0, overviewIcon);
        projectTabbedPane.setIconAt(1, personsIcon);
        projectTabbedPane.setIconAt(2, bookingIcon);
        projectTabbedPane.setIconAt(3, reqIcon);
        projectTabbedPane.setIconAt(4, addUserIcon);
        projectTabbedPane.setIconAt(5, buyIcon);
        projectTabbedPane.setIconAt(6, settingsIcon);
        
        titleLabel.setText(MPM.currentProject.getTitle());
        descriptionArea.setText(MPM.currentProject.getDescription());
        
        bookingPanel.add(new BookInfPanel(),BorderLayout.CENTER);
        requestPanel.add(new MakeReqPanel(),BorderLayout.CENTER);
        addUserPanel.add(new AddPartPanel(),BorderLayout.CENTER);
        materialsPanel.add(new BuyMatPanel(),BorderLayout.CENTER);
        
        if(!DAOs.participations.isUserAdminInProject(MPM.currentUser, MPM.currentProject)) {
            // TODO choose which panels to keep visualized for non-admin users
            projectTabbedPane.remove(settingsPanel);
            projectTabbedPane.remove(bookingPanel);
            projectTabbedPane.remove(addUserPanel);
            projectTabbedPane.remove(requestPanel);
        }     
               
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userListPanel = new javax.swing.JPanel();
        userScrollPane = new javax.swing.JScrollPane();
        projectTabbedPane = new javax.swing.JTabbedPane();
        overViewPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        descriptionArea = new javax.swing.JTextArea();
        descriptionLabel = new javax.swing.JLabel();
        userPanel = new javax.swing.JPanel();
        userTableScrollPane = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        selectedLabel = new javax.swing.JLabel();
        selectedUserNameLabel = new javax.swing.JLabel();
        changeRoleLabel = new javax.swing.JLabel();
        selectedUserComboBox = new javax.swing.JComboBox<>();
        deleteUserButton = new javax.swing.JButton();
        bookingPanel = new javax.swing.JPanel();
        requestPanel = new javax.swing.JPanel();
        addUserPanel = new javax.swing.JPanel();
        materialsPanel = new javax.swing.JPanel();
        settingsPanel = new javax.swing.JPanel();
        backButton = new javax.swing.JButton();

        userListPanel.setMaximumSize(new java.awt.Dimension(51131, 45125));
        userListPanel.setRequestFocusEnabled(false);
        userListPanel.setLayout(new javax.swing.BoxLayout(userListPanel, javax.swing.BoxLayout.PAGE_AXIS));

        userScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        userScrollPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        userScrollPane.setMaximumSize(new java.awt.Dimension(526, 3650));
        userScrollPane.setPreferredSize(new java.awt.Dimension(526, 433));

        setMaximumSize(new java.awt.Dimension(640, 480));
        setMinimumSize(new java.awt.Dimension(640, 480));
        setPreferredSize(new java.awt.Dimension(640, 480));

        projectTabbedPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        projectTabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                OnTabChange(evt);
            }
        });

        titleLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        titleLabel.setText("jLabel1");

        jScrollPane2.setBorder(null);

        descriptionArea.setBackground(javax.swing.UIManager.getDefaults().getColor("Panel.background"));
        descriptionArea.setColumns(20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setRows(3);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setBorder(null);
        jScrollPane2.setViewportView(descriptionArea);

        descriptionLabel.setText("Description");

        javax.swing.GroupLayout overViewPanelLayout = new javax.swing.GroupLayout(overViewPanel);
        overViewPanel.setLayout(overViewPanelLayout);
        overViewPanelLayout.setHorizontalGroup(
            overViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(overViewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(overViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(overViewPanelLayout.createSequentialGroup()
                        .addGroup(overViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titleLabel)
                            .addComponent(descriptionLabel))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        overViewPanelLayout.setVerticalGroup(
            overViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(overViewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addGap(40, 40, 40)
                .addComponent(descriptionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        projectTabbedPane.addTab("Overview", overViewPanel);

        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        userTableScrollPane.setViewportView(userTable);

        selectedLabel.setText("Selected User:");

        selectedUserNameLabel.setText("jLabel2");

        changeRoleLabel.setText("Change role:");

        selectedUserComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        selectedUserComboBox.setEnabled(false);

        deleteUserButton.setText("remove user from project");
        deleteUserButton.setEnabled(false);

        javax.swing.GroupLayout userPanelLayout = new javax.swing.GroupLayout(userPanel);
        userPanel.setLayout(userPanelLayout);
        userPanelLayout.setHorizontalGroup(
            userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(userTableScrollPane)
            .addGroup(userPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userPanelLayout.createSequentialGroup()
                        .addComponent(selectedLabel)
                        .addGap(18, 18, 18)
                        .addComponent(selectedUserNameLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(userPanelLayout.createSequentialGroup()
                        .addComponent(changeRoleLabel)
                        .addGap(31, 31, 31)
                        .addComponent(selectedUserComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                        .addComponent(deleteUserButton)
                        .addGap(39, 39, 39))))
        );
        userPanelLayout.setVerticalGroup(
            userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userPanelLayout.createSequentialGroup()
                .addComponent(userTableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectedLabel)
                    .addComponent(selectedUserNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(userPanelLayout.createSequentialGroup()
                        .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(selectedUserComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(changeRoleLabel))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userPanelLayout.createSequentialGroup()
                        .addComponent(deleteUserButton)
                        .addGap(23, 23, 23))))
        );

        projectTabbedPane.addTab("Persons", userPanel);

        bookingPanel.setLayout(new java.awt.BorderLayout());
        projectTabbedPane.addTab("Booking", bookingPanel);

        requestPanel.setLayout(new java.awt.BorderLayout());
        projectTabbedPane.addTab("Request", requestPanel);

        addUserPanel.setLayout(new java.awt.BorderLayout());
        projectTabbedPane.addTab("Add User", addUserPanel);

        materialsPanel.setLayout(new java.awt.BorderLayout());
        projectTabbedPane.addTab("Materials", materialsPanel);

        javax.swing.GroupLayout settingsPanelLayout = new javax.swing.GroupLayout(settingsPanel);
        settingsPanel.setLayout(settingsPanelLayout);
        settingsPanelLayout.setHorizontalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );
        settingsPanelLayout.setVerticalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 393, Short.MAX_VALUE)
        );

        projectTabbedPane.addTab("Settings", settingsPanel);

        backButton.setText("back to overview");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(backButton))
                    .addComponent(projectTabbedPane))
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(projectTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backButton)
                .addContainerGap())
        );

        projectTabbedPane.getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        
        MPM.setPanel(new OverviewPanel());
    }//GEN-LAST:event_backButtonActionPerformed

    private void OnTabChange(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_OnTabChange
       
        JTabbedPane e = (JTabbedPane)evt.getSource();
        
        if (e.getSelectedComponent().equals(userPanel))
            reloadUserTable();
        
    }//GEN-LAST:event_OnTabChange

    private void listSelectionChanged(ListSelectionEvent e){
    
        int userID = (Integer) userTableModel.getValueAt(userTable
                .getSelectedRow(), 0);
        
        selectedUserNameLabel.setText(DAOs.users.findByID(userID).getName());
        
        if(DAOs.participations.isUserAdminInProject(
                MPM.currentUser.getId(), MPM.currentProject.getId())){
            
            selectedUserComboBox.setEnabled(true);
            deleteUserButton.setEnabled(true);
        }
        
    }
    
    private void reloadProjectUsers()
    {
        userListPanel.removeAll();
        
        userListPanel.setLayout(new GridLayout(0, 1));//rscolati@unibz.it
        
        List<Pair<Participation, User>> list = 
            DAOs.participations.getUserParticipatingToProjectWithRole(MPM.currentProject.getId());
        
        for(Pair<Participation, User> p : list)
        {
            UserListElement el = new UserListElement(p.getSecond(), p.getFirst().getRole());
            userListPanel.add(el);
        }
        userScrollPane.setViewportView (userListPanel);
    }
    
    private void reloadUserTable(){
        
        List<Participation> participationList = DAOs.participations
                .findByProjectID(MPM.currentProject.getId());
        
        userTable = new javax.swing.JTable();
        userTable.setAutoCreateRowSorter(true);
                
        String[] projectColumnNames = {"Id",
            "Name",
            "Email",
            "Project Role",
            "Role"};
        
        userTableModel = new DefaultTableModel(
                0, projectColumnNames.length);
        
        for(Participation p : participationList){
            Object[] o = new Object[5];
            o[0] = p.getUserId();
            o[1] = DAOs.users.findByID(p.getUserId()).getName();
            o[2] = DAOs.users.findByID(p.getUserId()).getEmail();
            o[3] = p.getRole().toString();
            // Ok, this one is really ugly, sorry. I got carried away.
            o[4] = DAOs.roles.findByID(
                    DAOs.users.findByID(p.getUserId())
                            .getGeneralRoleId()).getName();
                       
            userTableModel.addRow(o);

        }
        
        userTable.setModel(userTableModel);
        userTableModel.setColumnIdentifiers(projectColumnNames);
        userTableScrollPane.setViewportView(userTable);
        userTable.setDefaultEditor(Object.class, null);
        
        selectedUserNameLabel.setText("<none>");
        
        if(DAOs.participations.isUserAdminInProject(
                MPM.currentUser.getId(), MPM.currentProject.getId())){
            
            selectedUserComboBox.setEnabled(false);
            deleteUserButton.setEnabled(false);
        }else{
        
            userPanel.remove(selectedUserComboBox);
            userPanel.remove(deleteUserButton);
            userPanel.remove(changeRoleLabel);
        }
          
        userTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                listSelectionChanged(e);}
        });
    }
    
    private DefaultTableModel userTableModel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addUserPanel;
    private javax.swing.JButton backButton;
    private javax.swing.JPanel bookingPanel;
    private javax.swing.JLabel changeRoleLabel;
    private javax.swing.JButton deleteUserButton;
    private javax.swing.JTextArea descriptionArea;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel materialsPanel;
    private javax.swing.JPanel overViewPanel;
    private javax.swing.JTabbedPane projectTabbedPane;
    private javax.swing.JPanel requestPanel;
    private javax.swing.JLabel selectedLabel;
    private javax.swing.JComboBox<String> selectedUserComboBox;
    private javax.swing.JLabel selectedUserNameLabel;
    private javax.swing.JPanel settingsPanel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel userListPanel;
    private javax.swing.JPanel userPanel;
    private javax.swing.JScrollPane userScrollPane;
    private javax.swing.JTable userTable;
    private javax.swing.JScrollPane userTableScrollPane;
    // End of variables declaration//GEN-END:variables
}
