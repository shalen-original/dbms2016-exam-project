/*
 * Makerspace FreeInf Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */
package mpm.data.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import mpm.data.entities.FreeInf;
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
public class FreeInfDAOTest {

    /**
     * Test of getAll method, of class FreeInfDAO.
     */
    @Test
    public void testGetAll() {
        // Filling the list
        ArrayList<FreeInf> expected = new ArrayList<>();
        FreeInf p;
        p = new FreeInf(301);
        p.setName("Room 301");
        p.setAvailable(true);
        expected.add(p);
        
        p = new FreeInf(305);
        p.setName("Room 305");
        p.setAvailable(true);
        expected.add(p);
        
        p = new FreeInf(321);
        p.setName("Room 321");
        p.setAvailable(true);
        expected.add(p);
        
        p = new FreeInf(322);
        p.setName("Room 322");
        p.setAvailable(true);
        expected.add(p);
        
        p = new FreeInf(323);
        p.setName("Room 323 - Not booked");
        p.setAvailable(true);
        expected.add(p);
        
        // Retriving the complete list
        FreeInfDAO instance = new FreeInfDAO();
        List<FreeInf> result = instance.getAll();
        result.sort((a,b) -> {
            return Integer.compare(a.getId(), b.getId());  
        });
        
        for (int i = 0; i < result.size(); i++)
        {
            if (!fiEquals(result.get(i),expected.get(i)))
            {
                fail(result.get(i) + " is different from " + expected.get(i));
            };
        }
    }

    /**
     * Test of update method, of class FreeInfDAO.
     */
    @Test
    public void testUpdate() {
        FreeInf objToWrite = new FreeInf(301);
        FreeInfDAO instance = new FreeInfDAO();
        FreeInf res;
           
        objToWrite.setName("NEW NAME");
        objToWrite.setAvailable(false);
        
        instance.update(objToWrite);
        res = instance.findByID(objToWrite.getId());
        
        if (!fiEquals(res, objToWrite))
        {
            fail(res + " is not equal to " + objToWrite + 
                 "\nRemember to fix database before testing again");
        }
        
        objToWrite.setName("Room 301");
        objToWrite.setAvailable(true);
        
        instance.update(objToWrite);
    }

    /**
     * Test of insert method, of class FreeInfDAO.
     */
    @Test
    public void testInsert() {
        FreeInf newp = new FreeInf(300);
        FreeInfDAO instance = new FreeInfDAO();
        
        newp.setName("INSERTED TITLE");
        newp.setAvailable(true);
        
        instance.insert(newp);
        
        List<FreeInf> a = instance.getAll();
        
        boolean ok = false;
        for (FreeInf p : a)
        {
            if (fiEquals(p, newp))
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
     * Test of findByID method, of class FreeInfDAO.
     */
    @Test
    public void testFindByID() {
        FreeInf expected = new FreeInf(301);
        expected.setName("Room 301");
        expected.setAvailable(true);

        int ID = 301; 
        FreeInfDAO instance = new FreeInfDAO();
        FreeInf res = instance.findByID(ID);
       
        if (!fiEquals(res, expected))
        {
            fail(res + " is different from " + expected);
        }
    }

    /**
     * Test of deleteByID method, of class FreeInfDAO.
     */
    @Test
    public void testDeleteByID() {
       
        FreeInf dp;
        dp = new FreeInf(323);
        dp.setName("Room 323 - Not booked");
        dp.setAvailable(true);
        
        FreeInfDAO instance = new FreeInfDAO();
        instance.delete(dp);
        
        List<FreeInf> a = instance.getAll();
        
        for (FreeInf p : a)
        {
            if (fiEquals(p, dp))
                fail(p + " was not deleted." + 
                    "Remember to fix the database before testing again");
        }
        
        instance.insert(dp);
        
    }
    
    
    
    
    private boolean fiEquals(FreeInf a, FreeInf b)
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
