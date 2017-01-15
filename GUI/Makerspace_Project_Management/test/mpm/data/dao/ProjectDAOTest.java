/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */
package mpm.data.dao;

import java.util.ArrayList;
import java.util.List;
import mpm.data.entities.Participation;
import mpm.data.entities.Project;
import mpm.data.entities.ProjectRole;
import mpm.data.entities.ProjectStatus;
import mpm.data.entities.User;
import static org.junit.Assert.fail;
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
    public void testGetUserProjectsInt() 
    {
        ArrayList<Project> expected = new ArrayList<>();
        expected.add(this.fullTableList.get(0));
        expected.add(this.fullTableList.get(1));
 
        List<Project> result = ((ProjectDAO)dao).getUserProjects(12);
        
        this.listEquals(expected, result);    
    }
    
    @Test
    public void testGetUserProjectsUser()
    {
        ArrayList<Project> expected = new ArrayList<>();
        expected.add(this.fullTableList.get(0));
        expected.add(this.fullTableList.get(1));
 
        List<Project> result = ((ProjectDAO)dao).getUserProjects(new User(12));
        
        this.listEquals(expected, result);   
    }
    
    @Test
    public void testCreateNewProjectWithAdmin()
    {
        User u = new User(12);
        u.setName("Matteo Nardini");
        u.setGeneralRoleId(1021);
        u.setEmail("mnardini@unibz.it");
        
        ((ProjectDAO)dao).createNewProjectWithAdmin(this.objectToInsert, u);
        
        List<Project> all = dao.getAll();
        
        boolean ok = false;
        for (Project item : all)
        {
            if (testEquals(item, objectToInsert))
                ok = true;
        }
        
        if (!ok)
        {
            fail("The new " + objectToInsert + " was not inserted." + 
                 "Remember to fix the database before testing again");
        }
        
        List<Participation> allp = (new ParticipationDAO()).getAll();
        ok = false;
        for (Participation item : allp)
        {
            if (item.getProjectId() != this.objectToInsert.getId() &&
                    item.getRole() == ProjectRole.ADMINISTRATOR &&
                    item.getUserId() == u.getId())
                ok = true;
            
        }
        
        if (!ok)
        {
            fail("No admin participation for this project was found." + 
                 "Remember to fix the database before testing again");
        }
        
        
        dao.delete(objectToInsert);
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
