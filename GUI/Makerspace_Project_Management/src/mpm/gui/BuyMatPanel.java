/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */
package mpm.gui;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.DefaultTableModel;
import mpm.data.dao.* ;
import mpm.data.entities.*;
import mpm.main.MPM;
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
        
        List<ProjectRole> r = DAOs.participations
                .getUserRolesInProject(MPM.currentUser, MPM.currentProject);
        
        if (r.contains(ProjectRole.ADMINISTRATOR))
        {
            cmbMaterials.setRenderer((a, value, c, d, e) -> {
                BasicComboBoxRenderer w = (BasicComboBoxRenderer)(new BasicComboBoxRenderer())
                                                .getListCellRendererComponent(a,value,c,d,e);
                if (value != null)
                    w.setText(value.getName());
                return w;
            });
            
            reloadMaterialComboBox();   
        }else{
            pnlBuyNew.setVisible(false);
        }
        
        reloadPurchaseHistory();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        historyScrollPane = new javax.swing.JScrollPane();
        historyTable = new javax.swing.JTable();
        lblUnitsOfMeasure1 = new javax.swing.JLabel();
        pnlBuyNew = new javax.swing.JPanel();
        MaterialLabel = new javax.swing.JLabel();
        lblTotalPrice = new javax.swing.JLabel();
        BuyButton = new javax.swing.JButton();
        lblUnitaryPrice = new javax.swing.JLabel();
        TotalLabel = new javax.swing.JLabel();
        UnitsLabel = new javax.swing.JLabel();
        cmbMaterials = new javax.swing.JComboBox<>();
        UnitPriceLabel = new javax.swing.JLabel();
        lblUnitsOfMeasure = new javax.swing.JLabel();
        sNumberOfUnits = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        lblTotalUnitsAvailable = new javax.swing.JLabel();

        historyTable.setAutoCreateRowSorter(true);
        historyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Material", "Unitary price", "Units", "Total price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        historyTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        historyScrollPane.setViewportView(historyTable);

        lblUnitsOfMeasure1.setText("Purchase history:");

        MaterialLabel.setText("Material:");

        lblTotalPrice.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalPrice.setText("20");

        BuyButton.setText("Buy");
        BuyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuyButtonActionPerformed(evt);
            }
        });

        lblUnitaryPrice.setText("10");

        TotalLabel.setText("Total Price:");

        UnitsLabel.setText("Units :");

        cmbMaterials.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbMaterialsItemStateChanged(evt);
            }
        });

        UnitPriceLabel.setText("Unitary Price:");

        lblUnitsOfMeasure.setText("unit_of_measure");

        sNumberOfUnits.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        sNumberOfUnits.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sNumberOfUnitsStateChanged(evt);
            }
        });

        jLabel1.setText("Total units available:");

        lblTotalUnitsAvailable.setText("5");

        javax.swing.GroupLayout pnlBuyNewLayout = new javax.swing.GroupLayout(pnlBuyNew);
        pnlBuyNew.setLayout(pnlBuyNewLayout);
        pnlBuyNewLayout.setHorizontalGroup(
            pnlBuyNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBuyNewLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnlBuyNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBuyNewLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalUnitsAvailable)
                        .addContainerGap())
                    .addGroup(pnlBuyNewLayout.createSequentialGroup()
                        .addComponent(MaterialLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbMaterials, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBuyNewLayout.createSequentialGroup()
                        .addGroup(pnlBuyNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlBuyNewLayout.createSequentialGroup()
                                .addComponent(UnitsLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sNumberOfUnits, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblUnitsOfMeasure))
                            .addGroup(pnlBuyNewLayout.createSequentialGroup()
                                .addComponent(UnitPriceLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblUnitaryPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(TotalLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBuyNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTotalPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BuyButton, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)))))
        );
        pnlBuyNewLayout.setVerticalGroup(
            pnlBuyNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBuyNewLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(pnlBuyNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MaterialLabel)
                    .addComponent(cmbMaterials, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBuyNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UnitPriceLabel)
                    .addComponent(lblUnitaryPrice)
                    .addComponent(TotalLabel)
                    .addComponent(lblTotalPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBuyNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UnitsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sNumberOfUnits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUnitsOfMeasure)
                    .addComponent(BuyButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBuyNewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblTotalUnitsAvailable)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(historyScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lblUnitsOfMeasure1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnlBuyNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(pnlBuyNew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUnitsOfMeasure1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(historyScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                .addGap(40, 40, 40))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BuyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuyButtonActionPerformed
     
        Material a = (Material)cmbMaterials.getSelectedItem();
        
        if (((int)sNumberOfUnits.getValue()) > a.getUnitsAvailable())
        {
            JOptionPane.showMessageDialog(this, "The number of units selected is "
                    + "greater than the total available for this material. "
                    + "\nPlease reduce the number of units."
                    + "The total available is " + a.getUnitsAvailable()+".", "Error", JOptionPane.ERROR_MESSAGE);
            
            lblUnitaryPrice.setText("");
            lblUnitsOfMeasure.setText("");
            lblTotalUnitsAvailable.setText("");
            sNumberOfUnits.setValue(((SpinnerNumberModel)sNumberOfUnits.getModel()).getMinimum());
            lblTotalPrice.setText("0 €"); 
            return;
        }
        
        Purchase p = new Purchase(DAOs.purchases.getNextValidId());
        p.setMaterialId(a.getId());
        p.setProjectId(MPM.currentProject.getId());
        p.setUnits((int)sNumberOfUnits.getValue());
        p.setTotalPrice(a.getUnitaryPrice().multiply(
                BigDecimal.valueOf((int)sNumberOfUnits.getValue())));
        
        try
        {
            DAOs.purchases.insertAndUpdateMaterial(p);
            reloadMaterialComboBox();
            reloadPurchaseHistory();
        }catch(RuntimeException ex){
            JOptionPane.showMessageDialog(this, "Oops, something went wrong. \n " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        JOptionPane.showMessageDialog(this, "Operation successful!");
        
    }//GEN-LAST:event_BuyButtonActionPerformed

    private void cmbMaterialsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbMaterialsItemStateChanged
        Material a = (Material)cmbMaterials.getSelectedItem();
        
        if (a == null)
        {
            lblUnitaryPrice.setText("");
            lblUnitsOfMeasure.setText("");
            lblTotalUnitsAvailable.setText("");
            lblTotalPrice.setText("0 €"); 
            return;
        }
        
        lblUnitaryPrice.setText(a.getUnitaryPrice().toString() + " € / " + a.getUnitsOfMeasure());
        lblUnitsOfMeasure.setText(a.getUnitsOfMeasure());
        lblTotalUnitsAvailable.setText(a.getUnitsAvailable() + "");
        
        lblTotalPrice.setText("0 €"); 
    }//GEN-LAST:event_cmbMaterialsItemStateChanged

    private void sNumberOfUnitsStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sNumberOfUnitsStateChanged
        
        Material a = (Material)cmbMaterials.getSelectedItem();
        
        BigDecimal total = a.getUnitaryPrice().multiply(
                BigDecimal.valueOf((int)sNumberOfUnits.getValue()));
        total = total.setScale(2);
        lblTotalPrice.setText(total + " €");
        
    }//GEN-LAST:event_sNumberOfUnitsStateChanged

    private void reloadPurchaseHistory()
    {
        List<Purchase> list = DAOs.purchases
                .findByProjectID(MPM.currentProject.getId());
        
        DefaultTableModel m = (DefaultTableModel)historyTable.getModel();
        m.setRowCount(0);
        
        for(Purchase p : list){
            Object[] o = new Object[4];
            o[0] = DAOs.materials.findByID(p.getMaterialId()).getName();
            o[1] = (p.getTotalPrice().floatValue()/p.getUnits());
            o[2] = p.getUnits();
            o[3] = p.getTotalPrice().floatValue();
            
            m.addRow(o);
        }
        
    }
    
    private void reloadMaterialComboBox()
    {
        Integer selectedMaterialId = null;
        if (cmbMaterials.getSelectedItem() != null)
        {
           selectedMaterialId = ((Material)cmbMaterials.getSelectedItem()).getId();
        }
        
        DefaultComboBoxModel<Material> m = (DefaultComboBoxModel<Material>)cmbMaterials.getModel();
        m.removeAllElements();
        
        for (Material material : DAOs.materials.getAll())
        { 
            m.addElement(material);
        }
        
        // Reselecting previously selected material
        if (selectedMaterialId != null)
        {
            int indexToSelect = 0;
            for (int i = 0; i < cmbMaterials.getItemCount(); i++)
            {
                if (((Material)cmbMaterials.getItemAt(i)).getId() == selectedMaterialId)
                    indexToSelect = i;
            }
            cmbMaterials.setSelectedIndex(indexToSelect);
            cmbMaterialsItemStateChanged(null);
        }else{
            if (cmbMaterials.getItemCount() > 0)
            {
                cmbMaterials.setSelectedIndex(0);
                cmbMaterialsItemStateChanged(null); // Forcing GUI update
            }
        }
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BuyButton;
    private javax.swing.JLabel MaterialLabel;
    private javax.swing.JLabel TotalLabel;
    private javax.swing.JLabel UnitPriceLabel;
    private javax.swing.JLabel UnitsLabel;
    private javax.swing.JComboBox<Material> cmbMaterials;
    private javax.swing.JScrollPane historyScrollPane;
    private javax.swing.JTable historyTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblTotalPrice;
    private javax.swing.JLabel lblTotalUnitsAvailable;
    private javax.swing.JLabel lblUnitaryPrice;
    private javax.swing.JLabel lblUnitsOfMeasure;
    private javax.swing.JLabel lblUnitsOfMeasure1;
    private javax.swing.JPanel pnlBuyNew;
    private javax.swing.JSpinner sNumberOfUnits;
    // End of variables declaration//GEN-END:variables
}
