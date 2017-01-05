/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.entities;

import mpm.data.logic.IUniquelyIdentifiable;


public class GeneralRole implements IUniquelyIdentifiable{
    
    private int id;
    private String name;
    private String description;

    public GeneralRole(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        
        b.append("GeneralRole[");
        b.append(id);
        b.append(", ");
        b.append(name);
        b.append(", ");
        b.append(description);
        b.append("]");
        
        return b.toString();
        
    }

}