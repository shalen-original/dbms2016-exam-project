/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.logic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a generic Data Access Object (DAO) for a uniquely identifiable object.
 * Provides a list of methods that each DAO should have and implements some shortcuts
 * for the simpler methods.
 * @author Matteo Nardini
 * @param <T> The type of the uniquely identifiable object that this DAO is wrapping.
 */
public abstract class GenericDataAccessObject< T extends IUniquelyIdentifiable>
{
    /**
     * Returns the list containing all the memorized T objects.
     * @return The list containing all memorized T objects
     */
    public abstract List<T> getAll();
    
    /**
     * Updates a single object from the memory to the persistent storage.
     * @param objToWrite The object to be written on the persistent storage.
     */
    public abstract void update(T objToWrite);
    
    /**
     * Deletes a single object from the persistent storage.
     * @param objToDelete The object to be deleted from the persistent storage.
     */
    public void delete(T objToDelete)
    {
        deleteByID(objToDelete.getId());
    }
    
    /**
     * Finds a single object in the persistent storage, searching by ID.
     * @param ID The ID of the object to be found in the persistent storage.
     * @return The object with id <i>ID</i> in the persistent storage. If no object
     * with id <i>ID</i> is found, the method returns nothing.
     */
    public abstract T findByID(int ID);
    
    /**
     * Deletes a single object in the persistent storage, given its ID.
     * @param ID The ID of the object to be deleted.
     */
    public abstract void deleteByID(int ID);
    
    /**
     * Analyzes a single row of a <i>ResultSet</i> and converts it to a object
     * of type T. This method does not call <i>next()</i> on the ResultSet object.
     * @param r The ResultSet placed in the right position.
     * @return An object of type T with the fields correctly initialized from the current
     * row of the ResultSet.
     * @throws SQLException If a database error occours.
     */
    protected abstract T parseSQLResult(ResultSet r) throws SQLException;
    
    /**
     * Provides an easy implementation for the <i>getAll()</i> method.
     * @param tableName The name of the table to be read.
     * @return The list containing all memorized T objects.
     */
    protected List<T> genericGetAll(String tableName)
    {
        ArrayList<T> ans = new ArrayList<>();
        
        DBUtils.performOperation(conn -> {
        
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery("SELECT * FROM " + tableName);
            
            while (r.next())
            {
                ans.add(parseSQLResult(r));
            }
        
        });

        return ans;
    }
}