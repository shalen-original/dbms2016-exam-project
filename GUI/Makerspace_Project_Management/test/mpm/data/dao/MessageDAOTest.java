/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */
package mpm.data.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import mpm.data.entities.Message;
import mpm.data.entities.User;
import mpm.data.logic.Pair;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 * Tests for the MessageDAO.java class
 * @author Matteo Nardini
 */
public class MessageDAOTest extends GenericDAOTestHelper<Message>{
    
    public MessageDAOTest() 
    {
        this.dao = new MessageDAO();
      
        // Filling initial list
        this.fullTableList = new ArrayList<>();
        Message p;
        p = new Message(601);
        p.setText("Need to print some things");
        p.setRequestId(401);
        p.setCreationTime(Timestamp.valueOf("2016-01-16 15:30:26"));
        p.setAuthorId(12);
        this.fullTableList.add(p);
        p = new Message(602);
        p.setText("Need to print some more things");
        p.setRequestId(402);
        p.setCreationTime(Timestamp.valueOf("2016-01-16 16:45:16"));
        p.setAuthorId(12);
        this.fullTableList.add(p);
        p = new Message(603);
        p.setText("Need to print even more things");
        p.setRequestId(403);
        p.setCreationTime(Timestamp.valueOf("2016-01-17 10:35:55"));
        p.setAuthorId(10);
        this.fullTableList.add(p);
        

        // Setting up update test
        this.objectToUpdate = new Message(601);
        this.objectToUpdate.setText("NEW TEXT");
        this.objectToUpdate.setRequestId(403);
        this.objectToUpdate.setCreationTime(Timestamp.valueOf("2016-02-16 19:45:56"));
        this.objectToUpdate.setAuthorId(11);
        
        this.objectToRevertUpdate = this.fullTableList.get(0);
        
        // Setting up insert test
        this.objectToInsert = new Message(600);
        this.objectToInsert.setText("NEW TEXT");
        this.objectToInsert.setRequestId(401);
        this.objectToInsert.setCreationTime(Timestamp.valueOf("2017-02-16 19:12:56"));
        this.objectToInsert.setAuthorId(12);
        
        // Setting up delete test
        this.objectToDelete = this.fullTableList.get(0);
    }
    
    @Test
    public void findByRequestIDTest()
    {
        ArrayList<Message> expected = new ArrayList<>();
        expected.add(this.fullTableList.get(0));
        
        List<Message> result = ((MessageDAO)dao).findByRequestID(401);
        
        this.listEquals(expected, result);  
    }
    
    @Test
    public void findByRequestIDWithAuthorData()
    {
        ArrayList<Pair<Message,User>> expected = new ArrayList<>();
        User u = new User(12);
        u.setName("Matteo Nardini");
        u.setGeneralRoleId(1021);
        u.setEmail("mnardini@unibz.it");
        
        expected.add(new Pair(this.fullTableList.get(0),u));
        
        List<Pair<Message,User>> result = ((MessageDAO)dao).findByRequestIDWithAuthorData(401);
        
        
        
        expected.sort((a,b) -> {
            return Integer.compare(a.getFirst().getId(), b.getFirst().getId());  
        });
        
        result.sort((a,b) -> {
            return Integer.compare(a.getFirst().getId(), b.getFirst().getId());  
        });
        
        if (expected.size() != result.size())
            fail("expected and result do not have the same size");
        
        for (int i = 0; i < expected.size(); i++)
        {
  
            if (!testEquals(expected.get(i).getFirst(), result.get(i).getFirst()))
            {
                fail(expected.get(i).getFirst() + " is different from " + result.get(i).getSecond());
            }
            
            User a = expected.get(i).getSecond();
            User b = result.get(i).getSecond();
            
            if (a.getId() != b.getId() ||
                    !a.getName().equals(b.getName()) ||
                    !a.getEmail().equals(b.getEmail()) ||
                    a.getGeneralRoleId() != b.getGeneralRoleId())
                fail(a + " is different from " + b);
        }     
    }
    
    @Override
    protected boolean testEquals(Message a, Message b) 
    {
        if (a.getId() != b.getId())
            return false;
        
        if (!a.getText().equals(b.getText()))
            return false;
        
        if (a.getAuthorId() != b.getAuthorId())
            return false;
        
        if (a.getRequestId() != b.getRequestId())
            return false;
        
        if (!a.getCreationTime().equals(b.getCreationTime()))
            return false;
        
        return true;  
    }
    
}
