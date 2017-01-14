/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.entities;

import mpm.data.logic.IUniquelyIdentifiable;

/**
 * Represents a single request for a technical infrastructure
 * @author Matteo Nardini
 */
public class Request implements IUniquelyIdentifiable{

    private int id;
    private String title;
    private int projectId;
    private int techInfId;
    private Integer assignedUserId;

    public Request(int id) {
        this.id = id;
    }
    
    @Override
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getTechInfId() {
        return techInfId;
    }

    public void setTechInfId(int techInfId) {
        this.techInfId = techInfId;
    }

    public Integer getAssignedUserId() {
        return assignedUserId;
    }

    public void setAssignedUserId(Integer assignedUserId) {
        this.assignedUserId = assignedUserId;
    }
    
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        
        b.append("Request[");
        b.append(id);
        b.append(",");
        b.append(title);
        b.append(", project: ");
        b.append(projectId);
        b.append(", tech inf: ");
        b.append(techInfId);
        b.append(", asseigned user: ");
        b.append(assignedUserId);
        b.append("]");
        
        return b.toString();
        
    }
    
}