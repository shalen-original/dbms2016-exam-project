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
import mpm.data.entities.Message;
import mpm.data.entities.User;
import mpm.data.logic.DBUtils;
import mpm.data.logic.GenericDataAccessObject;
import mpm.data.logic.IPreparedStatementFiller;
import mpm.data.logic.ISQLResultParser;
import mpm.data.logic.Pair;

/**
 * Implements a DAO for the Message table.
 * @author Matteo Nardini
 */
public class MessageDAO extends GenericDataAccessObject<Message>{

    public MessageDAO()
    {
        super("message", "message_id");
    }
    
    @Override
    public void update(Message objToWrite) 
    {
        String sql = "UPDATE message SET message_text = ?, " +
                    "request_id = ?, message_time = ?, " +
                    "message_author = ? WHERE message_id = ?";
        
        DBUtils.performUID(sql, s -> {
            s.setString(1, objToWrite.getText());
            s.setInt(2, objToWrite.getRequestId());
            s.setTimestamp(3, objToWrite.getCreationTime());
            s.setInt(4, objToWrite.getAuthorId());
            s.setInt(5, objToWrite.getId());
        });
    }

    @Override
    public void insert(Message objToInsert) 
    {
        String sql = "INSERT INTO message(message_id, message_text, " + 
                            "request_id, message_time, message_author) " + 
                            "VALUES (?, ?, ?, ?, ?)";

        DBUtils.performUID(sql, s -> {
            s.setInt(1, objToInsert.getId());
            s.setString(2, objToInsert.getText());
            s.setInt(3, objToInsert.getRequestId());
            s.setTimestamp(4, objToInsert.getCreationTime());
            s.setInt(5, objToInsert.getAuthorId());   
        });
    }
    
    public List<Message> findByRequestID(int requestID){
        
        String sql = "SELECT * FROM message " +
                    "WHERE request_id = ?";
        
        IPreparedStatementFiller f = s -> {s.setInt(1, requestID);};

        return DBUtils.performSelect(sql, f, this.defaultParser);
    }
    
    public List<Pair<Message, User>> findByRequestIDWithAuthorData(int requestID) {
        
        String sql = "SELECT * FROM message m INNER JOIN makerspace_user u "
                + "ON (m.message_author = u.user_id) WHERE request_id = ?";
        
        IPreparedStatementFiller f = s -> {s.setInt(1, requestID);};

        ISQLResultParser<Pair<Message,User>> p = r -> {
            
            User u = DAOs.users.parseSQLResult(r);
            Message m = parseSQLResult(r);

            return new Pair(m, u);
        };
        
        return DBUtils.performSelect(sql, f, p);
        
    }

    @Override
    public Message parseSQLResult(ResultSet r) throws SQLException 
    {
        Message p = new Message(r.getInt("message_id"));
        p.setText(r.getString("message_text"));
        p.setRequestId(r.getInt("request_id"));
        p.setCreationTime(r.getTimestamp("message_time"));
        p.setAuthorId(r.getInt("message_author"));
        
        return p;
    }
    

}