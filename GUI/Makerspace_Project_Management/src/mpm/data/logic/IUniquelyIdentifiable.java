/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.logic;

/**
 * Describes a uniquely identifiable object, that is, an object which exposes
 * a unique id. This unique id should also be immutable.
 * @author Matteo Nardini
 */
public interface IUniquelyIdentifiable {

    public int getId();
    
}