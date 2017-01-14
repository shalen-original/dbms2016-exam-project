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
import mpm.main.MPM;

/**
 *
 * @author Mikel
 */
public class MakeReqPanel extends javax.swing.JPanel {

    /**
     * Creates new form MakeReqPanel
     */
    public MakeReqPanel() {
        initComponents();
        
        DefaultComboBoxModel<TechInf> m = new DefaultComboBoxModel<>();
        for (TechInf ti : DAOs.technical_infs.getAll())
        { 
            m.addElement(ti);
        }
        cmbTechInf.setModel(m);
        cmbTechInf.setRenderer((a, value, c, d, e) -> {
            BasicComboBoxRenderer w = (BasicComboBoxRenderer)(new BasicComboBoxRenderer())
                                            .getListCellRendererComponent(a,value,c,d,e);
            w.setText(value.getName());
            return w;
        });

        if (cmbTechInf.getItemCount() > 0)
            cmbTechInf.setSelectedIndex(0);
        
        lblAuthor.setText(MPM.currentUser.getName());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        choice1 = new java.awt.Choice();
        TitleLabel = new javax.swing.JLabel();
        txtTitle = new javax.swing.JTextField();
        RelatedInfLabel = new javax.swing.JLabel();
        MessageLabel = new javax.swing.JLabel();
        SenderLabel = new javax.swing.JLabel();
        SendButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        taMessage = new javax.swing.JTextArea();
        lblAuthor = new javax.swing.JLabel();
        viewHistoryButton = new javax.swing.JButton();
        cmbTechInf = new javax.swing.JComboBox<>();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        TitleLabel.setText("Title :");

        RelatedInfLabel.setText("Requested technical infrastructure:");

        MessageLabel.setText("Message:");

        SenderLabel.setText("Author:");

        SendButton.setText("Create Request");
        SendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendButtonActionPerformed(evt);
            }
        });

        taMessage.setColumns(20);
        taMessage.setRows(5);
        jScrollPane3.setViewportView(taMessage);

        lblAuthor.setText("authorLabel");
        lblAuthor.setText(mpm.main.MPM.currentUser.getName());

        viewHistoryButton.setText("View all Requests");
        viewHistoryButton.setMaximumSize(new java.awt.Dimension(109, 23));
        viewHistoryButton.setMinimumSize(new java.awt.Dimension(109, 23));
        viewHistoryButton.setPreferredSize(new java.awt.Dimension(109, 23));
        viewHistoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewHistoryButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(MessageLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(SendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addComponent(viewHistoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(RelatedInfLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbTechInf, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(TitleLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTitle))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(SenderLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(40, 40, 40))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TitleLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbTechInf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RelatedInfLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(MessageLabel)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SenderLabel)
                    .addComponent(lblAuthor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(viewHistoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendButtonActionPerformed
        
        Request r = new Request(DAOs.requests.getNextValidId());
        
        r.setAssignedUserId(-1); // By default, no technician is assigned to this request
        r.setProjectId(MPM.currentProject.getId());
        r.setTechInfId(((TechInf)cmbTechInf.getSelectedItem()).getId());
        r.setTitle(txtTitle.getText());
        
        try
        {
            DAOs.requests.createRequestWithMessage(r, taMessage.getText(), MPM.currentUser.getId());
        }catch(RuntimeException ex){
            JOptionPane.showMessageDialog(this, "Oops, something went wrong. \n " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        txtTitle.setText("");
        taMessage.setText("");
        cmbTechInf.setSelectedIndex(0);
        
    }//GEN-LAST:event_SendButtonActionPerformed

    private void viewHistoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewHistoryButtonActionPerformed
        JOptionPane.showMessageDialog(null, new RequestHistoryPanel(), 
                "Request History", JOptionPane.PLAIN_MESSAGE, null);
    }//GEN-LAST:event_viewHistoryButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel MessageLabel;
    private javax.swing.JLabel RelatedInfLabel;
    private javax.swing.JButton SendButton;
    private javax.swing.JLabel SenderLabel;
    private javax.swing.JLabel TitleLabel;
    private java.awt.Choice choice1;
    private javax.swing.JComboBox<TechInf> cmbTechInf;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblAuthor;
    private javax.swing.JTextArea taMessage;
    private javax.swing.JTextField txtTitle;
    private javax.swing.JButton viewHistoryButton;
    // End of variables declaration//GEN-END:variables
}
