/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public FreeInfDAO()
    {
        super("free_inf", "free_inf_id");
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

    public List<FreeInf> getAvailable()
    {
        ArrayList<FreeInf> ans = new ArrayList<>();
        
        DBUtils.performOperation(conn -> {
        
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery("SELECT * FROM free_inf WHERE available = true");
            
            while (r.next())
            {
                ans.add(parseSQLResult(r));
            }
        
        });

        return ans;
    }
    
    @Override
    public FreeInf parseSQLResult(ResultSet r) throws SQLException {
        
        FreeInf p = new FreeInf(r.getInt("free_inf_id"));
        p.setName(r.getString("name"));
        p.setAvailable(r.getBoolean("available"));
        
        return p;
        
    }

}