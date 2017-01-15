/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */
package mpm.data.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import mpm.data.entities.Material;
import mpm.data.entities.Purchase;
import mpm.data.logic.DBUtils;
import org.junit.Test;

/**
 * Tests for the PurchaseDAO.java class
 * @author Matteo Nardini
 */
public class PurchaseDAOTest extends GenericDAOTestHelper<Purchase>{
    
    public PurchaseDAOTest() 
    {
        this.dao = new PurchaseDAO();
      
        // Filling initial list
        this.fullTableList = new ArrayList<>();
        Purchase p;
        p = new Purchase(801);
        p.setProjectId(100);
        p.setMaterialId(200);
        p.setUnits(5);
        p.setTotalPrice(BigDecimal.valueOf(0.1));
        this.fullTableList.add(p);
        
        p = new Purchase(802);
        p.setProjectId(100);
        p.setMaterialId(201);
        p.setUnits(10);
        p.setTotalPrice(BigDecimal.valueOf(30));
        this.fullTableList.add(p);
        
        p = new Purchase(803);
        p.setProjectId(101);
        p.setMaterialId(201);
        p.setUnits(6);
        p.setTotalPrice(BigDecimal.valueOf(18));
        this.fullTableList.add(p);
        
        p = new Purchase(804);
        p.setProjectId(101);
        p.setMaterialId(203);
        p.setUnits(10);
        p.setTotalPrice(BigDecimal.valueOf(30));
        this.fullTableList.add(p);

        // Setting up update test
        this.objectToUpdate = new Purchase(801);
        this.objectToUpdate.setProjectId(101);
        this.objectToUpdate.setMaterialId(201);
        this.objectToUpdate.setUnits(85);
        this.objectToUpdate.setTotalPrice(BigDecimal.valueOf(23.205));
        
        this.objectToRevertUpdate = this.fullTableList.get(0);
        
        // Setting up insert test
        this.objectToInsert = new Purchase(800);
        this.objectToInsert.setProjectId(100);
        this.objectToInsert.setMaterialId(200);
        this.objectToInsert.setUnits(34);
        this.objectToInsert.setTotalPrice(BigDecimal.valueOf(435.756));
        
        // Setting up delete test
        this.objectToDelete = this.fullTableList.get(3); 
    }

    @Test
    public void findByProjectIDTest()
    {
        ArrayList<Purchase> expected = new ArrayList<>();
        expected.add(this.fullTableList.get(0));
        expected.add(this.fullTableList.get(1));
        
        List<Purchase> result = ((PurchaseDAO)dao).findByProjectID(100);
        
        this.listEquals(expected, result);  
    }
    
    @Test
    public void insertAndUpdateMaterialTest()
    {
        MaterialDAO mdao = new MaterialDAO();
        Material m = mdao.findByID(objectToInsert.getMaterialId());  
        ((PurchaseDAO)dao).insertAndUpdateMaterial(objectToInsert);
        
        List<Purchase> res = dao.getAll();
        
        boolean ok = false;
        for (Purchase p : res)
        {
            if (testEquals(p, objectToInsert))
                ok = true;
        }
        
        if (!ok)
            fail(objectToInsert + " was not added to the database");
        
        Material mafter = mdao.findByID(objectToInsert.getMaterialId());
        
        if (m.getUnitsAvailable() - mafter.getUnitsAvailable() != objectToInsert.getUnits())
            fail("The material table was not updated correctly");
        
        dao.delete(objectToInsert);
        mdao.update(m); //Restoring database
        
        
    }
    
    @Override
    protected boolean testEquals(Purchase a, Purchase b)
    {
        if (a.getId() != b.getId())
            return false;
        
        if (a.getProjectId() != b.getProjectId())
            return false;
        
        if (a.getMaterialId() != b.getMaterialId())
            return false;
        
        if (a.getUnits() != b.getUnits())
            return false;
        
         if (!a.getTotalPrice().equals(b.getTotalPrice()))
           return false;
        
        return true;
    }

    private void fail(String string) {
    }
    
}
