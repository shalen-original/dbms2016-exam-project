/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */
package mpm.gui;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import mpm.data.dao.DAOs;
import mpm.data.entities.*;
import mpm.main.MPM;

/**
 *
 * @author Shalen
 */
public class RegisterPanel extends javax.swing.JPanel {

    /**
     * Creates new form RegisterPanel
     */
    public RegisterPanel() {
        initComponents();
        cmbRole.setRenderer((a, value, c, d, e) -> {
            BasicComboBoxRenderer w = (BasicComboBoxRenderer)(new BasicComboBoxRenderer())
                                            .getListCellRendererComponent(a,value,c,d,e);
            if (value != null)
                w.setText(value.getName());
            return w;
        });
        
        DAOs.roles.getAll().forEach(cmbRole::addItem);
        
   }
 

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        surnameLabel = new javax.swing.JLabel();
        surnameTextField = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        roleLabel = new javax.swing.JLabel();
        cmbRole = new javax.swing.JComboBox<>();
        backButton = new javax.swing.JButton();
        registerButton = new javax.swing.JButton();

        nameLabel.setText("Name :");

        surnameLabel.setText("Surname:");

        emailLabel.setText("Email:");

        roleLabel.setText("Role:");

        cmbRole.setModel(new javax.swing.DefaultComboBoxModel());

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        registerButton.setText("Register");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(156, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(surnameLabel)
                            .addComponent(roleLabel)
                            .addComponent(emailLabel)
                            .addComponent(nameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nameTextField)
                            .addComponent(surnameTextField)
                            .addComponent(emailTextField)
                            .addComponent(cmbRole, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(registerButton)))
                .addContainerGap(156, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(surnameLabel)
                    .addComponent(surnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roleLabel))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(registerButton))
                .addContainerGap(72, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        MPM.setPanel(new LoginPanel());
    }//GEN-LAST:event_backButtonActionPerformed

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        
        if (nameTextField.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "The name cannot be empty",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (surnameTextField.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "The surname cannot be empty",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (emailTextField.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "The email cannot be empty",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Pattern emailRegex = Pattern.compile("^[a-z0-9\\.\\-\\_]+@[a-z0-9\\.\\_]+\\.[a-z]{2,5}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailRegex .matcher(emailTextField.getText());
        if(!matcher.find())
            {
            JOptionPane.showMessageDialog(this, "The email is not in the right format.",
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        User u = new User(DAOs.users.getNextValidId());
        
        u.setName(nameTextField.getText() + " " + surnameTextField.getText());
        u.setEmail(emailTextField.getText());
        u.setGeneralRoleId(((GeneralRole)cmbRole.getSelectedItem()).getId());
        
        try
        {
           DAOs.users.insert(u);
        }catch(RuntimeException ex){
           JOptionPane.showMessageDialog(this, "Oops, something went wrong. \n " + ex.getMessage(), 
                   "Error", JOptionPane.ERROR_MESSAGE);
           return;
        }
        JOptionPane.showMessageDialog(null, "Operation succesful!");
        MPM.setPanel(new LoginPanel());
        
    }//GEN-LAST:event_registerButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JComboBox<GeneralRole> cmbRole;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JButton registerButton;
    private javax.swing.JLabel roleLabel;
    private javax.swing.JLabel surnameLabel;
    private javax.swing.JTextField surnameTextField;
    // End of variables declaration//GEN-END:variables
}
