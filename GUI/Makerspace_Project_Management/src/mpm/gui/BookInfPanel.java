/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */
package mpm.gui;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import mpm.data.dao.* ;
import mpm.data.entities.*;

import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import mpm.main.MPM;

/**
 *
 * @author Mikel
 */
public class BookInfPanel extends javax.swing.JPanel {

    /**
     * Creates new form BookInfPanel
     */
    public BookInfPanel() {
        initComponents();
        
        List<FreeInf> infs = DAOs.free_infs.getAvailable();
        infs.sort((a,b) -> {
            return a.getName().compareTo(b.getName());
        });
        
        DefaultComboBoxModel<FreeInf> m = new DefaultComboBoxModel<>();
        for(FreeInf fi: infs)
        {
            m.addElement(fi);
        }
        cmbFreeInf.setModel(m);
        cmbFreeInf.setRenderer((a, value, c, d, e) -> {
            BasicComboBoxRenderer w = (BasicComboBoxRenderer)(new BasicComboBoxRenderer())
                                            .getListCellRendererComponent(a,value,c,d,e);
            w.setText(value.getName());
            return w;
        });
        
        ((SpinnerDateModel)dtpTimeEnd.getModel()).setStart((Date)dtpTimeStart.getValue());
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FreeInfLabel = new javax.swing.JLabel();
        StartLabel = new javax.swing.JLabel();
        EndLabel = new javax.swing.JLabel();
        BookButton = new javax.swing.JButton();
        Date date = new Date();
        SpinnerDateModel sm = new SpinnerDateModel(date,null,null,Calendar.MINUTE);
        dtpTimeStart = new javax.swing.JSpinner(sm);
        Date date1 = new Date();
        SpinnerDateModel sm1 = new SpinnerDateModel(date1,null,null,Calendar.MINUTE);
        dtpTimeEnd = new javax.swing.JSpinner(sm1);
        cmbFreeInf = new javax.swing.JComboBox<>();

        FreeInfLabel.setText("Free Inf :");

        StartLabel.setText("Start time:");

        EndLabel.setText("End time:");

        BookButton.setText("Book");
        BookButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BookButtonActionPerformed(evt);
            }
        });

        JSpinner.DateEditor de = new JSpinner.DateEditor(dtpTimeStart, "E yyyy-MM-dd HH:mm");
        dtpTimeStart.setEditor(de);
        dtpTimeStart.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                dtpTimeStartStateChanged(evt);
            }
        });

        JSpinner.DateEditor de1 = new JSpinner.DateEditor(dtpTimeEnd, "E yyyy-MM-dd HH:mm");
        dtpTimeEnd.setEditor(de1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(116, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BookButton, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(FreeInfLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(StartLabel))
                            .addComponent(EndLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbFreeInf, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dtpTimeStart, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dtpTimeEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(117, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FreeInfLabel)
                    .addComponent(cmbFreeInf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(StartLabel)
                            .addComponent(dtpTimeStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(58, 58, 58))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dtpTimeEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(EndLabel)))
                .addGap(40, 40, 40)
                .addComponent(BookButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BookButtonActionPerformed

        FreeInfBooking b = new FreeInfBooking(DAOs.bookings.getNextValidId());
        b.setFreeInfId(((FreeInf)cmbFreeInf.getSelectedItem()).getId());
        b.setProjectId(MPM.currentProject.getId());
        b.setStartTime(new Timestamp(((Date)dtpTimeStart.getValue()).getTime()));
        b.setEndTime(new Timestamp(((Date)dtpTimeEnd.getValue()).getTime()));
        
        DAOs.bookings.insert(b);
        
    }//GEN-LAST:event_BookButtonActionPerformed

    private void dtpTimeStartStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_dtpTimeStartStateChanged
        
        Date start = (Date)dtpTimeStart.getValue();
        Date end = (Date)dtpTimeEnd.getValue();
        
        if (end.compareTo(start) < 0)
            dtpTimeEnd.setValue(dtpTimeStart.getValue());
        
        ((SpinnerDateModel)dtpTimeEnd.getModel()).setStart((Date)dtpTimeStart.getValue());
    }//GEN-LAST:event_dtpTimeStartStateChanged

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BookButton;
    private javax.swing.JLabel EndLabel;
    private javax.swing.JLabel FreeInfLabel;
    private javax.swing.JLabel StartLabel;
    private javax.swing.JComboBox<FreeInf> cmbFreeInf;
    private javax.swing.JSpinner dtpTimeEnd;
    private javax.swing.JSpinner dtpTimeStart;
    // End of variables declaration//GEN-END:variables
}
