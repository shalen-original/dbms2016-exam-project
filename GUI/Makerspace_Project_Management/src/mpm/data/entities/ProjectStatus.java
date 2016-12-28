/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.entities;

/**
 * Contains the possible statuses of a Makerspace Project.
 * @author Shalen
 */
public enum ProjectStatus
{
    PROPOSED,
    ACTIVE,
    COMPLETED;
    
    public static ProjectStatus fromString(String s)
    {
        switch (s)
        {
            case "proposed":
                return PROPOSED;
            case "active":
                return ACTIVE;
            case "completed":
                return COMPLETED;
            default:
                throw new RuntimeException("The string \"" + s + "\" is not a valid ProjectStatus string");
        }
    }
}
