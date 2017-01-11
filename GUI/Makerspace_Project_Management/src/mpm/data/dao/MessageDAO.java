/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import mpm.data.entities.Message;
import mpm.data.logic.DBUtils;
import mpm.data.logic.GenericDataAccessObject;

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

    @Override
    protected Message parseSQLResult(ResultSet r) throws SQLException 
    {
        Message p = new Message(r.getInt("message_id"));
        p.setText(r.getString("message_text"));
        p.setRequestId(r.getInt("request_id"));
        p.setCreationTime(r.getTimestamp("message_time"));
        p.setAuthorId(r.getInt("message_author"));
        
        return p;
    }
    

}