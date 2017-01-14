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
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;
import mpm.data.entities.Message;
import mpm.data.entities.Request;
import mpm.data.logic.DBUtils;
import mpm.data.logic.GenericDataAccessObject;
import mpm.data.logic.IPreparedStatementFiller;

/**
 * Implements a DAO for the Request table.
 * @author Matteo Nardini
 */
public class RequestDAO extends GenericDataAccessObject<Request> {

    private String insertIntoQuery;
    
    public RequestDAO()
    {
        super("request", "request_id");
        
        insertIntoQuery = "INSERT INTO request(request_id, title, " + 
                            "project_id, technical_inf_id, handled_by_user) " + 
                            "VALUES (?, ?, ?, ?, ?)";
    }
    
    @Override
    public void update(Request objToWrite) 
    {
        String sql = "UPDATE request SET title = ?, " +
                    "project_id = ?, technical_inf_id = ?, " +
                    "handled_by_user = ? WHERE request_id = ?";

        DBUtils.performUID(sql, s -> {
            s.setString(1, objToWrite.getTitle());
            s.setInt(2, objToWrite.getProjectId());
            s.setInt(3, objToWrite.getTechInfId());
            s.setInt(4, objToWrite.getAssignedUserId());
            s.setInt(5, objToWrite.getId());
        });
    }

    @Override
    public void insert(Request objToInsert) 
    {
        DBUtils.performUID(insertIntoQuery, s -> {
            s.setInt(1, objToInsert.getId());
            s.setString(2, objToInsert.getTitle());
            s.setInt(3, objToInsert.getProjectId());
            s.setInt(4, objToInsert.getTechInfId());
            s.setInt(5, objToInsert.getAssignedUserId());   
        });
    }
    
    public List<Request> findByProjectID(int projectID){
        
        String sql = "SELECT * FROM request " +
                    "WHERE project_id = ?";
        
        IPreparedStatementFiller f = s -> {s.setInt(1, projectID);};

        return DBUtils.performSelect(sql, f, this.defaultParser);
    }

    public void createRequestWithMessage(Request r, String messageText, int authorId)
    {
        DBUtils.performOperation(conn -> {
        
            conn.setAutoCommit(false);
            
            try {
                PreparedStatement s = conn.prepareStatement(insertIntoQuery);
                s.setInt(1, r.getId());
                s.setString(2, r.getTitle());
                s.setInt(3, r.getProjectId());
                s.setInt(4, r.getTechInfId());
                s.setNull(5, Types.INTEGER); 
                s.executeUpdate();

                String sql = "INSERT INTO message(message_id, message_text, " + 
                            "request_id, message_time, message_author) " + 
                            "VALUES (?, ?, ?, ?, ?)";
                s = conn.prepareStatement(sql);
                s.setInt(1, DAOs.messages.getNextValidId());
                s.setString(2, messageText);
                s.setInt(3, r.getId());
                s.setTimestamp(4, new Timestamp(System.currentTimeMillis())); 
                s.setInt(5, authorId);
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
    protected Request parseSQLResult(ResultSet r) throws SQLException 
    {
        Request p = new Request(r.getInt("request_id"));
        p.setTitle(r.getString("title"));
        p.setProjectId(r.getInt("project_id"));
        p.setTechInfId(r.getInt("technical_inf_id"));
        
        p.setAssignedUserId(r.getInt("handled_by_user"));
        if (r.wasNull())
            p.setAssignedUserId(null);
        
        return p;
    }

}