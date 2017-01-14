/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.logic;


public class IDHider <T> {

    private T hidden;
    private String displayName;

    public IDHider(T hiddenField, String displayName) {
        this.hidden = hiddenField;
        this.displayName = displayName;
    }

    public T getHidden() {
        return hidden;
    }

    public void setHidden(T hidden) {
        this.hidden = hidden;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
    
    
    
}