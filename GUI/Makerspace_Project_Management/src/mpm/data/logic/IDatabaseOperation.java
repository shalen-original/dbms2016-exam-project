/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.logic;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Describes a generic database operation
 * @author Matteo Nardini
 */
public interface IDatabaseOperation {   
    public void accept(Connection p1) throws SQLException;
}