/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */
package mpm.gui;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import mpm.data.dao.DAOs;
import mpm.data.entities.Message;
import mpm.data.entities.Request;
import mpm.main.MPM;

/**
 *
 * @author remo
 */
public class RequestHistoryPanel extends javax.swing.JPanel {

    /**
     * Creates new form RequestHistoryPanel
     */
    public RequestHistoryPanel() {
        initComponents();
        
        reloadRequestTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        requestsScrollPane = new javax.swing.JScrollPane();
        requestsTable = new javax.swing.JTable();
        messagesScrollPane = new javax.swing.JScrollPane();
        messagesTable = new javax.swing.JTable();
        requestLabel = new javax.swing.JLabel();
        messagesLabel = new javax.swing.JLabel();
        newMessageLabel = new javax.swing.JLabel();
        messageTextField = new javax.swing.JTextField();
        sendMessageButton = new javax.swing.JButton();
        authorLabel = new javax.swing.JLabel();
        messageAuthorLabel = new javax.swing.JLabel();

        requestsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        requestsScrollPane.setViewportView(requestsTable);

        messagesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        messagesScrollPane.setViewportView(messagesTable);

        requestLabel.setText("Requests");

        messagesLabel.setText("Messages");

        newMessageLabel.setText("Compose New Message");

        sendMessageButton.setText("Send Message");
        sendMessageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendMessageButtonActionPerformed(evt);
            }
        });

        authorLabel.setText("Author");

        messageAuthorLabel.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        messageAuthorLabel.setText("jLabel3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(requestLabel)
                    .addComponent(requestsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(messageTextField)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(authorLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(messageAuthorLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sendMessageButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(messagesLabel)
                            .addComponent(newMessageLabel)
                            .addComponent(messagesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(requestLabel)
                    .addComponent(messagesLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(messagesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(newMessageLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(messageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sendMessageButton)
                            .addComponent(authorLabel)
                            .addComponent(messageAuthorLabel)))
                    .addComponent(requestsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void sendMessageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendMessageButtonActionPerformed
        
        if(messageTextField.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Please insert a message text.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            
            Message message = new Message(DAOs.messages.getNextValidId());
            message.setAuthorId(MPM.currentUser.getId());
            message.setCreationTime(timestamp);
            message.setRequestId((Integer)reqModel.getValueAt(requestsTable
                .getSelectedRow(), 0));
            message.setText(messageTextField.getText());
            
            try{
                DAOs.messages.insert(message);
            }catch(RuntimeException ex){
                JOptionPane.showMessageDialog(this, "Oops, something went wrong. \n " + ex.getMessage(), 
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this, "Operation successful!");
            reloadMessageTable();
            
        }
    }//GEN-LAST:event_sendMessageButtonActionPerformed

    private void reloadMessageTable(){
    
        sendMessageButton.setEnabled(true);
        messageTextField.setEnabled(true);
        messageTextField.setText("");
        
        int reqID = (Integer) reqModel.getValueAt(requestsTable
                .getSelectedRow(), 0);

        List<Message> mesList = DAOs.messages.findByRequestID(reqID);

        messagesTable = new javax.swing.JTable();
        messagesTable.setAutoCreateRowSorter(true);

        String[] messageColumnNames = {"Message Content",
            "Author",
            "Time"};

        DefaultTableModel mesModel = new DefaultTableModel(0, 
                messageColumnNames.length);

        for(Message m : mesList){
            Object[] o = new Object[3];
            o[0] = m.getText();
            o[1] = DAOs.users.findByID(m.getAuthorId()).getName();
            o[2] = m.getCreationTime();

            mesModel.addRow(o);
        }

        messagesTable.setModel(mesModel);
        mesModel.setColumnIdentifiers(messageColumnNames);
        messagesScrollPane.setViewportView(messagesTable);
        messagesTable.setDefaultEditor(Object.class, null);
    }
    
    private void reloadRequestTable(){
        
        sendMessageButton.setEnabled(false);
        messageAuthorLabel.setText(MPM.currentUser.getName());
        messageTextField.setEnabled(false);
        
        List<Request> reqList = DAOs.requests
                .findByProjectID(MPM.currentProject.getId());
        
        requestsTable = new javax.swing.JTable();
        requestsTable.setAutoCreateRowSorter(true);
                
        String[] requestColumnNames = {"Id",
            "Title",
            "Infrastructure",
            "Handled by"};
        
        reqModel = new DefaultTableModel(
                0, requestColumnNames.length);
        
        for(Request r : reqList){
            Object[] o = new Object[4];
            o[0] = r.getId();
            o[1] = r.getTitle();
            o[2] = DAOs.technical_infs.findByID(r.getTechInfId()).getName();
            
            if (r.getAssignedUserId() != null)
                o[3] = DAOs.users.findByID(r.getAssignedUserId()).getName();
            else
                o[3] = "";
                       
            reqModel.addRow(o);
        }
        
        requestsTable.setModel(reqModel);
        reqModel.setColumnIdentifiers(requestColumnNames);
        requestsScrollPane.setViewportView(requestsTable);
        requestsTable.setDefaultEditor(Object.class, null);
        
        requestsTable.getSelectionModel().addListSelectionListener(e -> {
            listSelectionChanged();
        });
    }
    
    private void listSelectionChanged(){
        reloadMessageTable();
    }

    private DefaultTableModel reqModel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel authorLabel;
    private javax.swing.JLabel messageAuthorLabel;
    private javax.swing.JTextField messageTextField;
    private javax.swing.JLabel messagesLabel;
    private javax.swing.JScrollPane messagesScrollPane;
    private javax.swing.JTable messagesTable;
    private javax.swing.JLabel newMessageLabel;
    private javax.swing.JLabel requestLabel;
    private javax.swing.JScrollPane requestsScrollPane;
    private javax.swing.JTable requestsTable;
    private javax.swing.JButton sendMessageButton;
    // End of variables declaration//GEN-END:variables
}
