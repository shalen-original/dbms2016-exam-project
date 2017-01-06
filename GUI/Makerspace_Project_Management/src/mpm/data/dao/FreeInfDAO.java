/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import mpm.data.entities.FreeInf;
import mpm.data.logic.DBUtils;
import mpm.data.logic.GenericDataAccessObject;

/**
 * Implements a DAO for the Free_Infrastructure table.
 * @author Matteo Nardini
 */
public class FreeInfDAO extends GenericDataAccessObject<FreeInf> {

    public FreeInfDAO()
    {
        this.associatedTableName = "free_inf";
        
        this.findByIdQuery = "SELECT * FROM free_inf WHERE free_inf_id = ?";
        this.deleteByIdQuery = "DELETE FROM free_inf WHERE free_inf_id = ?";
    }

    @Override
    public void update(FreeInf objToWrite) {
        
        String sql = "UPDATE free_inf SET name = ?, " +
                            "available = ? WHERE free_inf_id = ?";
        
       DBUtils.performUID(sql, s -> {
            s.setString(1, objToWrite.getName());
            s.setBoolean(2, objToWrite.getAvailable());
            s.setInt(3, objToWrite.getId());  
        });
        
    }

    @Override
    public void insert(FreeInf objToInsert) {
        
        String sql = "INSERT INTO free_inf(free_inf_id, " + 
                            "name, available) VALUES (?, ?, ?)";
        
        DBUtils.performUID(sql, s -> {
            s.setInt(1, objToInsert.getId());
            s.setString(2, objToInsert.getName());
            s.setBoolean(3, objToInsert.getAvailable()); 
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