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
import java.util.ArrayList;
import java.util.List;
import mpm.data.entities.FreeInf;
import mpm.data.logic.DBUtils;
import mpm.data.logic.GenericDataAccessObject;

/**
 * Implements a DAO for the Free_Infrastructure table.
 * @author Matteo Nardini
 */
public class FreeInfDAO extends GenericDataAccessObject<FreeInf> {

    @Override
    public List<FreeInf> getAll() {
        return genericGetAll("free_inf");
    }

    @Override
    public void update(FreeInf objToWrite) {
        
        String updateQuery = "UPDATE free_inf SET name = ?, " +
                            "available = ? WHERE free_inf_id = ?";
        
        DBUtils.performOperation(conn -> {
        
            PreparedStatement s = conn.prepareStatement(updateQuery);
            s.setString(1, objToWrite.getName());
            s.setBoolean(2, objToWrite.getAvailable());
            s.setInt(3, objToWrite.getId());
            s.executeUpdate();
            
        });
        
    }

    @Override
    public void insert(FreeInf objToInsert) {
        
        String insertQuery = "INSERT INTO free_inf(free_inf_id, " + 
                            "name, available) VALUES (?, ?, ?)";
        
        DBUtils.performOperation(conn -> {
        
            PreparedStatement s = conn.prepareStatement(insertQuery);
            s.setInt(1, objToInsert.getId());
            s.setString(2, objToInsert.getName());
            s.setBoolean(3, objToInsert.getAvailable());
            s.executeUpdate();
            
        });
        
    }

    @Override
    public FreeInf findByID(int ID) {
        ArrayList<FreeInf> ans = new ArrayList<>();
        String findByIdQuery = "SELECT * FROM free_inf WHERE free_inf_id = ?";
        
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
        
        String deleteQuery = "DELETE FROM free_inf WHERE free_inf_id = ?";
        
        DBUtils.performOperation(conn -> {
        
            PreparedStatement s = conn.prepareStatement(deleteQuery);
            s.setInt(1, ID);
            s.executeUpdate();
            
        });
        
    }

    @Override
    protected FreeInf parseSQLResult(ResultSet r) throws SQLException {
        
        FreeInf p = new FreeInf(r.getInt("free_inf_id"));
        p.setName(r.getString("name"));
        p.setAvailable(r.getBoolean("available"));
        
        return p;
        
    }

}