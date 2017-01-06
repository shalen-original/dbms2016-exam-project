/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.dao;

import java.util.List;
import mpm.data.logic.GenericDataAccessObject;
import mpm.data.logic.IUniquelyIdentifiable;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 * Generic DAO tester. Provides easy testing of DAOs requiring only minimal configuration.
 * @author Matteo Nardini
 * @param <T> The type of the data handled by the DAO.
 */
public abstract class GenericDAOTestHelper<T extends IUniquelyIdentifiable> {

    protected List<T> fullTableList;
    protected GenericDataAccessObject<T> dao;
    
    protected T objectToUpdate;
    protected T objectToRevertUpdate;
    
    protected T objectToInsert;
    protected T objectToDelete;
    
    @Test
    public void testGetAll() 
    {
        List<T> result = dao.getAll();
        
        listEquals(result, this.fullTableList);
    }
    
    @Test
    public void testUpdate() 
    {       
        dao.update(this.objectToUpdate);
        T res = dao.findByID(objectToUpdate.getId());
        
        if (!testEquals(res, objectToUpdate))
        {
            fail(res + " is not equal to " + objectToUpdate + 
                 "\nRemember to fix database before testing again");
        }
        
        dao.update(this.objectToRevertUpdate);  
    }
    
    @Test
    public void testInsert() 
    {
        dao.insert(this.objectToInsert); 
        List<T> all = dao.getAll();
        
        boolean ok = false;
        for (T item : all)
        {
            if (testEquals(item, objectToInsert))
                ok = true;
        }
        
        if (!ok)
        {
            fail("The new " + objectToInsert + " was not inserted." + 
                 "Remember to fix the database before testing again");
        }
        
        dao.delete(objectToInsert);
    }
    
    @Test
    public void testFindByID() 
    {
        T toFind = this.fullTableList.get(0);
        T found = dao.findByID(toFind.getId());
       
        if (!testEquals(toFind, found))
        {
            fail(toFind + " is different from " + found);
        }
    }
    
    @Test
    public void testDeleteByID() 
    {
        dao.delete(this.objectToDelete);
        List<T> all = dao.getAll();

        for (T item : all)
        {
            if (testEquals(item, objectToDelete))
                fail(objectToDelete + " was not deleted." + 
                    "Remember to fix the database before testing again");
        }
        
        dao.insert(objectToDelete);
    }
    
    protected abstract boolean testEquals(T a, T b);
    
    protected boolean listEquals(List<T> first, List<T> second)
    {
        first.sort((a,b) -> {
            return Integer.compare(a.getId(), b.getId());  
        });
        
        second.sort((a,b) -> {
            return Integer.compare(a.getId(), b.getId());  
        });
        
        if (first.size() != second.size())
            return false;
        
        for (int i = 0; i < first.size(); i++)
        {
            if (!testEquals(first.get(i), second.get(i)))
            {
                fail(first.get(i) + " is different from " + second.get(i));
            };
        }     
        return true;
    }
    
}