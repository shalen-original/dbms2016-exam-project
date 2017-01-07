/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */
package mpm.data.dao;

import java.util.ArrayList;
import mpm.data.entities.Participation;
import mpm.data.entities.ProjectRole;

/**
 * Tests for the ParticipationDAO.java class
 * @author Matteo Nardini
 */
public class ParticipationDAOTest extends GenericDAOTestHelper<Participation>{
    
    public ParticipationDAOTest() 
    {
        this.dao = new ParticipationDAO();
      
        // Filling initial list
        this.fullTableList = new ArrayList<>();
        Participation p;
        p = new Participation(1);
        p.setProjectId(101);
        p.setUserId(10);
        p.setRole(ProjectRole.ADMINISTRATOR);
        this.fullTableList.add(p);
        
        p = new Participation(2);
        p.setProjectId(101);
        p.setUserId(11);
        p.setRole(ProjectRole.COLLABORATOR);
        this.fullTableList.add(p);
        
        p = new Participation(3);
        p.setProjectId(101);
        p.setUserId(12);
        p.setRole(ProjectRole.COLLABORATOR);
        this.fullTableList.add(p);
        
        p = new Participation(4);
        p.setProjectId(100);
        p.setUserId(10);
        p.setRole(ProjectRole.COLLABORATOR);
        this.fullTableList.add(p);
        
        p = new Participation(5);
        p.setProjectId(100);
        p.setUserId(11);
        p.setRole(ProjectRole.COLLABORATOR);
        this.fullTableList.add(p);
        
        p = new Participation(6);
        p.setProjectId(100);
        p.setUserId(12);
        p.setRole(ProjectRole.ADMINISTRATOR);
        this.fullTableList.add(p);
        
        // Setting up update test
        this.objectToUpdate = new Participation(1);
        this.objectToUpdate.setProjectId(100);
        this.objectToUpdate.setUserId(11);
        this.objectToUpdate.setRole(ProjectRole.RETIRED);
        
        this.objectToRevertUpdate = this.fullTableList.get(0);
        
        // Setting up insert test
        this.objectToInsert = new Participation(7);
        this.objectToInsert.setProjectId(100);
        this.objectToInsert.setUserId(10);
        this.objectToInsert.setRole(ProjectRole.ADMINISTRATOR);
        
        // Setting up delete test
        this.objectToDelete = this.fullTableList.get(0);  
    }

    @Override
    protected boolean testEquals(Participation a, Participation b) 
    {
        if (a.getId() != b.getId())
            return false;
        
        if (a.getProjectId() != b.getProjectId())
            return false;
        
        if (a.getUserId() != b.getUserId())
            return false;
        
        if (a.getRole() != b.getRole())
            return false;
        
        return true;
    }
    
}
