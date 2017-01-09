/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpm.gui;

import mpm.main.MPM;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
                
        projectTabbedPane.setIconAt(0, overviewIcon);
        projectTabbedPane.setIconAt(1, personsIcon);
        projectTabbedPane.setIconAt(2, settingsIcon);
        
        titleLabel.setText(MPM.currentProject.getTitle());
        descriptionArea.setText(MPM.currentProject.getDescription());
        
        if(!MPM.currentProject.isCurrentUserAdmin()) {
            projectTabbedPane.remove(settingsPanel);
        }
        
        // TODO: add list of users for current project
        /*ArrayList<UserListElement> list = new ArrayList<>();
        for(String user : test.userList){
            
            UserListElement el = new UserListElement();
            el.nameLabel.setText(user);
            list.add(el);
        }
        
        for(UserListElement el : list){
            userListPanel.add(el);
        }*/
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        projectTabbedPane = new javax.swing.JTabbedPane();
        overViewPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        descriptionArea = new javax.swing.JTextArea();
        descriptionLabel = new javax.swing.JLabel();
        userListPanel = new javax.swing.JPanel();
        settingsPanel = new javax.swing.JPanel();
        BookFreeInfButton = new javax.swing.JButton();
        BuyMatButton = new javax.swing.JButton();
        MakeReqButton = new javax.swing.JButton();
        AddPartButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(640, 480));
        setMinimumSize(new java.awt.Dimension(640, 480));
        setPreferredSize(new java.awt.Dimension(640, 480));

        projectTabbedPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);

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

        userListPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        projectTabbedPane.addTab("Persons", userListPanel);

        BookFreeInfButton.setText("Book Free Inf");
        BookFreeInfButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BookFreeInfButtonActionPerformed(evt);
            }
        });

        BuyMatButton.setText("Buy Material");
        BuyMatButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuyMatButtonActionPerformed(evt);
            }
        });

        MakeReqButton.setText("Make a Request");
        MakeReqButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MakeReqButtonActionPerformed(evt);
            }
        });

        AddPartButton.setText("Add Participant");
        AddPartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPartButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout settingsPanelLayout = new javax.swing.GroupLayout(settingsPanel);
        settingsPanel.setLayout(settingsPanelLayout);
        settingsPanelLayout.setHorizontalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingsPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BookFreeInfButton)
                    .addComponent(BuyMatButton))
                .addGap(39, 39, 39)
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddPartButton)
                    .addComponent(MakeReqButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        settingsPanelLayout.setVerticalGroup(
            settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, settingsPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BookFreeInfButton)
                    .addComponent(MakeReqButton))
                .addGap(18, 18, 18)
                .addGroup(settingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BuyMatButton)
                    .addComponent(AddPartButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(projectTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE))
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

    private void BuyMatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuyMatButtonActionPerformed
        // TODO add your handling code here:
        JOptionPane.showOptionDialog(null, new BuyMatPanel(),"Buying", 
                JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, 
                null, new Object[]{}, null);

    }//GEN-LAST:event_BuyMatButtonActionPerformed

    private void MakeReqButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MakeReqButtonActionPerformed
        // TODO add your handling code here:
        JOptionPane.showOptionDialog(null, new MakeReqPanel(),"Request", 
                JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, 
                null, new Object[]{}, null);
    }//GEN-LAST:event_MakeReqButtonActionPerformed

    private void AddPartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPartButtonActionPerformed
        // TODO add your handling code here:
        JOptionPane.showOptionDialog(null, new AddPartPanel(),"Participant", 
                JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, 
                null, new Object[]{}, null);
    }//GEN-LAST:event_AddPartButtonActionPerformed

    private void BookFreeInfButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BookFreeInfButtonActionPerformed
        // TODO add your handling code here:
        JOptionPane.showOptionDialog(null, new BookInfPanel(),"Booking", 
                JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, 
                null, new Object[]{}, null);
    }//GEN-LAST:event_BookFreeInfButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddPartButton;
    private javax.swing.JButton BookFreeInfButton;
    private javax.swing.JButton BuyMatButton;
    private javax.swing.JButton MakeReqButton;
    private javax.swing.JButton backButton;
    private javax.swing.JTextArea descriptionArea;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel overViewPanel;
    private javax.swing.JTabbedPane projectTabbedPane;
    private javax.swing.JPanel settingsPanel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel userListPanel;
    // End of variables declaration//GEN-END:variables
}
