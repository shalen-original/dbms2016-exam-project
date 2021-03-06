/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */
package mpm.gui;

import java.sql.Timestamp;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import mpm.data.dao.DAOs;
import mpm.data.dao.RequestDAO;
import mpm.data.entities.Message;
import mpm.data.entities.Request;
import mpm.data.entities.User;
import mpm.data.logic.IDHider;
import mpm.data.logic.Pair;
import mpm.main.MPM;

/**
 *
 * @author Shalen
 */
public class RequestsManagement extends javax.swing.JPanel {

    private Request selectedRequest;
    
    /**
     * Creates new form PendingRequestsManagement
     */
    public RequestsManagement() {
        initComponents();
        
        lblAuthor.setText(MPM.currentUser.getName());
        
        reloadRequestsList();
        reloadUsersList();
        
        tbRequests.getSelectionModel().addListSelectionListener(e -> {
            onSelectedRequestChange();
        });
        
        cmbTechnicians.setRenderer((a, value, c, d, e) -> {
                BasicComboBoxRenderer w = (BasicComboBoxRenderer)(new BasicComboBoxRenderer())
                                                .getListCellRendererComponent(a,value,c,d,e);
                w.setText(value.getName());
                return w;
            });
        
        TableRowSorter sorter = new TableRowSorter(tbMessages.getModel());
        sorter.setComparator(1, (a,b) -> {
            return ((Timestamp) a).compareTo((Timestamp) b);
        });
        tbMessages.setRowSorter(sorter);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgRequestShowModeGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        rdbShowOnlyPending = new javax.swing.JRadioButton();
        rdbShowAllRequests = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbRequests = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbMessages = new javax.swing.JTable();
        btnAssign = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cmbTechnicians = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtNewMessage = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        lblAuthor = new javax.swing.JLabel();
        rdbShowRequestsAssignedToMe = new javax.swing.JRadioButton();

        jLabel1.setText("Show:");

        bgRequestShowModeGroup.add(rdbShowOnlyPending);
        rdbShowOnlyPending.setSelected(true);
        rdbShowOnlyPending.setText("Unassigned requests");
        rdbShowOnlyPending.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbChange(evt);
            }
        });

        bgRequestShowModeGroup.add(rdbShowAllRequests);
        rdbShowAllRequests.setText("All requests");
        rdbShowAllRequests.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbChange(evt);
            }
        });

        jLabel2.setText("List of all unassigned requests:");

        tbRequests.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title", "Project", "Technical Inf", "Assigned to"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbRequests.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tbRequests);

        jLabel3.setText("List of the selected request messages:");

        tbMessages.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Text", "Timestap", "Sent by"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbMessages.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(tbMessages);

        btnAssign.setText("Assign");
        btnAssign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAssignActionPerformed(evt);
            }
        });

        jLabel4.setText("Assign selected request to:");

        jLabel5.setText("Add new message:");

        btnSend.setText("Send");
        btnSend.setPreferredSize(new java.awt.Dimension(93, 23));
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        jLabel6.setText("Message author: ");

        lblAuthor.setText("author_");

        bgRequestShowModeGroup.add(rdbShowRequestsAssignedToMe);
        rdbShowRequestsAssignedToMe.setText("Requests assigned to me");
        rdbShowRequestsAssignedToMe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbChange(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbTechnicians, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAssign, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtNewMessage)
                        .addGap(26, 26, 26)
                        .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdbShowOnlyPending)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdbShowAllRequests)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rdbShowRequestsAssignedToMe))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(41, 41, 41)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblAuthor)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(rdbShowOnlyPending)
                    .addComponent(rdbShowRequestsAssignedToMe)
                    .addComponent(rdbShowAllRequests))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAssign)
                    .addComponent(jLabel4)
                    .addComponent(cmbTechnicians, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(lblAuthor)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNewMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAssignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAssignActionPerformed
        
        if (selectedRequest == null)
        {
            JOptionPane.showMessageDialog(this, 
                    "Select a request before assigning a technician to it!", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;            
        }
        
        if (cmbTechnicians.getSelectedIndex() == -1)
        {
            JOptionPane.showMessageDialog(this, 
                    "Select a technician to be assigned!", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;  
        }
          
        try
        {
            selectedRequest.setAssignedUserId(((User)cmbTechnicians.getSelectedItem()).getId());
            DAOs.requests.update(selectedRequest);
            reloadRequestsList();
        }catch(RuntimeException ex){
            JOptionPane.showMessageDialog(this, "Oops, something went wrong. \n " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            
            return;
        }
        
        JOptionPane.showMessageDialog(this, "Operation successful!");
        
    }//GEN-LAST:event_btnAssignActionPerformed

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        
        if (selectedRequest == null)
        {
            JOptionPane.showMessageDialog(this, 
                    "Select a request to which you want to add a message!", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String a = txtNewMessage.getText();
        
        if (a.isEmpty())
        {
            JOptionPane.showMessageDialog(this, 
                    "Insert a message text!", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Message m = new Message(DAOs.messages.getNextValidId());
        m.setText(a);
        m.setRequestId(selectedRequest.getId());
        m.setAuthorId(MPM.currentUser.getId());
        m.setCreationTime(new Timestamp(System.currentTimeMillis())); 
        try
        {
            DAOs.messages.insert(m);
            reloadMessageList();
        }catch(RuntimeException ex){
            JOptionPane.showMessageDialog(this, "Oops, something went wrong. \n " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            
            return;
        }
        
        JOptionPane.showMessageDialog(this, "Operation successful!");
        
    }//GEN-LAST:event_btnSendActionPerformed

    private void rdbChange(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbChange
        
        reloadRequestsList();
    }//GEN-LAST:event_rdbChange

    private void reloadRequestsList()
    {
        List<RequestDAO.RequestWIDResolvedToStrings> list;
        
        if (this.rdbShowAllRequests.isSelected())
        {
            list = DAOs.requests.deepGetAll();
        }
        else if (this.rdbShowOnlyPending.isSelected())
        {
            list = DAOs.requests.deepGetUnassignedRequests();
        }
        else if (this.rdbShowRequestsAssignedToMe.isSelected())
        {
            list = DAOs.requests.deepFindByAssignedUserId(MPM.currentUser.getId());
        }
        else
        {
            rdbShowAllRequests.setSelected(true);
            list = DAOs.requests.deepGetAll();
        }
         
        DefaultTableModel m = (DefaultTableModel) tbRequests.getModel();
        
        m.setRowCount(0);
        
        list.forEach(r -> {
            
            Object[] a = new Object[4];
            a[0] = new IDHider(r.getRequest(), r.getRequest().getTitle());
            a[1] = r.getProjectName();
            a[2] = r.getTechInfName();
            a[3] = r.getAssignedToName();
            
            m.addRow(a); 
        });
    }
    
    private void reloadMessageList()
    {     
        DefaultTableModel m = (DefaultTableModel) tbMessages.getModel();
        m.setRowCount(0);
        
        if (selectedRequest == null)
            return;

        List<Pair<Message, User>> list = 
                DAOs.messages.findByRequestIDWithAuthorData(selectedRequest.getId());
        
        list.forEach(p -> {
        
            Object[] a = new Object[4];
            a[0] = p.getFirst().getText();
            a[1] = p.getFirst().getCreationTime();
            a[2] = p.getSecond().getName();
            
            m.addRow(a);
        
        });
    }
    
    private void reloadUsersList()
    {
        cmbTechnicians.removeAllItems();
        
        DefaultComboBoxModel<User> m = (DefaultComboBoxModel<User>) cmbTechnicians.getModel();
        
        DAOs.users.findByGeneralRole("Staff").forEach(u -> {
           m.addElement(u);
        });
        
        if (cmbTechnicians.getItemCount() > 0)
            cmbTechnicians.setSelectedIndex(0);
    }
    
    private void onSelectedRequestChange()
    {
        int selectedIndex = tbRequests.getSelectedRow();
        
        if (selectedIndex == -1)
        {
            selectedRequest = null;
        }else{
            TableModel m = tbRequests.getModel();
            selectedRequest = ((IDHider<Request>) m.getValueAt(selectedIndex, 0)).getHidden();
        }
        
        reloadMessageList();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgRequestShowModeGroup;
    private javax.swing.JButton btnAssign;
    private javax.swing.JButton btnSend;
    private javax.swing.JComboBox<User> cmbTechnicians;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAuthor;
    private javax.swing.JRadioButton rdbShowAllRequests;
    private javax.swing.JRadioButton rdbShowOnlyPending;
    private javax.swing.JRadioButton rdbShowRequestsAssignedToMe;
    private javax.swing.JTable tbMessages;
    private javax.swing.JTable tbRequests;
    private javax.swing.JTextField txtNewMessage;
    // End of variables declaration//GEN-END:variables
}
