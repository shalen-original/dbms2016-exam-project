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
 * Represents a single free infrastructure booking of the Makerspace
 * @author Matteo Nardini
 */
public class FreeInfBooking implements IUniquelyIdentifiable{
    
    private int id;
    private int projectId;
    private int freeInfId;
    private Timestamp startTime;
    private Timestamp endTime;

    public FreeInfBooking(int id) {
        this.id = id;
    }
    
    @Override
    public int getId() {
        return id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getFreeInfId() {
        return freeInfId;
    }

    public void setFreeInfId(int freeInfId) {
        this.freeInfId = freeInfId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        
        b.append("FreeInfBooking[");
        b.append(id);
        b.append(", project: ");
        b.append(projectId);
        b.append(", freeInf: ");
        b.append(freeInfId);
        b.append(", ");
        b.append(startTime);
        b.append(", ");
        b.append(endTime);
        b.append("]");
        
        return b.toString();
        
    }
}