/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.entities;

import mpm.data.logic.IUniquelyIdentifiable;

/**
 * Represents a generic user of the Makerspace
 * @author Matteo Nardini
 */
public class User implements IUniquelyIdentifiable{
    
    private int id;
    private String name;
    private int generalRoleId;
    private String email;

    public User(int id) {
        this.id = id;
    }
    
    @Override
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGeneralRoleId() {
        return generalRoleId;
    }

    public void setGeneralRoleId(int generalRoleId) {
        this.generalRoleId = generalRoleId;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }
    
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        
        b.append("User[");
        b.append(id);
        b.append(",");
        b.append(name);
        b.append(",");
        b.append(email);
        b.append(", general_role:");
        b.append(generalRoleId);
        b.append("]");
        
        return b.toString();
        
    }

}