/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.entities;

import mpm.data.logic.IUniquelyIdentifiable;

/**
 * Represents the participation of a single user to a project
 * @author Matteo Nardini
 */
public class Participation implements IUniquelyIdentifiable {

    private int id;
    private int userId;
    private int projectId;
    private ProjectRole role;

    public Participation(int id) {
        this.id = id;
    }
    
    @Override
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public ProjectRole getRole() {
        return role;
    }

    public void setRole(ProjectRole role) {
        this.role = role;
    }
    
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        
        b.append("Participation[");
        b.append(id);
        b.append(", user: ");
        b.append(userId);
        b.append(", project: ");
        b.append(projectId);
        b.append(",");
        b.append(role.toString());
        b.append("]");
        
        return b.toString();
        
    }
    
}