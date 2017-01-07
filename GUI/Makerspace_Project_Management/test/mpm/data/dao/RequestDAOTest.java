/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */
package mpm.data.dao;

import java.util.ArrayList;
import mpm.data.entities.Request;

/**
 * Tests for the RequestDAO.java class
 * @author Matteo Nardini
 */
public class RequestDAOTest extends GenericDAOTestHelper<Request>{
    
    public RequestDAOTest() 
    {
        this.dao = new RequestDAO();
      
        // Filling initial list
        this.fullTableList = new ArrayList<>();
        Request p;
        p = new Request(401);
        p.setTitle("Parts to print");
        p.setProjectId(100);
        p.setTechInfId(501);
        p.setAssignedUserId(10);
        this.fullTableList.add(p);
        
        p = new Request(402);
        p.setTitle("Something to print");
        p.setProjectId(100);
        p.setTechInfId(502);
        p.setAssignedUserId(11);
        this.fullTableList.add(p);
        
        p = new Request(403);
        p.setTitle("Something else to print");
        p.setProjectId(101);
        p.setTechInfId(503);
        p.setAssignedUserId(10);
        this.fullTableList.add(p);
        
        p = new Request(404);
        p.setTitle("Request without messages");
        p.setProjectId(101);
        p.setTechInfId(501);
        p.setAssignedUserId(12);
        this.fullTableList.add(p);
        
        // Setting up update test
        this.objectToUpdate = new Request(401);
        this.objectToUpdate.setTitle("NEW TITLE");
        this.objectToUpdate.setProjectId(101);
        this.objectToUpdate.setTechInfId(502);
        this.objectToUpdate.setAssignedUserId(11);
        
        this.objectToRevertUpdate = this.fullTableList.get(0);
        
        // Setting up insert test
        this.objectToInsert = new Request(400);
        this.objectToInsert.setTitle("INSERTED TITLE");
        this.objectToInsert.setProjectId(100);
        this.objectToInsert.setTechInfId(503);
        this.objectToInsert.setAssignedUserId(10);
        
        // Setting up delete test
        this.objectToDelete = this.fullTableList.get(3);
    }

    @Override
    protected boolean testEquals(Request a, Request b) 
    {
        if (a.getId() != b.getId())
            return false;
        
        if (!a.getTitle().equals(b.getTitle()))
            return false;
        
        if (a.getProjectId() != b.getProjectId())
            return false;
        
        if (a.getTechInfId() != b.getTechInfId())
            return false;
        
        if (a.getAssignedUserId() != b.getAssignedUserId())
            return false;
        
        return true;
    }
    
}
