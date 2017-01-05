/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import mpm.data.entities.FreeInfBooking;
import mpm.data.logic.DBUtils;
import mpm.data.logic.GenericDataAccessObject;

/**
 * Implements a DAO for the Booking table.
 * @author Matteo Nardini
 */
public class BookingDAO extends GenericDataAccessObject<FreeInfBooking>{

    @Override
    public List<FreeInfBooking> getAll() {
        return genericGetAll("booking");
    }

    @Override
    public void update(FreeInfBooking objToWrite) {
        
        String updateQuery = "UPDATE booking SET project_id = ?, " +
                            "free_inf_id = ?, start_time = ?, end_time = ? " +
                            "WHERE booking_id = ?";
        
        DBUtils.performOperation(conn -> {
        
            PreparedStatement s = conn.prepareStatement(updateQuery);
            s.setInt(1, objToWrite.getProjectId());
            s.setInt(2, objToWrite.getFreeInfId());
            s.setTimestamp(3, objToWrite.getStartTime());
            s.setTimestamp(4, objToWrite.getEndTime());
            s.setInt(5, objToWrite.getId());
            s.executeUpdate();
            
        });
        
    }

    @Override
    public void insert(FreeInfBooking objToInsert) {
        String insertQuery = "INSERT INTO booking(booking_id, " + 
                            "project_id, free_inf_id, start_time, end_time) " +
                            "VALUES (?, ?, ?, ?, ?)";
        
        DBUtils.performOperation(conn -> {
        
            PreparedStatement s = conn.prepareStatement(insertQuery);
            s.setInt(1, objToInsert.getId());
            s.setInt(2, objToInsert.getProjectId());
            s.setInt(3, objToInsert.getFreeInfId());
            s.setTimestamp(4, objToInsert.getStartTime());
            s.setTimestamp(5, objToInsert.getEndTime());
            s.executeUpdate();
            
        });
    }

    @Override
    public FreeInfBooking findByID(int ID) {
        
        ArrayList<FreeInfBooking> ans = new ArrayList<>();
        String findByIdQuery = "SELECT * FROM booking WHERE booking_id = ?";
        
        DBUtils.performOperation(conn -> {
        
            PreparedStatement s = conn.prepareStatement(findByIdQuery);
            s.setInt(1, ID);
            ResultSet r = s.executeQuery();
            
            if (r.next())
                ans.add(parseSQLResult(r));
            
        });
        
        return ans.size() >= 1 ? ans.get(0) : null;
        
    }

    @Override
    public void deleteByID(int ID) {
        
        String deleteQuery = "DELETE FROM booking WHERE booking_id = ?";
        
        DBUtils.performOperation(conn -> {
        
            PreparedStatement s = conn.prepareStatement(deleteQuery);
            s.setInt(1, ID);
            s.executeUpdate();
            
        });
        
    }

    @Override
    protected FreeInfBooking parseSQLResult(ResultSet r) throws SQLException {
        FreeInfBooking p = new FreeInfBooking(r.getInt("booking_id"));
        p.setProjectId(r.getInt("project_id"));
        p.setFreeInfId(r.getInt("free_inf_id"));
        p.setStartTime(r.getTimestamp("start_time"));
        p.setEndTime(r.getTimestamp("end_time"));
        
        return p;
    }

    public List<FreeInfBooking> findByProjectID(int projectID){
        
        ArrayList<FreeInfBooking> ans = new ArrayList<>();
        String findByProjectIdQuery = "SELECT * FROM booking WHERE project_id = ?";

        DBUtils.performOperation(conn -> {

            PreparedStatement s = conn.prepareStatement(findByProjectIdQuery);
            s.setInt(1, projectID);
            ResultSet r = s.executeQuery();

            while (r.next())
                ans.add(parseSQLResult(r));

        });

        return ans;
        
    }
    
    public List<FreeInfBooking> findByFreeInfID(int freeInfID){
        
        ArrayList<FreeInfBooking> ans = new ArrayList<>();
        String findByFreeInfIdQuery = "SELECT * FROM booking WHERE free_inf_id = ?";

        DBUtils.performOperation(conn -> {

            PreparedStatement s = conn.prepareStatement(findByFreeInfIdQuery);
            s.setInt(1, freeInfID);
            ResultSet r = s.executeQuery();

            while (r.next())
                ans.add(parseSQLResult(r));

        });

        return ans;
    }
    
    public List<FreeInfBooking> findBookedInfBetween(Timestamp start, Timestamp end){
       
        ArrayList<FreeInfBooking> ans = new ArrayList<>();
        String findByFreeInfIdQuery = "SELECT * FROM booking " +
                                      "WHERE (start_time BETWEEN ? AND ?)" +
                                      "OR    (end_time BETWEEN ? AND ?)" +
                                      "OR    (start_time < ? AND end_time > ?)";

        DBUtils.performOperation(conn -> {

            PreparedStatement s = conn.prepareStatement(findByFreeInfIdQuery);
            s.setTimestamp(1, start);
            s.setTimestamp(2, end);
            s.setTimestamp(3, start);
            s.setTimestamp(4, end);
            s.setTimestamp(5, start);
            s.setTimestamp(6, end);
            ResultSet r = s.executeQuery();

            while (r.next())
                ans.add(parseSQLResult(r));

        });

        return ans;
        
    }

    public List<FreeInfBooking> listInfBookingsBetween(int freeInfId, Timestamp start, Timestamp end){
        
        ArrayList<FreeInfBooking> ans = new ArrayList<>();
        String findByFreeInfIdQuery = "SELECT * FROM booking " +
                                      "WHERE free_inf_id = ? AND " +
                                      "((start_time BETWEEN ? AND ?)" +
                                      "OR    (end_time BETWEEN ? AND ?)" +
                                      "OR    (start_time < ? AND end_time > ?))";

        DBUtils.performOperation(conn -> {

            PreparedStatement s = conn.prepareStatement(findByFreeInfIdQuery);
            s.setInt(1, freeInfId);
            s.setTimestamp(2, start);
            s.setTimestamp(3, end);
            s.setTimestamp(4, start);
            s.setTimestamp(5, end);
            s.setTimestamp(6, start);
            s.setTimestamp(7, end);
            ResultSet r = s.executeQuery();

            while (r.next())
                ans.add(parseSQLResult(r));

        });

        return ans;
    }

}