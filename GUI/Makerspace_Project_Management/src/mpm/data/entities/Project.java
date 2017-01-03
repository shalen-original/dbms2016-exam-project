/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.entities;

import mpm.data.logic.IUniquelyIdentifiable;

/**
 * Represents a single Makerspace Project
 * @author Matteo Nardini
 */
public class Project implements IUniquelyIdentifiable
{

    private int id;
    private ProjectStatus status;
    private String title;
    private String description;
    private Boolean seekingCollaboration;

    public Project(int id)
    {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getSeekingCollaboration() {
        return seekingCollaboration;
    }

    public void setSeekingCollaboration(Boolean seekingCollaboration) {
        this.seekingCollaboration = seekingCollaboration;
    }
    
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        
        b.append("Project[");
        b.append(id);
        b.append(",");
        b.append(title);
        b.append(",");
        b.append(description);
        b.append(",");
        b.append(status.toString());
        b.append(",");
        b.append(seekingCollaboration.toString());
        b.append("]");
        
        return b.toString();
        
    }
    
}