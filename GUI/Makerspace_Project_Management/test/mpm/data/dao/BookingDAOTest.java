/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */
package mpm.data.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import mpm.data.entities.FreeInfBooking;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Shalen
 */
public class BookingDAOTest {
    
    public BookingDAOTest() {
    }

    /**
     * Test of getAll method, of class BookingDAO.
     */
    @Test
    public void testGetAll() {
        // Filling the list
        ArrayList<FreeInfBooking> expected = new ArrayList<>();
        FreeInfBooking p;
        p = new FreeInfBooking(901);
        p.setProjectId(100);
        p.setFreeInfId(301);
        p.setStartTime(Timestamp.valueOf("2016-01-16 15:00:00"));
        p.setEndTime(Timestamp.valueOf("2016-01-16 17:00:00"));
        expected.add(p);
        p = new FreeInfBooking(902);
        p.setProjectId(100);
        p.setFreeInfId(305);
        p.setStartTime(Timestamp.valueOf("2016-01-18 08:00:00"));
        p.setEndTime(Timestamp.valueOf("2016-01-18 17:00:00"));
        expected.add(p);
        p = new FreeInfBooking(903);
        p.setProjectId(101);
        p.setFreeInfId(321);
        p.setStartTime(Timestamp.valueOf("2016-02-17 08:00:00"));
        p.setEndTime(Timestamp.valueOf("2016-02-18 17:00:00"));
        expected.add(p);
        p = new FreeInfBooking(904);
        p.setProjectId(101);
        p.setFreeInfId(322);
        p.setStartTime(Timestamp.valueOf("2016-03-19 08:30:00"));
        p.setEndTime(Timestamp.valueOf("2016-03-19 13:30:00"));
        expected.add(p);
        
        
        // Retriving the complete list
        BookingDAO instance = new BookingDAO();
        List<FreeInfBooking> result = instance.getAll();
        result.sort((a,b) -> {
            return Integer.compare(a.getId(), b.getId());  
        });
        
        for (int i = 0; i < result.size(); i++)
        {
            if (!bEquals(result.get(i),expected.get(i)))
            {
                fail(result.get(i) + " is different from " + expected.get(i));
            }
        }
    }
    
    /**
     * Test of update method, of class BookingDAO.
     */
    @Test
    public void testUpdate() {
        FreeInfBooking objToWrite = new FreeInfBooking(901);
        BookingDAO instance = new BookingDAO();
        FreeInfBooking res;
        
        objToWrite.setProjectId(101);
        objToWrite.setFreeInfId(321);
        objToWrite.setStartTime(Timestamp.valueOf("2017-01-05 15:15:00"));
        objToWrite.setEndTime(Timestamp.valueOf("2017-01-05 15:16:00"));
        
        instance.update(objToWrite);
        res = instance.findByID(objToWrite.getId());
        
        if (!bEquals(res, objToWrite))
        {
            fail(res + " is not equal to " + objToWrite + 
                 "\nRemember to fix database before testing again");
        }
        
        objToWrite.setProjectId(100);
        objToWrite.setFreeInfId(301);
        objToWrite.setStartTime(Timestamp.valueOf("2016-01-16 15:00:00"));
        objToWrite.setEndTime(Timestamp.valueOf("2016-01-16 17:00:00"));
        
        instance.update(objToWrite);
    }

    /**
     * Test of insert method, of class BookingDAO.
     */
    @Test
    public void testInsert() {
        FreeInfBooking newp = new FreeInfBooking(900);
        BookingDAO instance = new BookingDAO();
        
        newp.setProjectId(101);
        newp.setFreeInfId(321);
        newp.setStartTime(Timestamp.valueOf("2017-01-05 15:15:00"));
        newp.setEndTime(Timestamp.valueOf("2017-01-05 15:16:00"));
        
        instance.insert(newp);
        
        List<FreeInfBooking> a = instance.getAll();
        
        boolean ok = false;
        for (FreeInfBooking p : a)
        {
            if (bEquals(p, newp))
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
     * Test of findByID method, of class BookingDAO.
     */
    @Test
    public void testFindByID() {
        FreeInfBooking expected = new FreeInfBooking(901);
        expected.setProjectId(100);
        expected.setFreeInfId(301);
        expected.setStartTime(Timestamp.valueOf("2016-01-16 15:00:00"));
        expected.setEndTime(Timestamp.valueOf("2016-01-16 17:00:00"));
        
        int ID = 901; 
        BookingDAO instance = new BookingDAO();
        FreeInfBooking res = instance.findByID(ID);
       
        if (!bEquals(res, expected))
        {
            fail(res + " is different from " + expected);
        }
    }

    /**
     * Test of deleteByID method, of class BookingDAO.
     */
    @Test
    public void testDeleteByID() {
        FreeInfBooking dp;
        dp = new FreeInfBooking(901);
        dp.setProjectId(100);
        dp.setFreeInfId(301);
        dp.setStartTime(Timestamp.valueOf("2016-01-16 15:00:00"));
        dp.setEndTime(Timestamp.valueOf("2016-01-16 17:00:00"));
        
        BookingDAO instance = new BookingDAO();
        instance.delete(dp);
        
        List<FreeInfBooking> a = instance.getAll();
        
        for (FreeInfBooking p : a)
        {
            if (bEquals(p, dp))
                fail(p + " was not deleted." + 
                    "Remember to fix the database before testing again");
        }
        
        instance.insert(dp);
    }

    /**
     * Test of findByProjectID method, of class BookingDAO.
     */
    @Test
    public void testFindByProjectID() {
        
        // Filling the list
        ArrayList<FreeInfBooking> expected = new ArrayList<>();
        FreeInfBooking p;
        p = new FreeInfBooking(901);
        p.setProjectId(100);
        p.setFreeInfId(301);
        p.setStartTime(Timestamp.valueOf("2016-01-16 15:00:00"));
        p.setEndTime(Timestamp.valueOf("2016-01-16 17:00:00"));
        expected.add(p);
        p = new FreeInfBooking(902);
        p.setProjectId(100);
        p.setFreeInfId(305);
        p.setStartTime(Timestamp.valueOf("2016-01-18 08:00:00"));
        p.setEndTime(Timestamp.valueOf("2016-01-18 17:00:00"));
        expected.add(p);
        
        
        // Retriving the complete list
        BookingDAO instance = new BookingDAO();
        List<FreeInfBooking> result = instance.findByProjectID(100);
        result.sort((a,b) -> {
            return Integer.compare(a.getId(), b.getId());  
        });
        
        for (int i = 0; i < result.size(); i++)
        {
            if (!bEquals(result.get(i),expected.get(i)))
            {
                fail(result.get(i) + " is different from " + expected.get(i));
            }
        }
        
    }

    /**
     * Test of findByFreeInfID method, of class BookingDAO.
     */
    @Test
    public void testFindByFreeInfID() {
        // Filling the list
        ArrayList<FreeInfBooking> expected = new ArrayList<>();
        FreeInfBooking p;
        p = new FreeInfBooking(903);
        p.setProjectId(101);
        p.setFreeInfId(321);
        p.setStartTime(Timestamp.valueOf("2016-02-17 08:00:00"));
        p.setEndTime(Timestamp.valueOf("2016-02-18 17:00:00"));
        expected.add(p);
        
        
        // Retriving the complete list
        BookingDAO instance = new BookingDAO();
        List<FreeInfBooking> result = instance.findByFreeInfID(321);
        result.sort((a,b) -> {
            return Integer.compare(a.getId(), b.getId());  
        });
        
        for (int i = 0; i < result.size(); i++)
        {
            if (!bEquals(result.get(i),expected.get(i)))
            {
                fail(result.get(i) + " is different from " + expected.get(i));
            }
        }
    }

    /**
     * Test of findBookedInfBetween method, of class BookingDAO.
     */
    @Test
    public void testFindBookedInfBetween() {
        
        // Filling the list
        ArrayList<FreeInfBooking> expected = new ArrayList<>();
        FreeInfBooking p;
        p = new FreeInfBooking(901);
        p.setProjectId(100);
        p.setFreeInfId(301);
        p.setStartTime(Timestamp.valueOf("2016-01-16 15:00:00"));
        p.setEndTime(Timestamp.valueOf("2016-01-16 17:00:00"));
        expected.add(p);
        p = new FreeInfBooking(902);
        p.setProjectId(100);
        p.setFreeInfId(305);
        p.setStartTime(Timestamp.valueOf("2016-01-18 08:00:00"));
        p.setEndTime(Timestamp.valueOf("2016-01-18 17:00:00"));
        expected.add(p);
        
        // Retriving the complete list
        BookingDAO instance = new BookingDAO();
        List<FreeInfBooking> result = instance.findBookedInfBetween(Timestamp.valueOf("2016-1-16 08:10:00"),
                                            Timestamp.valueOf("2016-1-18 15:10:00"));
        result.sort((a,b) -> {
            return Integer.compare(a.getId(), b.getId());  
        });
        
        for (int i = 0; i < result.size(); i++)
        {
            if (!bEquals(result.get(i),expected.get(i)))
            {
                fail(result.get(i) + " is different from " + expected.get(i));
            }
        }
        
    }

    /**
     * Test of listInfBookingsBetween method, of class BookingDAO.
     */
    @Test
    public void testListInfBookingsBetween() {
        
        // Filling the list
        ArrayList<FreeInfBooking> expected = new ArrayList<>();
        FreeInfBooking p;
        p = new FreeInfBooking(902);
        p.setProjectId(100);
        p.setFreeInfId(305);
        p.setStartTime(Timestamp.valueOf("2016-01-18 08:00:00"));
        p.setEndTime(Timestamp.valueOf("2016-01-18 17:00:00"));
        expected.add(p);
        
        // Retriving the complete list
        BookingDAO instance = new BookingDAO();
        List<FreeInfBooking> result = instance.listInfBookingsBetween(305, Timestamp.valueOf("2016-1-16 08:10:00"),
                                            Timestamp.valueOf("2016-1-18 15:10:00"));
        result.sort((a,b) -> {
            return Integer.compare(a.getId(), b.getId());  
        });
        
        for (int i = 0; i < result.size(); i++)
        {
            if (!bEquals(result.get(i),expected.get(i)))
            {
                fail(result.get(i) + " is different from " + expected.get(i));
            }
        }
        
    }
    
    
    private boolean bEquals(FreeInfBooking a, FreeInfBooking b)
    {
        if (a.getId() != b.getId())
            return false;
        
        if (a.getProjectId() != b.getProjectId())
            return false;
        
        if (a.getFreeInfId() != b.getFreeInfId())
            return false;
        
        if (!a.getStartTime().equals(b.getStartTime()))
            return false;
        
        if (!a.getEndTime().equals(b.getEndTime()))
            return false;
        
        return true;
    }
    
}
