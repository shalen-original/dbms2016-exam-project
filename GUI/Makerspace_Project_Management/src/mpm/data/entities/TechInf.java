/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.entities;

import mpm.data.logic.IUniquelyIdentifiable;

/**
 * Represents a single technical infrastructure of the Makerspace
 * @author Matteo Nardini
 */
public class TechInf implements IUniquelyIdentifiable{

    private int id;
    private String name;
    private boolean available;

    public TechInf(int id) {
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

    public boolean getAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        
        b.append("TechInf[");
        b.append(id);
        b.append(",");
        b.append(name);
        b.append(",");
        b.append(available ? "available" : "not available");
        b.append("]");
        
        return b.toString();
        
    }
    
}