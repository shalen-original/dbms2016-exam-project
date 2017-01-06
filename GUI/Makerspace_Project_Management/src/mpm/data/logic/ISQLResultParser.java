/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.logic;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Analyzes a single row of a <i>ResultSet</i> and converts it to a object
 * of type T. This method does not call <i>next()</i> on the ResultSet object.
 * @author Matteo Nardini
 * @param <T> The type of the result
 */
public interface ISQLResultParser<T> {
    public T accept (ResultSet r) throws SQLException;
}