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
import mpm.data.entities.GeneralRole;
import mpm.data.logic.DBUtils;
import mpm.data.logic.GenericDataAccessObject;


public class GeneralRoleDAO extends GenericDataAccessObject<GeneralRole>{

    @Override
    public List<GeneralRole> getAll() {
        return genericGetAll("general_role");
    }

    @Override
    public void update(GeneralRole objToWrite) {
        String updateQuery = "UPDATE general_role SET name = ?, " +
                            "description = ? WHERE role_id = ?";
        
        DBUtils.performOperation(conn -> {
        
            PreparedStatement s = conn.prepareStatement(updateQuery);
            s.setString(1, objToWrite.getName());
            s.setString(2, objToWrite.getDescription());
            s.setInt(3, objToWrite.getId());
            s.executeUpdate();
            
        });
    }

    @Override
    public void insert(GeneralRole objToInsert) {
        
        String insertQuery = "INSERT INTO general_role(role_id, name, description)" + 
                            "VALUES (?, ?, ?)";
        
        DBUtils.performOperation(conn -> {
        
            PreparedStatement s = conn.prepareStatement(insertQuery);
            s.setInt(1, objToInsert.getId());
            s.setString(2, objToInsert.getName());
            s.setString(3, objToInsert.getDescription());
            s.executeUpdate();
            
        });
        
    }

    @Override
    public GeneralRole findByID(int ID) {
        
        ArrayList<GeneralRole> ans = new ArrayList<>();
        String findByIdQuery = "SELECT * FROM general_role WHERE role_id = ?";
        
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
        
        String deleteQuery = "DELETE FROM general_role WHERE role_id = ?";
        
        DBUtils.performOperation(conn -> {
        
            PreparedStatement s = conn.prepareStatement(deleteQuery);
            s.setInt(1, ID);
            s.executeUpdate();
            
        });
        
    }

    @Override
    protected GeneralRole parseSQLResult(ResultSet r) throws SQLException {
        
        GeneralRole p = new GeneralRole(r.getInt("role_id"));
        p.setName(r.getString("name"));
        p.setDescription(r.getString("description"));
        
        return p;
        
    }

}