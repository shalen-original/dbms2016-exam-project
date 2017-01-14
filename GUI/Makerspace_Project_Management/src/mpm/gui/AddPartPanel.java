/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */
package mpm.gui;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import mpm.data.dao.* ;
import mpm.data.entities.*;
import mpm.data.logic.Pair;
import mpm.main.MPM;

/**
 *
 * @author Mikel
 */
public class AddPartPanel extends javax.swing.JPanel {

    /**
     * Creates new form AddPartPanel
     */
    boolean exists = false;
    public AddPartPanel() {
        initComponents();
        
        DefaultComboBoxModel<User> m = new DefaultComboBoxModel<>();
        for (User user : DAOs.users.getAll())
        {   for( Pair pair :DAOs.participations.getUserParticipatingToProjectWithRole(MPM.currentProject.getId())) 
        {
            if(((User)pair.getSecond()).getName().equals(user.getName()))
           exists=true;
        }
        if(!exists)
         m.addElement(user);
        exists=false;
        }
        if(m.getSize()!=0){
        cmbUser.setModel(m);
        cmbUser.setRenderer((a, value, c, d, e) -> {
            BasicComboBoxRenderer w = (BasicComboBoxRenderer)(new BasicComboBoxRenderer())
                                            .getListCellRendererComponent(a,value,c,d,e);
            w.setText(value.getName() + " (" + value.getEmail() + ")");
            return w;
        });
        }
        cmbRole.setModel(new DefaultComboBoxModel<>(ProjectRole.values()));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ParticipantLabel = new javax.swing.JLabel();
        RoleLabel = new javax.swing.JLabel();
        AddButton = new javax.swing.JButton();
        cmbUser = new javax.swing.JComboBox<>();
        cmbRole = new javax.swing.JComboBox<>();

        ParticipantLabel.setText("User :");

        RoleLabel.setText("Project Role :");

        AddButton.setText("Add");
        AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(148, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(AddButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RoleLabel)
                            .addComponent(ParticipantLabel))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbUser, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbRole, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(149, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ParticipantLabel)
                    .addComponent(cmbUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(RoleLabel)
                    .addComponent(cmbRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(83, 83, 83)
                .addComponent(AddButton)
                .addContainerGap(80, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonActionPerformed
        
        Participation p = new Participation(DAOs.participations.getNextValidId());
        p.setProjectId(MPM.currentProject.getId());
        p.setRole((ProjectRole)cmbRole.getSelectedItem());
        p.setUserId(((User)cmbUser.getSelectedItem()).getId());

        try
        {
            DAOs.participations.insert(p);
        }catch(RuntimeException ex){
            JOptionPane.showMessageDialog(this, "Oops, something went wrong. \n " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        JOptionPane.showMessageDialog(this, "Operation successful!");
        
        
        
    }//GEN-LAST:event_AddButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddButton;
    private javax.swing.JLabel ParticipantLabel;
    private javax.swing.JLabel RoleLabel;
    private javax.swing.JComboBox<ProjectRole> cmbRole;
    private javax.swing.JComboBox<User> cmbUser;
    // End of variables declaration//GEN-END:variables
}
