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
        part.setUserId(MPM.currentUser.getId());
        
        DBUtils.performOperation(conn -> {
        
            conn.setAutoCommit(false);
            
            try {
                String sql = "INSERT INTO project(project_id, title, " + 
                                "description, status, seeking_collaboration) " + 
                                "VALUES (?, ?, ?, ?::p_status, ?)";
                PreparedStatement s = conn.prepareStatement(sql);
                s.setInt(1, p.getId());
                s.setString(2, p.getTitle());
                s.setString(3, p.getDescription());
                s.setString(4, p.getStatus().toString().toLowerCase());
                s.setBoolean(5, p.getSeekingCollaboration()); 
                s.executeUpdate();

                sql = "INSERT INTO participation(participation_id," + 
                        "project_id, user_id, project_role) " + 
                        "VALUES (?, ?, ?, ?::p_role)";
                s = conn.prepareStatement(sql);
                s.setInt(1, part.getId());
                s.setInt(2, part.getProjectId());
                s.setInt(3, part.getUserId());
                s.setString(4, part.getRole().toString().toLowerCase()); 
                s.executeUpdate();
                
                conn.commit();
                            
            }catch (SQLException e){              
                conn.rollback();
                throw e;               
            }finally{
                conn.setAutoCommit(true);
            }
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