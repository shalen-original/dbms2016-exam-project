/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */
package mpm.data.dao;

import java.util.ArrayList;
import mpm.data.entities.User;

/**
 * Tests for the UserDAO.java class.
 * @author Matteo Nardini
 */
public class UserDAOTest extends GenericDAOTestHelper<User>{
    
    public UserDAOTest() 
    {
        this.dao = new UserDAO();
      
        // Filling initial list
        this.fullTableList = new ArrayList<>();
        User p;
        p = new User(10);
        p.setName("Remo Scolati");
        p.setGeneralRoleId(1020);
        p.setEmail("rscolati@unibz.it");
        this.fullTableList.add(p);
        
        p = new User(11);
        p.setName("Mikel Grabocka");
        p.setGeneralRoleId(1022);
        p.setEmail("mgrabocka@unibz.it");
        this.fullTableList.add(p);
        
        p = new User(12);
        p.setName("Matteo Nardini");
        p.setGeneralRoleId(1021);
        p.setEmail("mnardini@unibz.it");
        this.fullTableList.add(p);
        
        p = new User(13);
        p.setName("Dependecies Free");
        p.setGeneralRoleId(1021);
        p.setEmail("dep@free.it");
        this.fullTableList.add(p);
        
        // Setting up update test
        this.objectToUpdate = new User(10);
        this.objectToUpdate.setName("NEW NAME");
        this.objectToUpdate.setEmail("new.email@example.org");
        this.objectToUpdate.setGeneralRoleId(1021);
        
        this.objectToRevertUpdate = this.fullTableList.get(0);
        
        // Setting up insert test
        this.objectToInsert = new User(9);
        this.objectToInsert.setName("INSERTED NAME");
        this.objectToInsert.setEmail("inserted.email@example.org");
        this.objectToInsert.setGeneralRoleId(1021);
        
        // Setting up delete test
        this.objectToDelete = this.fullTableList.get(3);
    }

    @Override
    protected boolean testEquals(User a, User b) 
    {
         if (a.getId() != b.getId())
            return false;
        
        if (!a.getName().equals(b.getName()))
            return false;
        
        if (!a.getEmail().equals(b.getEmail()))
            return false;
        
        if (a.getGeneralRoleId() != b.getGeneralRoleId())
            return false;
        
        return true;
    }
    
}
