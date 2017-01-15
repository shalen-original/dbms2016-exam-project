/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import mpm.data.entities.TechInf;
import mpm.data.logic.DBUtils;
import mpm.data.logic.GenericDataAccessObject;

/**
 * Implements a DAO for the Techincal infrastructure table.
 * @author Matteo Nardini
 */
public class TechInfDAO extends GenericDataAccessObject<TechInf>{

    public TechInfDAO()
    {
        super("technical_inf", "technical_inf_id");
    }
    
    @Override
    public void update(TechInf objToWrite) 
    {
        String sql = "UPDATE technical_inf SET name = ?, " +
                            "available = ? WHERE technical_inf_id = ?";

        DBUtils.performUID(sql, s -> {
            s.setString(1, objToWrite.getName());
            s.setBoolean(2, objToWrite.getAvailable());
            s.setInt(3, objToWrite.getId());  
        });
    }

    @Override
    public void insert(TechInf objToInsert) 
    {
        String sql = "INSERT INTO technical_inf(technical_inf_id, " + 
                            "name, available) VALUES (?, ?, ?)";
        
        DBUtils.performUID(sql, s -> {
            s.setInt(1, objToInsert.getId());
            s.setString(2, objToInsert.getName());
            s.setBoolean(3, objToInsert.getAvailable()); 
        });
    }

    @Override
    public TechInf parseSQLResult(ResultSet r) throws SQLException 
    {
        TechInf p = new TechInf(r.getInt("technical_inf_id"));
        p.setName(r.getString("name"));
        p.setAvailable(r.getBoolean("available"));
        
        return p;
    }

}