/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mpm.main;

/**
 *
 * @author remo
 */
public class TestObject {
    
    public String title, description;
    public String[] userList;
    
    /*
     * Mock Object to have something to work with, later for every project
     * in the DB (where logged in user is in project user), there will be
     * an object with more arguments
     */
    public TestObject(String title, String description, String[] userList) {
    
        this.title = title;
        this.userList = userList;
        this.description = description;
    }
}
