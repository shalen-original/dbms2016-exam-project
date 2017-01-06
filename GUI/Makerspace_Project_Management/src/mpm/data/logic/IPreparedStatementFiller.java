/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.logic;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Describes a generic method that fills the parameters of a prepared statement
 * @author Matteo Nardini
 */
public interface IPreparedStatementFiller {
    public void accept(PreparedStatement s) throws SQLException;
}