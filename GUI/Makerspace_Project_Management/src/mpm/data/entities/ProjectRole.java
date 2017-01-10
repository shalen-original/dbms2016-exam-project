/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.entities;

/**
 * Contains the possible roles that a user can have in a Makerspace Project.
 * @author Matteo Nardini
 */
public enum ProjectRole
{
    ADMINISTRATOR,
    COLLABORATOR,
    RETIRED;
    
    public static ProjectRole fromString(String s)
    {
        switch (s.toLowerCase())
        {
            case "administrator":
                return ADMINISTRATOR;
            case "collaborator":
                return COLLABORATOR;
            case "retired":
                return RETIRED;
            default:
                throw new IllegalArgumentException("The string \"" + s + "\" is not a valid ProjectRole string");
        }
    }
    
    @Override
    public String toString()
    {
        if (this == ADMINISTRATOR)
            return "Administrator";
        
        if (this == COLLABORATOR)
            return "Collaborator";
        
        if (this == RETIRED)
            return "Retired";
        
        return "Error: ProjectRole.toString() should not print this line";
    }
}
