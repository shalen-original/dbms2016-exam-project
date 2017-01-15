/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */
package mpm.data.dao;

import java.util.ArrayList;
import java.util.List;
import mpm.data.dao.RequestDAO.RequestWIDResolvedToStrings;
import mpm.data.entities.Message;
import mpm.data.entities.Request;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 * Tests for the RequestDAO.java class
 * @author Matteo Nardini
 */
public class RequestDAOTest extends GenericDAOTestHelper<Request>{
    
    List<RequestWIDResolvedToStrings> deepFullTableList;
    
    public RequestDAOTest() 
    {
        this.dao = new RequestDAO();
      
        // <editor-fold defaultstate="collapsed" desc="Filling fullTableList">          
        // Filling initial list
        this.fullTableList = new ArrayList<>();
        Request p;
        p = new Request(401);
        p.setTitle("Parts to print");
        p.setProjectId(100);
        p.setTechInfId(501);
        p.setAssignedUserId(10);
        this.fullTableList.add(p);
        
        p = new Request(402);
        p.setTitle("Something to print");
        p.setProjectId(100);
        p.setTechInfId(502);
        p.setAssignedUserId(11);
        this.fullTableList.add(p);
        
        p = new Request(403);
        p.setTitle("Something else to print");
        p.setProjectId(101);
        p.setTechInfId(503);
        p.setAssignedUserId(10);
        this.fullTableList.add(p);
        
        p = new Request(404);
        p.setTitle("Request without messages");
        p.setProjectId(101);
        p.setTechInfId(501);
        p.setAssignedUserId(12);
        this.fullTableList.add(p);
        
        p = new Request(405);
        p.setTitle("Request not assigned to anyone");
        p.setProjectId(101);
        p.setTechInfId(501);
        p.setAssignedUserId(null);
        this.fullTableList.add(p);
        // </editor-fold> 
        
        // <editor-fold defaultstate="collapsed" desc="Filling deepFullTableList">
        this.deepFullTableList = new ArrayList<>();
        RequestWIDResolvedToStrings a;
        
        a = new RequestWIDResolvedToStrings(fullTableList.get(0));
        a.setProjectName("Board Game");
        a.setAssignedToName("Remo Scolati");
        a.setTechInfName("3D printer 101");
        deepFullTableList.add(a);
        
        a = new RequestWIDResolvedToStrings(fullTableList.get(1));
        a.setProjectName("Board Game");
        a.setAssignedToName("Mikel Grabocka");
        a.setTechInfName("3D printer 102");
        deepFullTableList.add(a);
        
        a = new RequestWIDResolvedToStrings(fullTableList.get(2));
        a.setProjectName("Arduino Workshop");
        a.setAssignedToName("Remo Scolati");
        a.setTechInfName("3D printer 103");
        deepFullTableList.add(a);
        
        a = new RequestWIDResolvedToStrings(fullTableList.get(3));
        a.setProjectName("Arduino Workshop");
        a.setAssignedToName("Matteo Nardini");
        a.setTechInfName("3D printer 101");
        deepFullTableList.add(a);
        
        a = new RequestWIDResolvedToStrings(fullTableList.get(4));
        a.setProjectName("Arduino Workshop");
        a.setAssignedToName(null);
        a.setTechInfName("3D printer 101");
        deepFullTableList.add(a);
        
        // </editor-fold>
        
        // Setting up update test
        this.objectToUpdate = new Request(401);
        this.objectToUpdate.setTitle("NEW TITLE");
        this.objectToUpdate.setProjectId(101);
        this.objectToUpdate.setTechInfId(502);
        this.objectToUpdate.setAssignedUserId(11);
        
        this.objectToRevertUpdate = this.fullTableList.get(0);
        
        // Setting up insert test
        this.objectToInsert = new Request(400);
        this.objectToInsert.setTitle("INSERTED TITLE");
        this.objectToInsert.setProjectId(100);
        this.objectToInsert.setTechInfId(503);
        this.objectToInsert.setAssignedUserId(10);
        
        // Setting up delete test
        this.objectToDelete = this.fullTableList.get(3);
    }

    @Test
    public void findByProjectIDTest()
    {
        ArrayList<Request> expected = new ArrayList<>();
        expected.add(this.fullTableList.get(0));
        expected.add(this.fullTableList.get(1));
        
        List<Request> result = ((RequestDAO)dao).findByProjectID(100);
        
        this.listEquals(expected, result);  
    }
    
    @Test
    public void deepGetAllTest()
    {
        List<RequestWIDResolvedToStrings> result = ((RequestDAO)dao).deepGetAll();
        
        listDeepEquals(this.deepFullTableList, result);    
    }
    
    @Test
    public void deepGetUnassignedRequestsTest()
    {
        ArrayList<RequestWIDResolvedToStrings> expected = new ArrayList<>();
        expected.add(this.deepFullTableList.get(4));
        
        List<RequestWIDResolvedToStrings> result = ((RequestDAO)dao).deepGetUnassignedRequests();
        
        listDeepEquals(expected, result);
    }
    
    @Test
    public void deepFindByAssignedUserIdTest()
    {
        ArrayList<RequestWIDResolvedToStrings> expected = new ArrayList<>();
        expected.add(this.deepFullTableList.get(0));
        expected.add(this.deepFullTableList.get(2));
        
        List<RequestWIDResolvedToStrings> result = ((RequestDAO)dao).deepFindByAssignedUserId(10);
        
        listDeepEquals(result, expected);
    }
    
    @Test
    public void createRequestWithMessageTest()
    {
        int tmp = objectToInsert.getAssignedUserId();
        objectToInsert.setAssignedUserId(null);
        
        ((RequestDAO)dao).createRequestWithMessage(objectToInsert, "I guess that this is a test message", 11);
        
        List<Request> l = dao.getAll();
        boolean ok = false;
        for (Request r : l)
        {
            if (testEquals(r, objectToInsert))
                ok = true;
        }
        
        if (!ok)
            fail(objectToInsert + " was not inserted.");
        
        List<Message> ml = ((new MessageDAO()).getAll());
        ok = false;
        for (Message m : ml)
        {
            if (m.getRequestId() == objectToInsert.getId() &&
                m.getAuthorId() == 11 &&
                m.getText().equals("I guess that this is a test message"))
                ok = true;
        }
        
        if (!ok)
            fail("Cannot find the message that should have been created with the request " + objectToInsert);
        
        dao.delete(objectToInsert);
        objectToInsert.setAssignedUserId(tmp);
    }
    
    @Override
    protected boolean testEquals(Request a, Request b) 
    {
        if (a.getId() != b.getId())
            return false;
        
        if (!a.getTitle().equals(b.getTitle()))
            return false;
        
        if (a.getProjectId() != b.getProjectId())
            return false;
        
        if (a.getTechInfId() != b.getTechInfId())
            return false;
        
        if (a.getAssignedUserId() != b.getAssignedUserId())
            return false;
        
        return true;
    }
    
    private boolean testEqualsDeep(RequestWIDResolvedToStrings a, RequestWIDResolvedToStrings b)
    {
        if (!testEquals(a.getRequest(), b.getRequest()))
            return false;
        
        if (!a.getProjectName().equals(b.getProjectName()))
            return false;
           
        if (!a.getTechInfName().equals(b.getTechInfName()))
            return false;
        
        if (a.getAssignedToName() == null && b.getAssignedToName() == null)
            return true; // If both null ok.
        
        if (a.getAssignedToName() == null || b.getAssignedToName() == null)
            return false; // If this one is null and one is not. Not good.
        
        if (!a.getAssignedToName().equals(b.getAssignedToName()))
            return false;
        
        return true;
    }
    
    private boolean listDeepEquals(List<RequestWIDResolvedToStrings> first, List<RequestWIDResolvedToStrings> second)
    {
        first.sort((a,b) -> {
            return Integer.compare(a.getRequest().getId(), b.getRequest().getId());  
        });
        
        second.sort((a,b) -> {
            return Integer.compare(a.getRequest().getId(), b.getRequest().getId());  
        });
        
        if (first.size() != second.size())
            return false;
        
        for (int i = 0; i < first.size(); i++)
        {
            if (!testEqualsDeep(first.get(i), second.get(i)))
            {
                fail(first.get(i) + " is different from " + second.get(i));
            };
        }     
        return true;
    }
    
}
