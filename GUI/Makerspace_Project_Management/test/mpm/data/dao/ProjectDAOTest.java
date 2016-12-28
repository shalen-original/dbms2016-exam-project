/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */
package mpm.data.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mpm.data.entities.Project;
import mpm.data.entities.ProjectStatus;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Shalen
 */
public class ProjectDAOTest {

    /**
     * Test of getAll method, of class ProjectDAO.
     */
    @Test
    public void testGetAll() {
        // Filling the list
        ArrayList<Project> expected = new ArrayList<>();
        Project p;
        p = new Project(100);
        p.setTitle("Board Game");
        p.setDescription("Creating a board game for the management course");
        p.setStatus(ProjectStatus.ACTIVE);
        p.setSeekingCollaboration(false);
        expected.add(p);
        p = new Project(101);
        p.setTitle("Arduino Workshop");
        p.setDescription("A one week workshop on what to use Arduino for");
        p.setStatus(ProjectStatus.COMPLETED);
        p.setSeekingCollaboration(false);
        expected.add(p);
        
        // Retriving the complete list
        ProjectDAO instance = new ProjectDAO();
        List<Project> result = instance.getAll();
        result.sort((a,b) -> {
            return Integer.compare(a.getId(), b.getId());  
        });
        
        for (int i = 0; i < result.size(); i++)
        {
            if (!projectEquals(result.get(i),expected.get(i)))
            {
                fail("Project " + result.get(i) + " is different from project " + expected.get(i));
            };
        }
    }

    /**
     * Test of update method, of class ProjectDAO.
     */
    @Test
    public void testUpdate() {
        Project objToWrite = new Project(100);
        ProjectDAO instance = new ProjectDAO();
        Project res;
        
        
        objToWrite.setTitle("NEW TITLE");
        objToWrite.setDescription("NEW DESCRIPTION");
        objToWrite.setStatus(ProjectStatus.COMPLETED);
        objToWrite.setSeekingCollaboration(true);
        
        instance.update(objToWrite);
        res = instance.findByID(objToWrite.getId());
        
        if (!projectEquals(res, objToWrite))
        {
            fail("Project " + res + " is not equal to project " + objToWrite + 
                 "\nRemember to fix database before testing again");
        }
        
        objToWrite.setTitle("Board Game");
        objToWrite.setDescription("Creating a board game for the management course");
        objToWrite.setStatus(ProjectStatus.ACTIVE);
        objToWrite.setSeekingCollaboration(false);
        
        instance.update(objToWrite);
    }
    
    /**
     * Test of insert method, of class ProjectDAO.
     */
    @Test
    public void testInsert() {
        
        Project newp = new Project(150);
        ProjectDAO instance = new ProjectDAO();
        
        newp.setTitle("INSERTED TITLE");
        newp.setDescription("INSERTED DESCRIPTION");
        newp.setSeekingCollaboration(true);
        newp.setStatus(ProjectStatus.COMPLETED);
        
        instance.insert(newp);
        
        List<Project> a = instance.getAll();
        
        boolean ok = false;
        for (Project p : a)
        {
            if (projectEquals(p, newp))
                ok = true;
        }
        
        if (!ok)
        {
            fail("The new project " + newp + " was not inserted." + 
                 "Remember to fix the database before testing again");
        }
        
        instance.delete(newp);
    }

    /**
     * Test of findByID method, of class ProjectDAO.
     */
    @Test
    public void testFindByID() {
        Project expected = new Project(100);
        expected.setTitle("Board Game");
        expected.setDescription("Creating a board game for the management course");
        expected.setStatus(ProjectStatus.ACTIVE);
        expected.setSeekingCollaboration(false);
        
        int ID = 100; 
        ProjectDAO instance = new ProjectDAO();
        Project res = instance.findByID(ID);
       
        if (!projectEquals(res, expected))
        {
            fail("Project " + res + " is different from project " + expected);
        }
    }

    /**
     * Test of deleteByID method, of class ProjectDAO.
     */
    @Test
    public void testDeleteByID() {
       
        Project dp;
        dp = new Project(100);
        dp.setTitle("Board Game");
        dp.setDescription("Creating a board game for the management course");
        dp.setStatus(ProjectStatus.ACTIVE);
        dp.setSeekingCollaboration(false);
        
        ProjectDAO instance = new ProjectDAO();
        instance.delete(dp);
        
        List<Project> a = instance.getAll();
        
        for (Project p : a)
        {
            if (projectEquals(p, dp))
                fail("Project " + p + " was not deleted." + 
                    "Remember to fix the database before testing again");
        }
        
        instance.insert(dp);
        
    }
    
    
    
    private boolean projectEquals(Project a, Project b)
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
