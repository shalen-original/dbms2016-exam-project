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
    
    public static ProjectDAO project = new ProjectDAO();
    public static FreeInfDAO free_inf = new FreeInfDAO();
    public static BookingDAO booking = new BookingDAO();

}