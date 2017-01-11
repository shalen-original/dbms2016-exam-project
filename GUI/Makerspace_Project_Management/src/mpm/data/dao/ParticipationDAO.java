/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mpm.data.entities.Participation;
import mpm.data.entities.Project;
import mpm.data.entities.ProjectRole;
import mpm.data.entities.User;
import mpm.data.logic.DBUtils;
import mpm.data.logic.GenericDataAccessObject;
import mpm.data.logic.IPreparedStatementFiller;
import mpm.data.logic.ISQLResultParser;
import mpm.data.logic.Pair;

/**
 * Implements a DAO for the Participation table.
 * @author Matteo Nardini
 */
public class ParticipationDAO extends GenericDataAccessObject<Participation>{

    public ParticipationDAO()
    {
        super("participation", "participation_id");
    }
    
    @Override
    public void update(Participation objToWrite) 
    {
        String sql = "UPDATE participation SET project_id = ?, " +
                    "user_id = ?, project_role = ?::p_role WHERE participation_id = ?";
        
        DBUtils.performUID(sql, s -> {
            s.setInt(1, objToWrite.getProjectId());
            s.setInt(2, objToWrite.getUserId());
            s.setString(3, objToWrite.getRole().toString().toLowerCase());
            s.setInt(4, objToWrite.getId());
        });
    }

    @Override
    public void insert(Participation objToInsert) 
    {
        String sql = "INSERT INTO participation(participation_id," + 
                    "project_id, user_id, project_role) " + 
                    "VALUES (?, ?, ?, ?::p_role)";

        DBUtils.performUID(sql, s -> {
             s.setInt(1, objToInsert.getId());
             s.setInt(2, objToInsert.getProjectId());
             s.setInt(3, objToInsert.getUserId());
             s.setString(4, objToInsert.getRole().toString().toLowerCase()); 
         });
    }

    public List<Participation> findByProjectID(int projectID){
        
        String sql = "SELECT * FROM participation " +
                    "WHERE project_id = ?";
        
        IPreparedStatementFiller f = s -> {
            s.setInt(1, projectID);        
        };

        return DBUtils.performSelect(sql, f, this.defaultParser);
    }
    
    public List<Pair<Participation, User>> getUserParticipatingToProjectWithRole(int projectID)
    {
        String sql = "SELECT * FROM participation NATURAL JOIN makerspace_user"
                    + " WHERE project_id = ?";
        
        IPreparedStatementFiller f = s -> {
            s.setInt(1, projectID);        
        };
        
        ISQLResultParser<Pair<Participation, User>> p = r -> {
            
            Participation pa = parseSQLResult(r);
            
            User u = new User(r.getInt("user_id"));
            u.setName(r.getString("name"));
            u.setGeneralRoleId(r.getInt("user_role"));
            u.setEmail(r.getString("email"));

            return new Pair(pa, u);
        };
        
        return DBUtils.performSelect(sql, f, p);
    }
    
    public List<ProjectRole> getUserRolesInProject(int userID, int projectID){
        
        String sql = "SELECT * FROM participation " +
                    "WHERE user_id = ? " +
                    "AND project_id = ?";
        
        IPreparedStatementFiller f = s -> {
            s.setInt(1, userID);
            s.setInt(2, projectID);        
        };
        
        ISQLResultParser<ProjectRole> p = r -> {
            return ProjectRole.fromString(r.getString("project_role"));
        };

        return DBUtils.performSelect(sql, f, p);
    }
    
    public List<ProjectRole> getUserRolesInProject(User user, Project project){
        return getUserRolesInProject(user.getId(), project.getId());
    }
    
    public boolean isUserAdminInProject(int userID, int projectID){
        return getUserRolesInProject(userID, projectID).contains(ProjectRole.ADMINISTRATOR);
    }
    
    public boolean isUserAdminInProject(User user, Project project){
        return getUserRolesInProject(user.getId(), project.getId())
                .contains(ProjectRole.ADMINISTRATOR);
    }
    
    @Override
    protected Participation parseSQLResult(ResultSet r) throws SQLException 
    {
        Participation p = new Participation(r.getInt("participation_id"));
        p.setProjectId(r.getInt("project_id"));
        p.setUserId(r.getInt("user_id"));
        p.setRole(ProjectRole.fromString(r.getString("project_role")));
        
        return p;
    }

}