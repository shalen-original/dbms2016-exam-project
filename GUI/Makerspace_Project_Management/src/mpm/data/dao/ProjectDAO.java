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
import mpm.data.entities.Project;
import mpm.data.entities.ProjectStatus;
import mpm.data.logic.DBUtils;
import mpm.data.logic.GenericDataAccessObject;

/**
 * Implements a DAO for the Project table.
 * @author Matteo Nardini
 */
public class ProjectDAO extends GenericDataAccessObject<Project>
{
    @Override
    public List<Project> getAll() {      
        return genericGetAll("Project");      
    }

    @Override
    public void update(Project objToWrite) {
        
        String updateQuery = "UPDATE project SET title = ?, " +
                            "description = ?, status = ?::p_status, " +
                            "seeking_collaboration = ? WHERE project_id = ?";
        
        DBUtils.performOperation(conn -> {
        
            PreparedStatement s = conn.prepareStatement(updateQuery);
            s.setString(1, objToWrite.getTitle());
            s.setString(2, objToWrite.getDescription());
            s.setString(3, objToWrite.getStatus().toString().toLowerCase());
            s.setBoolean(4, objToWrite.getSeekingCollaboration());
            s.setInt(5, objToWrite.getId());
            s.executeUpdate();
            
        });
        
    }
    
    @Override
    public void insert(Project objToInsert) {
        
        String insertQuery = "INSERT INTO project(project_id, title, " + 
                            "description, status, seeking_collaboration) " + 
                            "VALUES (?, ?, ?, ?::p_status, ?)";
        
        DBUtils.performOperation(conn -> {
        
            PreparedStatement s = conn.prepareStatement(insertQuery);
            s.setInt(1, objToInsert.getId());
            s.setString(2, objToInsert.getTitle());
            s.setString(3, objToInsert.getDescription());
            s.setString(4, objToInsert.getStatus().toString().toLowerCase());
            s.setBoolean(5, objToInsert.getSeekingCollaboration());
            s.executeUpdate();
            
        });
        
    }

    @Override
    public Project findByID(int ID) {
        
        ArrayList<Project> ans = new ArrayList<>();
        String findByIdQuery = "SELECT * FROM project WHERE project_id = ?";
        
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
        
        String deleteQuery = "DELETE FROM project WHERE project_id = ?";
        
        DBUtils.performOperation(conn -> {
        
            PreparedStatement s = conn.prepareStatement(deleteQuery);
            s.setInt(1, ID);
            s.executeUpdate();
            
        });
        
    }   

    @Override
    protected Project parseSQLResult(ResultSet r) throws SQLException {
        
        Project p = new Project(r.getInt("project_id"));
        p.setTitle(r.getString("title"));
        p.setDescription(r.getString("description"));
        p.setSeekingCollaboration(r.getBoolean("seeking_collaboration"));
        p.setStatus(ProjectStatus.fromString(r.getString("status")));
        
        return p;
        
    }
  
}