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
import java.util.List;
import mpm.data.entities.Participation;
import mpm.data.entities.Project;
import mpm.data.entities.ProjectRole;
import mpm.data.entities.ProjectStatus;
import mpm.data.entities.User;
import mpm.data.logic.DBUtils;
import mpm.data.logic.GenericDataAccessObject;
import mpm.data.logic.IPreparedStatementFiller;
import mpm.main.MPM;

/**
 * Implements a DAO for the Project table.
 * @author Matteo Nardini
 */
public class ProjectDAO extends GenericDataAccessObject<Project>
{
    public ProjectDAO()
    {
        super("project", "project_id");
    }

    @Override
    public void update(Project objToWrite) {
        
        String sql = "UPDATE project SET title = ?, " +
                            "description = ?, status = ?::p_status, " +
                            "seeking_collaboration = ? WHERE project_id = ?";
        
        DBUtils.performUID(sql, s -> {
            s.setString(1, objToWrite.getTitle());
            s.setString(2, objToWrite.getDescription());
            s.setString(3, objToWrite.getStatus().toString().toLowerCase());
            s.setBoolean(4, objToWrite.getSeekingCollaboration());
            s.setInt(5, objToWrite.getId());
        });
        
    }
    
    @Override
    public void insert(Project objToInsert) {
        
        String sql = "INSERT INTO project(project_id, title, " + 
                            "description, status, seeking_collaboration) " + 
                            "VALUES (?, ?, ?, ?::p_status, ?)";
        
       DBUtils.performUID(sql, s -> {
            s.setInt(1, objToInsert.getId());
            s.setString(2, objToInsert.getTitle());
            s.setString(3, objToInsert.getDescription());
            s.setString(4, objToInsert.getStatus().toString().toLowerCase());
            s.setBoolean(5, objToInsert.getSeekingCollaboration());   
        });
        
    }
    
    public List<Project> getUserProjects(int userId){
        
        String sql = "SELECT * " +
                "FROM participation NATURAL JOIN project " +     
                "WHERE user_id = ?";
        
        IPreparedStatementFiller f = s -> {s.setInt(1, userId);};
        
        return DBUtils.performSelect(sql, f, this.defaultParser);
    }
    
    public List<Project> getUserProjects(User user){
        return getUserProjects(user.getId());
    }

    public void createNewProjectWithAdmin(Project p, User admin)
    {
        Participation part = new Participation(DAOs.participations.getNextValidId());
        part.setProjectId(p.getId());
        part.setRole(ProjectRole.ADMINISTRATOR);
        part.setUserId(admin.getId());
        
        DBUtils.beginTransaction();
        this.insert(p);
        DAOs.participations.insert(part);
        DBUtils.endTranstaction();
    }
    
    @Override
    public Project parseSQLResult(ResultSet r) throws SQLException {
        
        Project p = new Project(r.getInt("project_id"));
        p.setTitle(r.getString("title"));
        p.setDescription(r.getString("description"));
        p.setSeekingCollaboration(r.getBoolean("seeking_collaboration"));
        p.setStatus(ProjectStatus.fromString(r.getString("status")));
        
        return p;
        
    }
  
}