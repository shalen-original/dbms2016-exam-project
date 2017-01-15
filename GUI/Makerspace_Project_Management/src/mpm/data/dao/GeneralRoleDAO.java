/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import mpm.data.entities.GeneralRole;
import mpm.data.logic.DBUtils;
import mpm.data.logic.GenericDataAccessObject;


public class GeneralRoleDAO extends GenericDataAccessObject<GeneralRole>{

    public GeneralRoleDAO()
    {
        super("general_role", "role_id");
    }

    @Override
    public void update(GeneralRole objToWrite) {
        String sql = "UPDATE general_role SET name = ?, " +
                            "description = ? WHERE role_id = ?";
        
        DBUtils.performUID(sql, s -> {
            s.setString(1, objToWrite.getName());
            s.setString(2, objToWrite.getDescription());
            s.setInt(3, objToWrite.getId());
        });
    }

    @Override
    public void insert(GeneralRole objToInsert) {
        
        String sql = "INSERT INTO general_role(role_id, name, description)" + 
                            "VALUES (?, ?, ?)";
        
        DBUtils.performUID(sql, s -> {
            s.setInt(1, objToInsert.getId());
            s.setString(2, objToInsert.getName());
            s.setString(3, objToInsert.getDescription());
        });
        
    }


    @Override
    public GeneralRole parseSQLResult(ResultSet r) throws SQLException {
        
        GeneralRole p = new GeneralRole(r.getInt("role_id"));
        p.setName(r.getString("name"));
        p.setDescription(r.getString("description"));
        
        return p;
        
    }

}