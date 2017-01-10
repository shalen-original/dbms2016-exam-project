/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */
package mpm.gui;
import mpm.data.dao.* ;
import mpm.data.entities.*;
/**
 *
 * @author Mikel
 */
public class BuyMatPanel extends javax.swing.JPanel {

    /**
     * Creates new form BuyMatPanel
     */
    public BuyMatPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MaterialLabel = new javax.swing.JLabel();
        MaterialChoice = new java.awt.Choice();
        UnitsLabel = new javax.swing.JLabel();
        UnitsSpiner = new javax.swing.JSpinner();
        UnitPriceLabel = new javax.swing.JLabel();
        ActualUnitPriceLabel = new javax.swing.JLabel();
        TotalLabel = new javax.swing.JLabel();
        ActualTotalPriceLabel = new javax.swing.JLabel();
        BuyButton = new javax.swing.JButton();

        MaterialLabel.setText("Material:");

        for (Material material : DAOs.materials.getAll())
        { MaterialChoice.addItem(material.getName());
        }
        MaterialChoice.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                MaterialChoiceComponentShown(evt);
            }
        });
        MaterialChoice.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                MaterialChoiceItemStateChanged(evt);
            }
        });
        MaterialChoice.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                MaterialChoicePropertyChange(evt);
            }
        });

        UnitsLabel.setText("Units :");

        UnitsSpiner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));

        UnitPriceLabel.setText("Unitary Price:");

        ActualUnitPriceLabel.setText("10");

        TotalLabel.setText("Total Price:");

        ActualTotalPriceLabel.setText("20");

        BuyButton.setText("Buy");
        BuyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuyButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(BuyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(UnitPriceLabel)
                                .addGap(18, 18, 18)
                                .addComponent(ActualUnitPriceLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(TotalLabel)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(MaterialLabel)
                                        .addComponent(UnitsLabel, javax.swing.GroupLayout.Alignment.TRAILING)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ActualTotalPriceLabel)
                                    .addComponent(UnitsSpiner, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(MaterialChoice, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MaterialChoice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MaterialLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UnitPriceLabel)
                    .addComponent(ActualUnitPriceLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UnitsSpiner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(UnitsLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TotalLabel)
                    .addComponent(ActualTotalPriceLabel))
                .addGap(44, 44, 44)
                .addComponent(BuyButton)
                .addContainerGap(78, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BuyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuyButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BuyButtonActionPerformed

    private void MaterialChoicePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_MaterialChoicePropertyChange

    }//GEN-LAST:event_MaterialChoicePropertyChange

    private void MaterialChoiceComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_MaterialChoiceComponentShown
        // TODO add your handling code here:
    }//GEN-LAST:event_MaterialChoiceComponentShown

    private void MaterialChoiceItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_MaterialChoiceItemStateChanged

        //UnitPriceLabel.setText((((Material)evt.getItem()).getUnitaryPrice())+"");
        
    }//GEN-LAST:event_MaterialChoiceItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ActualTotalPriceLabel;
    private javax.swing.JLabel ActualUnitPriceLabel;
    private javax.swing.JButton BuyButton;
    private java.awt.Choice MaterialChoice;
    private javax.swing.JLabel MaterialLabel;
    private javax.swing.JLabel TotalLabel;
    private javax.swing.JLabel UnitPriceLabel;
    private javax.swing.JLabel UnitsLabel;
    private javax.swing.JSpinner UnitsSpiner;
    // End of variables declaration//GEN-END:variables
}
