/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.entities;

import mpm.data.logic.IUniquelyIdentifiable;

/**
 * Represents a single free infrastructure of the Makerspace
 * @author Matteo Nardini
 */
public class FreeInf implements IUniquelyIdentifiable {
    
    private int id;
    private String name;
    private Boolean available;

    public FreeInf(int id) {
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

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
    
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        
        b.append("FreeInf[");
        b.append(id);
        b.append(",");
        b.append(name);
        b.append(",");
        b.append(available ? "available" : "not available");
        b.append("]");
        
        return b.toString();
        
    }

}