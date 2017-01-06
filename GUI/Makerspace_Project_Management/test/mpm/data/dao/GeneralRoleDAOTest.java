/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */
package mpm.data.dao;

import java.util.ArrayList;
import mpm.data.entities.GeneralRole;

/**
 * Test for the GeneralRoleDAO.java class.
 * @author Matteo Nardini
 */
public class GeneralRoleDAOTest extends GenericDAOTestHelper<GeneralRole>{
    
    public GeneralRoleDAOTest()
    {
        this.dao = new GeneralRoleDAO();
      
        // Filling initial list
        this.fullTableList = new ArrayList<>();
        GeneralRole p;
        p = new GeneralRole(1020);
        p.setName("Staff");
        p.setDescription("Technical support");
        this.fullTableList.add(p);
        
        p = new GeneralRole(1021);
        p.setName("Teacher");
        p.setDescription("Academic staff of the University of Bolzano");
        this.fullTableList.add(p);
        
        p = new GeneralRole(1022);
        p.setName("Student");
        p.setDescription("Student of the University of Bolzano");
        this.fullTableList.add(p);
        
        p = new GeneralRole(1023);
        p.setName("Dependecies Free");
        p.setDescription("A role that none has. For test purposes");
        this.fullTableList.add(p);
        
        // Setting up update test
        this.objectToUpdate = new GeneralRole(1020);
        this.objectToUpdate.setName("NEW ROLE NAME");
        this.objectToUpdate.setDescription("NEW ROLE DESCRIPTION");
        
        this.objectToRevertUpdate = this.fullTableList.get(0);
        
        // Setting up insert test
        this.objectToInsert = new GeneralRole(150);
        this.objectToInsert.setName("INSERTED TITLE");
        this.objectToInsert.setDescription("INSERTED DESCRIPTION");
        
        // Setting up delete test
        this.objectToDelete = this.fullTableList.get(3);
    }

    @Override
    protected boolean testEquals(GeneralRole a, GeneralRole b)
    {
        if (a.getId() != b.getId())
            return false;
        
        if (!a.getName().equals(b.getName()))
            return false;
        
        if (!a.getDescription().equals(b.getDescription()))
            return false;
        
        return true;
    }
    
}
