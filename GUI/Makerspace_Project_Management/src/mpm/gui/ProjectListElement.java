/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpm.gui;

import java.awt.*;
import javax.swing.*;
import mpm.data.entities.Project;
import mpm.main.MPM;

/**
 *
 * @author remo
 */
public class ProjectListElement extends javax.swing.JPanel {
    
    private Project project;
    /**
     * Creates new form ProjectListElement
     */
    public ProjectListElement(Project project) {
        initComponents();
        
        this.project = project;
        
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(
                getClass().getResource("/mpm/res/toolLogo.png"))
                .getImage().getScaledInstance(
                        76, 76, Image.SCALE_DEFAULT));
        
        imageLabel.setIcon(imageIcon);
        titleLabel.setText(project.getTitle());
        descriptionLabel.setText(project.getDescription());
        statusLabel.setText(project.getStatus().toString());
        
        if(project.isCurrentUserAdmin()){
            roleLabel.setForeground(Color.green);
            roleLabel.setText("Administrator");
        }else{
            roleLabel.setText("Collaborator");
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

        imageLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        statusLabel = new javax.swing.JLabel();
        roleLabel = new javax.swing.JLabel();
        roleTitleLabel = new javax.swing.JLabel();
        statusTitleLabel = new javax.swing.JLabel();
        projectButton = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(450, 100));
        setMinimumSize(new java.awt.Dimension(450, 100));
        setPreferredSize(new java.awt.Dimension(450, 100));

        titleLabel.setText("jLabel1");

        descriptionLabel.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        descriptionLabel.setText("jLabel1");

        statusLabel.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        statusLabel.setText("jLabel1");

        roleLabel.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        roleLabel.setText("jLabel1");

        roleTitleLabel.setText("Role");

        statusTitleLabel.setText("Status");

        projectButton.setBorderPainted(false);
        projectButton.setContentAreaFilled(false);
        projectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                    .addComponent(descriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(roleTitleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(roleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(statusTitleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(projectButton, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(descriptionLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(roleLabel)
                            .addComponent(statusLabel)
                            .addComponent(roleTitleLabel)
                            .addComponent(statusTitleLabel))))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(projectButton, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
        );

        projectButton.getAccessibleContext().setAccessibleName("projectButton");
    }// </editor-fold>//GEN-END:initComponents

    private void projectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projectButtonActionPerformed
        
        MPM.currentProject = project;
        MPM.setPanel(new ProjectPanel());
    }//GEN-LAST:event_projectButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JButton projectButton;
    private javax.swing.JLabel roleLabel;
    private javax.swing.JLabel roleTitleLabel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel statusTitleLabel;
    public javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
