/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import mpm.data.entities.Message;
import mpm.data.entities.Request;
import mpm.data.logic.DBUtils;
import mpm.data.logic.GenericDataAccessObject;
import mpm.data.logic.IPreparedStatementFiller;
import mpm.data.logic.ISQLResultParser;

/**
 * Implements a DAO for the Request table.
 * @author Matteo Nardini
 */
public class RequestDAO extends GenericDataAccessObject<Request> {

    private String insertIntoQuery;
    private ISQLResultParser<RequestWIDResolvedToStrings> deepParser;
    
    public RequestDAO()
    {
        super("request", "request_id");
        
        insertIntoQuery = "INSERT INTO request(request_id, title, " + 
                            "project_id, technical_inf_id, handled_by_user) " + 
                            "VALUES (?, ?, ?, ?, ?)";
        
        deepParser = r -> {
            
            RequestWIDResolvedToStrings ans = 
                    new RequestWIDResolvedToStrings(parseSQLResult(r));
            
            ans.setProjectName(r.getString("project_title"));
            ans.setTechInfName(r.getString("tech_inf_name"));
            ans.setAssignedToName(r.getString("user_name"));
            
            return ans;
        };
    }
    
    @Override
    public void update(Request objToWrite) 
    {
        String sql = "UPDATE request SET title = ?, " +
                    "project_id = ?, technical_inf_id = ?, " +
                    "handled_by_user = ? WHERE request_id = ?";

        DBUtils.performUID(sql, s -> {
            s.setString(1, objToWrite.getTitle());
            s.setInt(2, objToWrite.getProjectId());
            s.setInt(3, objToWrite.getTechInfId());
            s.setInt(4, objToWrite.getAssignedUserId());
            s.setInt(5, objToWrite.getId());
        });
    }

    @Override
    public void insert(Request objToInsert) 
    {
        DBUtils.performUID(insertIntoQuery, s -> {
            s.setInt(1, objToInsert.getId());
            s.setString(2, objToInsert.getTitle());
            s.setInt(3, objToInsert.getProjectId());
            s.setInt(4, objToInsert.getTechInfId());
            s.setInt(5, objToInsert.getAssignedUserId());   
        });
    }
    
    public List<Request> findByProjectID(int projectID){
        
        String sql = "SELECT * FROM request " +
                    "WHERE project_id = ?";
        
        IPreparedStatementFiller f = s -> {s.setInt(1, projectID);};

        return DBUtils.performSelect(sql, f, this.defaultParser);
    }

    public void createRequestWithMessage(Request r, String messageText, int authorId)
    {
        Message m = new Message(DAOs.messages.getNextValidId());
        m.setText(messageText);
        m.setRequestId(r.getId());
        m.setCreationTime(new Timestamp(System.currentTimeMillis()));
        m.setAuthorId(authorId);
        
        DBUtils.beginTransaction();
        DBUtils.performOperation(conn -> {
            PreparedStatement s = conn.prepareStatement(insertIntoQuery);
            s.setInt(1, r.getId());
            s.setString(2, r.getTitle());
            s.setInt(3, r.getProjectId());
            s.setInt(4, r.getTechInfId());
            s.setNull(5, Types.INTEGER); 
            s.executeUpdate();
        });
        DAOs.messages.insert(m);
        DBUtils.endTranstaction();
    }
    
    public List<RequestWIDResolvedToStrings> deepGetAll()
    {
        String sql = "SELECT r.request_id, r.title, r.project_id, r.technical_inf_id, " +
                            "r.handled_by_user, p.title AS project_title, t.name AS tech_inf_name, " +
                            "u.name AS user_name " + 
                    "FROM request r INNER JOIN project p ON (r.project_id = p.project_id) " + 
                    "INNER JOIN technical_inf t ON (r.technical_inf_id = t.technical_inf_id) " +
                    "INNER JOIN makerspace_user u ON (u.user_id = r.handled_by_user)";
        
        return DBUtils.performSelect(sql, this.deepParser);

    }
    
    public List<RequestWIDResolvedToStrings> deepGetUnassignedRequests()
    {
        String sql = "SELECT r.request_id, r.title, r.project_id, r.technical_inf_id, " +
                            "r.handled_by_user, p.title AS project_title, t.name AS tech_inf_name " +
                    "FROM request r INNER JOIN project p ON (r.project_id = p.project_id) " + 
                    "INNER JOIN technical_inf t ON (r.technical_inf_id = t.technical_inf_id) " +
                    "WHERE r.handled_by_user IS NULL";
        
        ISQLResultParser<RequestWIDResolvedToStrings> p = r -> {
            
            RequestWIDResolvedToStrings ans = 
                    new RequestWIDResolvedToStrings(parseSQLResult(r));
            
            ans.setProjectName(r.getString("project_title"));
            ans.setTechInfName(r.getString("tech_inf_name"));
            
            return ans;
        };
        
        return DBUtils.performSelect(sql, p);
    }
    
    public List<RequestWIDResolvedToStrings> deepFindByAssignedUserId(int userID)
    {
        String sql = "SELECT r.request_id, r.title, r.project_id, r.technical_inf_id, " +
                            "r.handled_by_user, p.title AS project_title, t.name AS tech_inf_name, " +
                            "u.name AS user_name " + 
                    "FROM request r INNER JOIN project p ON (r.project_id = p.project_id) " + 
                    "INNER JOIN technical_inf t ON (r.technical_inf_id = t.technical_inf_id) " +
                    "INNER JOIN makerspace_user u ON (u.user_id = r.handled_by_user) "
                    + "WHERE r.handled_by_user = ?";
        
        IPreparedStatementFiller f = s -> {
            s.setInt(1, userID);
        };
        
        return DBUtils.performSelect(sql, f, this.deepParser);
    }
    
    @Override
    public Request parseSQLResult(ResultSet r) throws SQLException 
    {
        Request p = new Request(r.getInt("request_id"));
        p.setTitle(r.getString("title"));
        p.setProjectId(r.getInt("project_id"));
        p.setTechInfId(r.getInt("technical_inf_id"));
        
        p.setAssignedUserId(r.getInt("handled_by_user"));
        if (r.wasNull())
            p.setAssignedUserId(null);
        
        return p;
    }
    
    public class RequestWIDResolvedToStrings
    {
        private String projectName;
        private String techInfName;
        private String assignedToName;
        private Request request;

        public RequestWIDResolvedToStrings(Request request) {
            this.request = request;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getTechInfName() {
            return techInfName;
        }

        public void setTechInfName(String techInfName) {
            this.techInfName = techInfName;
        }

        public String getAssignedToName() {
            return assignedToName;
        }

        public void setAssignedToName(String assignedToName) {
            this.assignedToName = assignedToName;
        }

        public Request getRequest() {
            return request;
        }

        public void setRequest(Request request) {
            this.request = request;
        }
        
    }

}