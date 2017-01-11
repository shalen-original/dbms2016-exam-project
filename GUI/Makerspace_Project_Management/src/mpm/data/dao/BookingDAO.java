/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import mpm.data.entities.FreeInfBooking;
import mpm.data.logic.DBUtils;
import mpm.data.logic.GenericDataAccessObject;
import mpm.data.logic.IPreparedStatementFiller;

/**
 * Implements a DAO for the Booking table.
 * @author Matteo Nardini
 */
public class BookingDAO extends GenericDataAccessObject<FreeInfBooking>{

    public BookingDAO()
    {
        super("booking", "booking_id");
    }

    @Override
    public void update(FreeInfBooking objToWrite) {
        
        String sql = "UPDATE booking SET project_id = ?, " +
                            "free_inf_id = ?, start_time = ?, end_time = ? " +
                            "WHERE booking_id = ?";
        
        DBUtils.performUID(sql, s -> {
            s.setInt(1, objToWrite.getProjectId());
            s.setInt(2, objToWrite.getFreeInfId());
            s.setTimestamp(3, objToWrite.getStartTime());
            s.setTimestamp(4, objToWrite.getEndTime());
            s.setInt(5, objToWrite.getId());  
        });
        
    }

    @Override
    public void insert(FreeInfBooking objToInsert) {
        String sql = "INSERT INTO booking(booking_id, " + 
                            "project_id, free_inf_id, start_time, end_time) " +
                            "VALUES (?, ?, ?, ?, ?)";
        
        DBUtils.performUID(sql, s -> {
            s.setInt(1, objToInsert.getId());
            s.setInt(2, objToInsert.getProjectId());
            s.setInt(3, objToInsert.getFreeInfId());
            s.setTimestamp(4, objToInsert.getStartTime());
            s.setTimestamp(5, objToInsert.getEndTime());
        });
    }

    public List<FreeInfBooking> findByProjectID(int projectID){
        
        String sql = "SELECT * FROM booking WHERE project_id = ?";
        IPreparedStatementFiller f = s -> {s.setInt(1, projectID);};
        
        return DBUtils.performSelect(sql, f, this.defaultParser);
        
    }
    
    public List<FreeInfBooking> findByFreeInfID(int freeInfID){
        
        String sql = "SELECT * FROM booking WHERE free_inf_id = ?";
        IPreparedStatementFiller f = s -> {s.setInt(1, freeInfID);};
        
        return DBUtils.performSelect(sql, f, this.defaultParser);
    }
    
    public List<FreeInfBooking> findBookedInfBetween(Timestamp start, Timestamp end){
       
        String sql = "SELECT * FROM booking " +
                    "WHERE (start_time BETWEEN ? AND ?)" +
                    "OR    (end_time BETWEEN ? AND ?)" +
                    "OR    (start_time < ? AND end_time > ?)";
        
        IPreparedStatementFiller f = s -> {
            s.setTimestamp(1, start);
            s.setTimestamp(2, end);
            s.setTimestamp(3, start);
            s.setTimestamp(4, end);
            s.setTimestamp(5, start);
            s.setTimestamp(6, end);
        };
        
        return DBUtils.performSelect(sql, f, this.defaultParser);
        
    }

    public List<FreeInfBooking> listInfBookingsBetween(int freeInfId, Timestamp start, Timestamp end){
        
        String sql = "SELECT * FROM booking " +
                    "WHERE free_inf_id = ? AND " +
                    "((start_time BETWEEN ? AND ?)" +
                    "OR    (end_time BETWEEN ? AND ?)" +
                    "OR    (start_time < ? AND end_time > ?))";
        
        IPreparedStatementFiller f = s -> {
            s.setInt(1, freeInfId);
            s.setTimestamp(2, start);
            s.setTimestamp(3, end);
            s.setTimestamp(4, start);
            s.setTimestamp(5, end);
            s.setTimestamp(6, start);
            s.setTimestamp(7, end);
        };
        
        return DBUtils.performSelect(sql, f, this.defaultParser);
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
}