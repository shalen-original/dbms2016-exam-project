/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */
package mpm.data.dao;

import java.util.ArrayList;
import java.util.List;
import mpm.data.entities.Participation;
import mpm.data.entities.Project;
import mpm.data.entities.ProjectRole;
import mpm.data.entities.User;
import mpm.data.logic.Pair;
import static org.junit.Assert.fail;
import org.junit.Test;

/**
 * Tests for the ParticipationDAO.java class
 * @author Matteo Nardini
 */
public class ParticipationDAOTest extends GenericDAOTestHelper<Participation>{
    
    public ParticipationDAOTest() 
    {
        this.dao = new ParticipationDAO();
      
        // Filling initial list
        this.fullTableList = new ArrayList<>();
        Participation p;
        p = new Participation(1);
        p.setProjectId(101);
        p.setUserId(10);
        p.setRole(ProjectRole.ADMINISTRATOR);
        this.fullTableList.add(p);
        
        p = new Participation(2);
        p.setProjectId(101);
        p.setUserId(11);
        p.setRole(ProjectRole.COLLABORATOR);
        this.fullTableList.add(p);
        
        p = new Participation(3);
        p.setProjectId(101);
        p.setUserId(12);
        p.setRole(ProjectRole.COLLABORATOR);
        this.fullTableList.add(p);
        
        p = new Participation(4);
        p.setProjectId(100);
        p.setUserId(10);
        p.setRole(ProjectRole.COLLABORATOR);
        this.fullTableList.add(p);
        
        p = new Participation(5);
        p.setProjectId(100);
        p.setUserId(11);
        p.setRole(ProjectRole.COLLABORATOR);
        this.fullTableList.add(p);
        
        p = new Participation(6);
        p.setProjectId(100);
        p.setUserId(12);
        p.setRole(ProjectRole.ADMINISTRATOR);
        this.fullTableList.add(p);
        
        // Setting up update test
        this.objectToUpdate = new Participation(1);
        this.objectToUpdate.setProjectId(100);
        this.objectToUpdate.setUserId(11);
        this.objectToUpdate.setRole(ProjectRole.RETIRED);
        
        this.objectToRevertUpdate = this.fullTableList.get(0);
        
        // Setting up insert test
        this.objectToInsert = new Participation(7);
        this.objectToInsert.setProjectId(100);
        this.objectToInsert.setUserId(10);
        this.objectToInsert.setRole(ProjectRole.ADMINISTRATOR);
        
        // Setting up delete test
        this.objectToDelete = this.fullTableList.get(0);  
    }
    
    @Test
    public void findByProjectIDTest()
    {
        ArrayList<Participation> expected = new ArrayList<>();
        expected.add(this.fullTableList.get(1));
        
        List<Participation> result = ((ParticipationDAO)dao).findByProjectID(101);
        
        this.listEquals(expected, result);  
    }
    
    @Test
    public void getUserParticipatingToProjectWithRoleTest()
    {
        ArrayList<Pair<Participation, User>> expected = new ArrayList<>();
        User u = new User(10);
        u.setName("Remo Scolati");
        u.setGeneralRoleId(1020);
        u.setEmail("rscolati@unibz.it");
        expected.add(new Pair(this.fullTableList.get(3), u));
        u = new User(11);
        u.setName("Mikel Grabocka");
        u.setGeneralRoleId(1022);
        u.setEmail("mgrabocka@unibz.it");
        expected.add(new Pair(this.fullTableList.get(4), u));
        u = new User(12);
        u.setName("Matteo Nardini");
        u.setGeneralRoleId(1021);
        u.setEmail("mnardini@unibz.it");
        expected.add(new Pair(this.fullTableList.get(5), u));
        
        List<Pair<Participation, User>> result = ((ParticipationDAO)dao).getUserParticipatingToProjectWithRole(100);
         
        if (expected.size() != result.size())
            fail("The number of roles is different");
        
        result.sort((Pair<Participation, User> a, Pair<Participation, User> b) -> {     
            return Integer.compare(a.getFirst().getId(), b.getFirst().getId());    
        });
        
        for (int i = 0; i < expected.size(); i++)
        {
            if (!testEquals(expected.get(i).getFirst(), result.get(i).getFirst()))
                fail(expected.get(i).getFirst() + " is not equal to " + result.get(i).getFirst());
            
            User a = expected.get(i).getSecond();
            User b = result.get(i).getSecond();
            
            if (a.getId() != b.getId())
                fail(a + " is not equal to " + b);
        
            if (!a.getName().equals(b.getName()))
                fail(a + " is not equal to " + b);

            if (!a.getEmail().equals(b.getEmail()))
                fail(a + " is not equal to " + b);

            if (a.getGeneralRoleId() != b.getGeneralRoleId())
                fail(a + " is not equal to " + b);
        }
    }
    
    @Test
    public void getUserRolesInProjectTestIntInt()
    {
        ArrayList<ProjectRole> expected = new ArrayList<>();
        expected.add(ProjectRole.COLLABORATOR);
 
        List<ProjectRole> result = ((ParticipationDAO)dao).getUserRolesInProject(11, 101);
        
        if (expected.size() != result.size())
            fail("The number of roles is different");
        
        for (ProjectRole r : expected)
        {
            if (!result.contains(r))
                fail("Role " + r + " was not contained in the result");
        }
    }
    
    @Test
    public void getUserRolesInProjectTestUserProject()
    {
        ArrayList<ProjectRole> expected = new ArrayList<>();
        expected.add(ProjectRole.COLLABORATOR);
 
        List<ProjectRole> result = ((ParticipationDAO)dao).getUserRolesInProject(new User(11), new Project(101));
        
        if (expected.size() != result.size())
            fail("The number of roles is different");
        
        for (ProjectRole r : expected)
        {
            if (!result.contains(r))
                fail("Role " + r + " was not contained in the result");
        }
    }
    
    @Test
    public void isUserAdminInProjectTestIntInt()
    {
        ParticipationDAO a = (ParticipationDAO) dao;
        
        if (!a.isUserAdminInProject(12, 100))
            fail("User id 12 should be admin for project id 100");
        
        if (a.isUserAdminInProject(11, 100))
            fail("User id 11 should NOT be admin for project id 100");
        
    }
    
    @Test
    public void isUserAdminInProjectTestUserProject()
    {
        ParticipationDAO a = (ParticipationDAO) dao;
        
        if (!a.isUserAdminInProject(new User(12), new Project(100)))
            fail("User id 12 should be admin for project id 100");
        
        if (a.isUserAdminInProject(new User(11), new Project(100)))
            fail("User id 11 should NOT be admin for project id 100");
        
    }
    
    @Override
    protected boolean testEquals(Participation a, Participation b) 
    {
        if (a.getId() != b.getId())
            return false;
        
        if (a.getProjectId() != b.getProjectId())
            return false;
        
        if (a.getUserId() != b.getUserId())
            return false;
        
        if (a.getRole() != b.getRole())
            return false;
        
        return true;
    }
    
}
