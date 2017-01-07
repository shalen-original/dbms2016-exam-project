/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */
package mpm.data.dao;

import java.util.ArrayList;
import mpm.data.entities.TechInf;

/**
 * Tests for the TechInfDAO.java class
 * @author Matteo Nardini
 */
public class TechInfDAOTest extends GenericDAOTestHelper<TechInf>{
    
    public TechInfDAOTest() 
    {
        this.dao = new TechInfDAO();
        
        this.fullTableList = new ArrayList<>();
        TechInf p;   
        p = new TechInf(501);
        p.setName("3D printer 101");
        p.setAvailable(true);
        this.fullTableList.add(p);
        
        p = new TechInf(502);
        p.setName("3D printer 102");
        p.setAvailable(true);
        this.fullTableList.add(p);
        
        p = new TechInf(503);
        p.setName("3D printer 103");
        p.setAvailable(true);
        this.fullTableList.add(p);
        
        p = new TechInf(504);
        p.setName("Not booked");
        p.setAvailable(true);
        this.fullTableList.add(p);
        
        // Setting up update test
        this.objectToUpdate = new TechInf(501);
        this.objectToUpdate.setName("NEW NAME");
        this.objectToUpdate.setAvailable(false);
        
        this.objectToRevertUpdate = this.fullTableList.get(0);
        
        // Setting up insert test
        this.objectToInsert = new TechInf(505);
        this.objectToInsert.setName("INSERTED TITLE");
        this.objectToInsert.setAvailable(true);
        
        // Setting up delete test
        this.objectToDelete = this.fullTableList.get(3);
    }

    @Override
    protected boolean testEquals(TechInf a, TechInf b) 
    {
        if (a.getId() != b.getId())
            return false;
        
        if (!a.getName().equals(b.getName()))
            return false;
        
        if (a.getAvailable() != b.getAvailable())
            return false;
        
        return true;
    }
    
}
