/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.entities;

import java.sql.Timestamp;
import mpm.data.logic.IUniquelyIdentifiable;

/**
 * Represents a message bound to a request.
 * @author Matteo Nardini
 */
public class Message implements IUniquelyIdentifiable{
    
    private int id;
    private String text;
    private int requestId;
    private Timestamp creationTime;
    private int authorId;

    public Message(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public Timestamp getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
    
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        
        b.append("Message[");
        b.append(id);
        b.append(", request: ");
        b.append(requestId);
        b.append(",");
        b.append(creationTime);
        b.append(", author: ");
        b.append(authorId);
        b.append(",");
        b.append(text);
        b.append("]");
        
        return b.toString();
        
    }

}