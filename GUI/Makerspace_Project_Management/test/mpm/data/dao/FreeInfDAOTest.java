/*
 * Makerspace FreeInf Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */
package mpm.data.dao;

import java.util.ArrayList;
import mpm.data.entities.FreeInf;

/**
 * Tests for the FreeInfDAO.java class
 * @author Matteo Nardini
 */
public class FreeInfDAOTest extends GenericDAOTestHelper<FreeInf>{

    public FreeInfDAOTest()
    {
        this.dao = new FreeInfDAO();
        
        this.fullTableList = new ArrayList<>();
        FreeInf p;   
        p = new FreeInf(301);
        p.setName("Room 301");
        p.setAvailable(true);
        this.fullTableList.add(p);
        
        p = new FreeInf(305);
        p.setName("Room 305");
        p.setAvailable(true);
        this.fullTableList.add(p);
        
        p = new FreeInf(321);
        p.setName("Room 321");
        p.setAvailable(true);
        this.fullTableList.add(p);
        
        p = new FreeInf(322);
        p.setName("Room 322");
        p.setAvailable(true);
        this.fullTableList.add(p);
        
        p = new FreeInf(323);
        p.setName("Room 323 - Not booked");
        p.setAvailable(true);
        this.fullTableList.add(p);
        
        // Setting up update test
        this.objectToUpdate = new FreeInf(301);
        this.objectToUpdate.setName("NEW NAME");
        this.objectToUpdate.setAvailable(false);
        
        this.objectToRevertUpdate = this.fullTableList.get(0);
        
        // Setting up insert test
        this.objectToInsert = new FreeInf(300);
        this.objectToInsert.setName("INSERTED TITLE");
        this.objectToInsert.setAvailable(true);
        
        // Setting up delete test
        this.objectToDelete = this.fullTableList.get(4);
    }
    
    @Override
    protected boolean testEquals(FreeInf a, FreeInf b)
    {
        if (a.getId() != b.getId())
            return false;
        
        if (!a.getName().equals(b.getName()))
            return false;
        
        if (!a.getAvailable().equals(b.getAvailable()))
            return false;
        
        return true;
    }
    
}
