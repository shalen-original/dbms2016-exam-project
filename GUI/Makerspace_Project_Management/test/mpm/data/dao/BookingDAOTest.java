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

/**
 * Tests for the BookingDAO.java class.
 * @author Matteo Nardini
 */
public class BookingDAOTest extends GenericDAOTestHelper<FreeInfBooking>{
    
    public BookingDAOTest() 
    {
        this.dao = new BookingDAO();
      
        // Filling initial list
        this.fullTableList = new ArrayList<>();
        FreeInfBooking p;
        p = new FreeInfBooking(901);
        p.setProjectId(100);
        p.setFreeInfId(301);
        p.setStartTime(Timestamp.valueOf("2016-01-16 15:00:00"));
        p.setEndTime(Timestamp.valueOf("2016-01-16 17:00:00"));
        this.fullTableList.add(p);
        
        p = new FreeInfBooking(902);
        p.setProjectId(100);
        p.setFreeInfId(305);
        p.setStartTime(Timestamp.valueOf("2016-01-18 08:00:00"));
        p.setEndTime(Timestamp.valueOf("2016-01-18 17:00:00"));
        this.fullTableList.add(p);
        
        p = new FreeInfBooking(903);
        p.setProjectId(101);
        p.setFreeInfId(321);
        p.setStartTime(Timestamp.valueOf("2016-02-17 08:00:00"));
        p.setEndTime(Timestamp.valueOf("2016-02-18 17:00:00"));
        this.fullTableList.add(p);
        
        p = new FreeInfBooking(904);
        p.setProjectId(101);
        p.setFreeInfId(322);
        p.setStartTime(Timestamp.valueOf("2016-03-19 08:30:00"));
        p.setEndTime(Timestamp.valueOf("2016-03-19 13:30:00"));
        this.fullTableList.add(p);
        
        // Setting up update test
        this.objectToUpdate = new FreeInfBooking(901);
        this.objectToUpdate.setProjectId(101);
        this.objectToUpdate.setFreeInfId(321);
        this.objectToUpdate.setStartTime(Timestamp.valueOf("2017-01-05 15:15:00"));
        this.objectToUpdate.setEndTime(Timestamp.valueOf("2017-01-05 15:16:00"));
        
        this.objectToRevertUpdate = this.fullTableList.get(0);
        
        // Setting up insert test
        this.objectToInsert = new FreeInfBooking(900);
        this.objectToInsert.setProjectId(101);
        this.objectToInsert.setFreeInfId(321);
        this.objectToInsert.setStartTime(Timestamp.valueOf("2017-01-05 15:15:00"));
        this.objectToInsert.setEndTime(Timestamp.valueOf("2017-01-05 15:16:00"));
        
        // Setting up delete test
        this.objectToDelete = this.fullTableList.get(0);
    }
    
    @Test
    public void testFindByProjectID() 
    {
        ArrayList<FreeInfBooking> expected = new ArrayList<>();
        expected.add(this.fullTableList.get(0));
        expected.add(this.fullTableList.get(1));
 
        List<FreeInfBooking> result = ((BookingDAO)dao).findByProjectID(100);
        
        this.listEquals(expected, result);    
    }

    @Test
    public void testFindByFreeInfID() 
    {
        ArrayList<FreeInfBooking> expected = new ArrayList<>();
        expected.add(this.fullTableList.get(2));
 
        List<FreeInfBooking> result = ((BookingDAO)dao).findByFreeInfID(321);
        
        this.listEquals(expected, result);
    }

    /**
     * Test of findBookedInfBetween method, of class BookingDAO.
     */
    @Test
    public void testFindBookedInfBetween() 
    {
        ArrayList<FreeInfBooking> expected = new ArrayList<>();
        expected.add(this.fullTableList.get(0));
        expected.add(this.fullTableList.get(1));
        
        List<FreeInfBooking> result = ((BookingDAO)dao).findBookedInfBetween(
                                        Timestamp.valueOf("2016-1-16 08:10:00"),
                                        Timestamp.valueOf("2016-1-18 15:10:00"));
        
        this.listEquals(expected, result);  
    }

    /**
     * Test of listInfBookingsBetween method, of class BookingDAO.
     */
    @Test
    public void testListInfBookingsBetween() {
        
        ArrayList<FreeInfBooking> expected = new ArrayList<>();
        expected.add(this.fullTableList.get(1));
        
        List<FreeInfBooking> result = ((BookingDAO)dao).listInfBookingsBetween(305,
                                        Timestamp.valueOf("2016-1-16 08:10:00"),
                                        Timestamp.valueOf("2016-1-18 15:10:00"));
       
        this.listEquals(expected, result);
        
    }
    
    @Override
    protected boolean testEquals(FreeInfBooking a, FreeInfBooking b)
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
