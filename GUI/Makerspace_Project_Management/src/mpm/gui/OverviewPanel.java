/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpm.gui;

import mpm.main.MPM;
import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;
import mpm.data.dao.DAOs;
import mpm.data.entities.GeneralRole;
import mpm.data.entities.Project;
import mpm.data.entities.ProjectStatus;

/**
 *
 * @author remo
 */
public class OverviewPanel extends javax.swing.JPanel {
    
    /**
     * Creates new form projectPanel
     */
    public OverviewPanel() {
        initComponents();

        
        ImageIcon projectsIcon = new ImageIcon(new ImageIcon(getClass()
                .getResource("/mpm/res/toolLogo.png"))
                .getImage().getScaledInstance(
                32, 32, Image.SCALE_DEFAULT));
            
        ImageIcon settingsIcon = new ImageIcon(new ImageIcon(getClass()
                .getResource("/mpm/res/settingsLogo.png"))
                .getImage().getScaledInstance(
                32, 32, Image.SCALE_DEFAULT));
        
        ImageIcon inventoryIcon = new ImageIcon(new ImageIcon(getClass()
                .getResource("/mpm/res/inventoryLogo.png"))
                .getImage().getScaledInstance(
                32, 32, Image.SCALE_DEFAULT));
        
        ImageIcon freeInfIcon = new ImageIcon(new ImageIcon(getClass()
                .getResource("/mpm/res/booking.png"))
                .getImage().getScaledInstance(
                32, 32, Image.SCALE_DEFAULT));
        
        ImageIcon techInfIcon = new ImageIcon(new ImageIcon(getClass()
                .getResource("/mpm/res/techInfLogo.png"))
                .getImage().getScaledInstance(
                32, 32, Image.SCALE_DEFAULT));
        
        // Icon for Request List
        ImageIcon requestListIcon = new ImageIcon(new ImageIcon(getClass()
                .getResource("/mpm/res/requestBellLogo.png"))
                .getImage().getScaledInstance(
                32, 32, Image.SCALE_DEFAULT));
        
        overviewTabbedPane.setIconAt(0, projectsIcon);
        overviewTabbedPane.setIconAt(1, settingsIcon);
        //projectListPanel.setLayout(new GridLayout(0, 1));
        
        GeneralRole currR = DAOs.roles.findByID(MPM.currentUser.getGeneralRoleId());
        
        nameLabel.setText(MPM.currentUser.getName());
        emailLabel.setText(MPM.currentUser.getEmail());
        roleLabel.setText(currR.getName());
        descriptionLabel.setText("(" + currR.getDescription() + ")");
        idLabel.setText("" + MPM.currentUser.getId());
        
        //projectScrollPane.setViewportView (projectListPanel);
        reloadProjectTable();
        
        /*
            Parts dedicated to Makerspace technicians
        */
        
        if (DAOs.users.hasUserRole(MPM.currentUser, "Staff"))
        { 
            overviewTabbedPane.insertTab("Inventory", 
                    inventoryIcon, new MaterialInventoryManagement(), "", 2);
            overviewTabbedPane.insertTab("Free Infs", 
                    freeInfIcon, new FreeInfManagement(), "", 3);
            overviewTabbedPane.insertTab("Technical Infs", 
                    techInfIcon, new TechInfManagement(), "", 4);
            overviewTabbedPane.insertTab("Requests", 
                    requestListIcon, new RequestsManagement(), "", 5);
            // TODO Change JPanel with RequestListPanel or however it will be called
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

        overviewTabbedPane = new javax.swing.JTabbedPane();
        projectTab = new javax.swing.JPanel();
        projectTableScrollPane = new javax.swing.JScrollPane();
        projectTable = new javax.swing.JTable();
        newProjectLabel = new javax.swing.JLabel();
        defaultStatusLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtProjectName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taProjectDescription = new javax.swing.JTextArea();
        chbCollaboration = new javax.swing.JCheckBox();
        createButton = new javax.swing.JButton();
        openSelectedButton = new javax.swing.JButton();
        settingsTab = new javax.swing.JPanel();
        nameTitleLabel = new javax.swing.JLabel();
        emailTitleLabel = new javax.swing.JLabel();
        roleTitleLabel = new javax.swing.JLabel();
        idTitleLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        roleLabel = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        idLabel = new javax.swing.JLabel();
        updateProfileLabel = new javax.swing.JLabel();
        newEmailLabel = new javax.swing.JLabel();
        newEmailTextField = new javax.swing.JTextField();
        updateInformationButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(640, 480));
        setMinimumSize(new java.awt.Dimension(640, 480));
        setPreferredSize(new java.awt.Dimension(640, 480));

        overviewTabbedPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        overviewTabbedPane.setMaximumSize(new java.awt.Dimension(635, 400));
        overviewTabbedPane.setMinimumSize(new java.awt.Dimension(600, 400));
        overviewTabbedPane.setPreferredSize(new java.awt.Dimension(600, 400));
        overviewTabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                overviewTabbedPaneStateChanged(evt);
            }
        });

        projectTab.setMaximumSize(new java.awt.Dimension(526, 433));

        projectTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        projectTableScrollPane.setViewportView(projectTable);

        newProjectLabel.setText("Add new Project");

        defaultStatusLabel.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        defaultStatusLabel.setText("Default Status: Proposed");

        jLabel1.setText("Title");

        jLabel2.setText("Description");

        taProjectDescription.setColumns(20);
        taProjectDescription.setLineWrap(true);
        taProjectDescription.setRows(5);
        taProjectDescription.setWrapStyleWord(true);
        jScrollPane1.setViewportView(taProjectDescription);

        chbCollaboration.setText("Open for Collaboration");

        createButton.setText("Create Project");
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        openSelectedButton.setText("Open Selected Project");
        openSelectedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openSelectedButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout projectTabLayout = new javax.swing.GroupLayout(projectTab);
        projectTab.setLayout(projectTabLayout);
        projectTabLayout.setHorizontalGroup(
            projectTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(projectTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(projectTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(projectTableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                    .addGroup(projectTabLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(projectTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chbCollaboration, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(createButton, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(projectTabLayout.createSequentialGroup()
                        .addComponent(newProjectLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(openSelectedButton))
                    .addGroup(projectTabLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(projectTabLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtProjectName, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(defaultStatusLabel)))
                .addContainerGap())
        );
        projectTabLayout.setVerticalGroup(
            projectTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(projectTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(projectTableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(projectTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(projectTabLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(openSelectedButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, projectTabLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(newProjectLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(projectTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtProjectName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(defaultStatusLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(12, 12, 12)
                .addGroup(projectTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, projectTabLayout.createSequentialGroup()
                        .addComponent(chbCollaboration)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(createButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        overviewTabbedPane.addTab("Projects", null, projectTab, "");

        settingsTab.setPreferredSize(new java.awt.Dimension(526, 433));

        nameTitleLabel.setText("Name");

        emailTitleLabel.setText("Email");

        roleTitleLabel.setText("Role");

        idTitleLabel.setText("ID");

        nameLabel.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        nameLabel.setText("jLabel9");

        emailLabel.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        emailLabel.setText("jLabel10");

        roleLabel.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        roleLabel.setText("jLabel11");

        descriptionLabel.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        descriptionLabel.setText("jLabel12");

        idLabel.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        idLabel.setText("jLabel13");

        updateProfileLabel.setText("Update Profile Information");

        newEmailLabel.setText("New email address");

        updateInformationButton.setText("Save");
        updateInformationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateInformationButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout settingsTabLayout = new javax.swing.GroupLayout(settingsTab);
        settingsTab.setLayout(settingsTabLayout);
        settingsTabLayout.setHorizontalGroup(
            settingsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsTabLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(settingsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(updateInformationButton)
                    .addComponent(updateProfileLabel)
                    .addGroup(settingsTabLayout.createSequentialGroup()
                        .addGroup(settingsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameTitleLabel)
                            .addComponent(emailTitleLabel)
                            .addComponent(roleTitleLabel)
                            .addComponent(idTitleLabel))
                        .addGap(18, 18, 18)
                        .addGroup(settingsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idLabel)
                            .addGroup(settingsTabLayout.createSequentialGroup()
                                .addComponent(roleLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(descriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(emailLabel)
                            .addComponent(nameLabel)))
                    .addGroup(settingsTabLayout.createSequentialGroup()
                        .addComponent(newEmailLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(newEmailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(138, Short.MAX_VALUE))
        );
        settingsTabLayout.setVerticalGroup(
            settingsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsTabLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(settingsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTitleLabel)
                    .addComponent(nameLabel))
                .addGap(18, 18, 18)
                .addGroup(settingsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailTitleLabel)
                    .addComponent(emailLabel))
                .addGap(18, 18, 18)
                .addGroup(settingsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roleTitleLabel)
                    .addComponent(roleLabel)
                    .addComponent(descriptionLabel))
                .addGap(18, 18, 18)
                .addGroup(settingsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idTitleLabel)
                    .addComponent(idLabel))
                .addGap(114, 114, 114)
                .addComponent(updateProfileLabel)
                .addGap(18, 18, 18)
                .addGroup(settingsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newEmailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newEmailLabel))
                .addGap(18, 18, 18)
                .addComponent(updateInformationButton)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        overviewTabbedPane.addTab("Profile", settingsTab);

        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(overviewTabbedPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(overviewTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logoutButton)
                .addGap(27, 27, 27))
        );

        overviewTabbedPane.getAccessibleContext().setAccessibleName("Projects");
    }// </editor-fold>//GEN-END:initComponents

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        
        MPM.setPanel(new LoginPanel());
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void overviewTabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_overviewTabbedPaneStateChanged
        
        JTabbedPane e = (JTabbedPane)evt.getSource();
        
        if (e.getSelectedComponent().equals(projectTab))
            reloadProjectTable();
            
        openSelectedButton.setEnabled(
                e.getSelectedComponent().equals(projectTab));
        
    }//GEN-LAST:event_overviewTabbedPaneStateChanged

    private void openSelectedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openSelectedButtonActionPerformed
        
        int row = projectTable.getSelectedRow();

        if(row == -1){
            JOptionPane.showMessageDialog(this, "No Project selected.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            int projectID = (Integer) projectTableModel
                    .getValueAt(row, 0);
            
            MPM.currentProject = DAOs.projects.findByID(projectID);
            MPM.setPanel(new ProjectPanel());
        }
    }//GEN-LAST:event_openSelectedButtonActionPerformed

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
        
        if (txtProjectName.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Insert a project name", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Project p = new Project(DAOs.projects.getNextValidId());
        p.setTitle(txtProjectName.getText());
        p.setDescription(taProjectDescription.getText());
        p.setSeekingCollaboration(chbCollaboration.isSelected());
        
        // By default new projects are proposed: they have to be set as active
        // by a Makerspace technician.
        p.setStatus(ProjectStatus.PROPOSED);

        try
        {
            DAOs.projects.createNewProjectWithAdmin(p, MPM.currentUser);
        }catch(RuntimeException ex){
            JOptionPane.showMessageDialog(this, "Oops, something went wrong. \n " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        JOptionPane.showMessageDialog(this, "Operation successful!");
        txtProjectName.setText("");
        taProjectDescription.setText("");
        chbCollaboration.setSelected(false);
        reloadProjectTable();
    }//GEN-LAST:event_createButtonActionPerformed

    private void updateInformationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateInformationButtonActionPerformed

        MPM.currentUser.setEmail(newEmailTextField.getText());
        
        try
        {
            DAOs.users.update(MPM.currentUser);
            emailLabel.setText(newEmailTextField.getText());
        }catch(RuntimeException ex){
            JOptionPane.showMessageDialog(this, "Oops, something went wrong. \n " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        JOptionPane.showMessageDialog(this, "Operation successful!");
        newEmailTextField.setText("");
        
    }//GEN-LAST:event_updateInformationButtonActionPerformed

    private void reloadProjectTable()
    {
        
        List<Project> projectList = DAOs
                .projects.getUserProjects(MPM.currentUser);
        
        projectTable = new javax.swing.JTable();
        projectTable.setAutoCreateRowSorter(true);
                
        String[] projectColumnNames = {"Id",
            "Title",
            "Description",
            "Status",
            "Role"};
        
        projectTableModel = new DefaultTableModel(
                0, projectColumnNames.length);
        
        for(Project p : projectList){
            Object[] o = new Object[5];
            o[0] = p.getId();
            o[1] = p.getTitle();
            o[2] = p.getDescription();
            o[3] = p.getStatus();
            o[4] = DAOs.participations.getUserRolesInProject(
                    MPM.currentUser.getId(), p.getId())
                    .get(0).toString();
                       
            projectTableModel.addRow(o);
        }
        
        projectTable.setModel(projectTableModel);
        projectTableModel.setColumnIdentifiers(projectColumnNames);
        projectTableScrollPane.setViewportView(projectTable);
        projectTable.setDefaultEditor(Object.class, null);
    }
    
    private DefaultTableModel projectTableModel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chbCollaboration;
    private javax.swing.JButton createButton;
    private javax.swing.JLabel defaultStatusLabel;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel emailTitleLabel;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel idTitleLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logoutButton;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel nameTitleLabel;
    private javax.swing.JLabel newEmailLabel;
    private javax.swing.JTextField newEmailTextField;
    private javax.swing.JLabel newProjectLabel;
    private javax.swing.JButton openSelectedButton;
    private javax.swing.JTabbedPane overviewTabbedPane;
    private javax.swing.JPanel projectTab;
    private javax.swing.JTable projectTable;
    private javax.swing.JScrollPane projectTableScrollPane;
    private javax.swing.JLabel roleLabel;
    private javax.swing.JLabel roleTitleLabel;
    private javax.swing.JPanel settingsTab;
    private javax.swing.JTextArea taProjectDescription;
    private javax.swing.JTextField txtProjectName;
    private javax.swing.JButton updateInformationButton;
    private javax.swing.JLabel updateProfileLabel;
    // End of variables declaration//GEN-END:variables
}
