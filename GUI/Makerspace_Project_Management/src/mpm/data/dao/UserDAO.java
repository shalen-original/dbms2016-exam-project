/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import mpm.data.entities.GeneralRole;
import mpm.data.entities.User;
import mpm.data.logic.DBUtils;
import mpm.data.logic.GenericDataAccessObject;
import mpm.data.logic.IPreparedStatementFiller;

/**
 * Implements a DAO for the Makerspace_User table.
 * @author Matteo Nardini
 */
public class UserDAO extends GenericDataAccessObject<User>{

    public UserDAO()
    {
        super("makerspace_user", "user_id");
    }
    
    @Override
    public void update(User objToWrite) 
    {
        String sql = "UPDATE makerspace_user SET name = ?, " +
                    "user_role = ?, email = ? WHERE user_id = ?";
        
        DBUtils.performUID(sql, s -> {
            s.setString(1, objToWrite.getName());
            s.setInt(2, objToWrite.getGeneralRoleId());
            s.setString(3, objToWrite.getEmail().toLowerCase());
            s.setInt(4, objToWrite.getId());
        });
    }

    @Override
    public void insert(User objToInsert) 
    {
        
        String sql = "INSERT INTO makerspace_user(user_id, name, " + 
                            "user_role, email) " + 
                            "VALUES (?, ?, ?, ?)";
        
        DBUtils.performUID(sql, s -> {
             s.setInt(1, objToInsert.getId());
             s.setString(2, objToInsert.getName());
             s.setInt(3, objToInsert.getGeneralRoleId());
             s.setString(4, objToInsert.getEmail().toLowerCase());  
         });
        
    }
    
    public List<User> findByMail(String email)
    {
        
        String sql = "SELECT * FROM makerspace_user " +
                    "WHERE email = ?";
        
        IPreparedStatementFiller f = s -> {s.setString(1, email);};

        return DBUtils.performSelect(sql, f, this.defaultParser);
    }

    public boolean hasUserRole(User u, String roleName)
    {
        GeneralRole r = DAOs.roles.findByID(u.getGeneralRoleId());
        
        return r.getName().equals(roleName);
    }
    
    public List<User> findByGeneralRole(String generalRoleName)
    {
        String sql = "SELECT u.user_id, u.name, u.user_role, u.email "
                + "FROM makerspace_user u INNER JOIN general_role r "
                + "ON (u.user_role = r.role_id) WHERE r.name = ?";
        
        IPreparedStatementFiller f = s -> {     
            s.setString(1, generalRoleName);          
        };
        
        return DBUtils.performSelect(sql, f, this.defaultParser);
    }
    
    public List<User> getUsersNotPartecipatingInProject(int projectId)
    {
        String sql = "SELECT * FROM makerspace_user "
                + "WHERE user_id "
                + "NOT IN ("
                    + "SELECT u.user_id "
                    + "FROM project NATURAL JOIN participation "
                    + "NATURAL JOIN makerspace_user u WHERE project_id=?"
                + ")";
        
        IPreparedStatementFiller f = s -> {
          s.setInt(1, projectId);
        };
        
        return DBUtils.performSelect(sql, f, defaultParser);
    }
    
    @Override
    public User parseSQLResult(ResultSet r) throws SQLException 
    {
        User p = new User(r.getInt("user_id"));
        p.setName(r.getString("name"));
        p.setGeneralRoleId(r.getInt("user_role"));
        p.setEmail(r.getString("email"));
        
        return p;
    }

}