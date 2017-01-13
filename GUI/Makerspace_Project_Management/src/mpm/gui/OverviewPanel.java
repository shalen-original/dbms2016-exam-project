/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpm.gui;

import java.awt.BorderLayout;
import mpm.main.MPM;
import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;
import mpm.data.dao.DAOs;
import mpm.data.entities.Project;

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
        
        ImageIcon addIcon = new ImageIcon(new ImageIcon(getClass()
                .getResource("/mpm/res/addLogo.png"))
                .getImage().getScaledInstance(
                32, 32, Image.SCALE_DEFAULT)); 
        
        overviewTabbedPane.setIconAt(0, projectsIcon);
        overviewTabbedPane.setIconAt(1, settingsIcon);
        overviewTabbedPane.setIconAt(2, addIcon);
        //projectListPanel.setLayout(new GridLayout(0, 1));
        
        addProjectPanel.add(new CreateProjectPanel(),BorderLayout.CENTER);
        //projectScrollPane.setViewportView (projectListPanel);
        reloadProjectTable();
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
        settingsTab = new javax.swing.JPanel();
        addProjectPanel = new javax.swing.JPanel();
        logoutButton = new javax.swing.JButton();
        openSelectedButton = new javax.swing.JButton();

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

        javax.swing.GroupLayout projectTabLayout = new javax.swing.GroupLayout(projectTab);
        projectTab.setLayout(projectTabLayout);
        projectTabLayout.setHorizontalGroup(
            projectTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(projectTableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
        );
        projectTabLayout.setVerticalGroup(
            projectTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(projectTableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
        );

        overviewTabbedPane.addTab("Projects", null, projectTab, "");

        settingsTab.setPreferredSize(new java.awt.Dimension(526, 433));

        javax.swing.GroupLayout settingsTabLayout = new javax.swing.GroupLayout(settingsTab);
        settingsTab.setLayout(settingsTabLayout);
        settingsTabLayout.setHorizontalGroup(
            settingsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 521, Short.MAX_VALUE)
        );
        settingsTabLayout.setVerticalGroup(
            settingsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 393, Short.MAX_VALUE)
        );

        overviewTabbedPane.addTab("Preferences", settingsTab);

        addProjectPanel.setLayout(new java.awt.BorderLayout());
        overviewTabbedPane.addTab("New Project", addProjectPanel);

        logoutButton.setText("logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        openSelectedButton.setText("open Selected Project");
        openSelectedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openSelectedButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(openSelectedButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(overviewTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(overviewTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logoutButton)
                    .addComponent(openSelectedButton))
                .addContainerGap())
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

    private void reloadProjectTable(){
        
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
    private javax.swing.JPanel addProjectPanel;
    private javax.swing.JButton logoutButton;
    private javax.swing.JButton openSelectedButton;
    private javax.swing.JTabbedPane overviewTabbedPane;
    private javax.swing.JPanel projectTab;
    private javax.swing.JTable projectTable;
    private javax.swing.JScrollPane projectTableScrollPane;
    private javax.swing.JPanel settingsTab;
    // End of variables declaration//GEN-END:variables
}
