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
import mpm.data.entities.GeneralRole;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Shalen
 */
public class GeneralRoleDAOTest {
    
    public GeneralRoleDAOTest() {
    }

    /**
     * Test of getAll method, of class GeneralRoleDAO.
     */
    @Test
    public void testGetAll() {
        ArrayList<GeneralRole> expected = new ArrayList<>();
        GeneralRole p;
        p = new GeneralRole(1020);
        p.setName("Staff");
        p.setDescription("Technical support");
        expected.add(p);
        
        p = new GeneralRole(1021);
        p.setName("Teacher");
        p.setDescription("Academic staff of the University of Bolzano");
        expected.add(p);
        
        p = new GeneralRole(1022);
        p.setName("Student");
        p.setDescription("Student of the University of Bolzano");
        expected.add(p);
        
         p = new GeneralRole(1023);
        p.setName("Dependecies Free");
        p.setDescription("A role that none has. For test purposes");
        expected.add(p);
      
        
        // Retriving the complete list
        GeneralRoleDAO instance = new GeneralRoleDAO();
        List<GeneralRole> result = instance.getAll();
        result.sort((a,b) -> {
            return Integer.compare(a.getId(), b.getId());  
        });
        
        for (int i = 0; i < result.size(); i++)
        {
            if (!grEquals(result.get(i),expected.get(i)))
            {
                fail(result.get(i) + " is different from " + expected.get(i));
            };
        }
    }

    /**
     * Test of update method, of class GeneralRoleDAO.
     */
    @Test
    public void testUpdate() {
        GeneralRole objToWrite = new GeneralRole(1020);
        GeneralRoleDAO instance = new GeneralRoleDAO();
        GeneralRole res;

        objToWrite.setName("NEW ROLE NAME");
        objToWrite.setDescription("NEW ROLE DESCRIPTION");
        
        instance.update(objToWrite);
        res = instance.findByID(objToWrite.getId());
        
        if (!grEquals(res, objToWrite))
        {
            fail(res + " is not equal to " + objToWrite + 
                 "\nRemember to fix database before testing again");
        }
        
        objToWrite.setName("Staff");
        objToWrite.setDescription("Technical support");
        
        instance.update(objToWrite);
    }

    /**
     * Test of insert method, of class GeneralRoleDAO.
     */
    @Test
    public void testInsert() {
        GeneralRole newp = new GeneralRole(150);
        GeneralRoleDAO instance = new GeneralRoleDAO();
        
        newp.setName("INSERTED TITLE");
        newp.setDescription("INSERTED DESCRIPTION");
        
        instance.insert(newp);
        
        List<GeneralRole> a = instance.getAll();
        
        boolean ok = false;
        for (GeneralRole p : a)
        {
            if (grEquals(p, newp))
                ok = true;
        }
        
        if (!ok)
        {
            fail("The new " + newp + " was not inserted." + 
                 "Remember to fix the database before testing again");
        }
        
        instance.delete(newp);
    }

    /**
     * Test of findByID method, of class GeneralRoleDAO.
     */
    @Test
    public void testFindByID() {
        GeneralRole expected = new GeneralRole(1020);
        expected.setName("Staff");
        expected.setDescription("Technical support");
        
        int ID = 1020; 
        GeneralRoleDAO instance = new GeneralRoleDAO();
        GeneralRole res = instance.findByID(ID);
       
        if (!grEquals(res, expected))
        {
            fail(res + " is different from " + expected);
        }
    }

    /**
     * Test of deleteByID method, of class GeneralRoleDAO.
     */
    @Test
    public void testDeleteByID() {
        GeneralRole dp;
        dp = new GeneralRole(1023);
        dp.setName("Dependecies Free");
        dp.setDescription("A role that none has. For test purposes");
        
        GeneralRoleDAO instance = new GeneralRoleDAO();
        instance.delete(dp);
        
        List<GeneralRole> a = instance.getAll();
        
        for (GeneralRole p : a)
        {
            if (grEquals(p, dp))
                fail(p + " was not deleted." + 
                    "Remember to fix the database before testing again");
        }
        
        instance.insert(dp);
    }

    private boolean grEquals(GeneralRole a, GeneralRole b)
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
