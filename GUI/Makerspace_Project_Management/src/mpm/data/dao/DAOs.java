/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.dao;

/**
 * Contains a static instance of each DAO. If memory usage grows to much,
 * this approach can be changed.
 * @author Matteo Nardini
 */
public class DAOs {
    
    public static ProjectDAO projects = new ProjectDAO();
    public static FreeInfDAO free_infs = new FreeInfDAO();
    public static BookingDAO bookings = new BookingDAO();
    public static GeneralRoleDAO roles = new GeneralRoleDAO();
    public static UserDAO users = new UserDAO();
    public static MaterialDAO materials = new MaterialDAO();

}