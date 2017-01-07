/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */
package mpm.data.dao;

import java.util.ArrayList;
import java.util.List;
import mpm.data.entities.Project;
import mpm.data.entities.ProjectStatus;
import org.junit.Test;

/**
 * Tests for the ProjectDAO.java class
 * @author Matteo Nardini
 */
public class ProjectDAOTest extends GenericDAOTestHelper<Project>{

    public ProjectDAOTest()
    {
        this.dao = new ProjectDAO();
      
        // Filling initial list
        this.fullTableList = new ArrayList<>();
        Project p;
        p = new Project(100);
        p.setTitle("Board Game");
        p.setDescription("Creating a board game for the management course");
        p.setStatus(ProjectStatus.ACTIVE);
        p.setSeekingCollaboration(false);
        this.fullTableList.add(p);
        
        p = new Project(101);
        p.setTitle("Arduino Workshop");
        p.setDescription("A one week workshop on what to use Arduino for");
        p.setStatus(ProjectStatus.COMPLETED);
        p.setSeekingCollaboration(false);
        this.fullTableList.add(p);
        
        p = new Project(102);
        p.setTitle("Dependecies free");
        p.setDescription("A project without bookings, purchases and such. For test purposes");
        p.setStatus(ProjectStatus.COMPLETED);
        p.setSeekingCollaboration(false);
        this.fullTableList.add(p);
        
        // Setting up update test
        this.objectToUpdate = new Project(100);
        this.objectToUpdate.setTitle("NEW TITLE");
        this.objectToUpdate.setDescription("NEW DESCRIPTION");
        this.objectToUpdate.setStatus(ProjectStatus.COMPLETED);
        this.objectToUpdate.setSeekingCollaboration(true);
        
        this.objectToRevertUpdate = this.fullTableList.get(0);
        
        // Setting up insert test
        this.objectToInsert = new Project(150);
        this.objectToInsert.setTitle("INSERTED TITLE");
        this.objectToInsert.setDescription("INSERTED DESCRIPTION");
        this.objectToInsert.setSeekingCollaboration(true);
        this.objectToInsert.setStatus(ProjectStatus.COMPLETED);
        
        // Setting up delete test
        this.objectToDelete = this.fullTableList.get(2);
    }

    @Test
    public void testFindByProjectID() 
    {
        ArrayList<Project> expected = new ArrayList<>();
        expected.add(this.fullTableList.get(0));
        expected.add(this.fullTableList.get(1));
 
        List<Project> result = ((ProjectDAO)dao).findByUserID(12);
        
        this.listEquals(expected, result);    
    }
    
    @Override
    protected boolean testEquals(Project a, Project b)
    {
        if (a.getId() != b.getId())
            return false;
        
        if (!a.getTitle().equals(b.getTitle()))
            return false;
        
        if (!a.getDescription().equals(b.getDescription()))
            return false;
        
        if (!a.getSeekingCollaboration().equals(b.getSeekingCollaboration()))
            return false;
        
        if (a.getStatus() != b.getStatus())
            return false;
        
        return true;
    }
}
