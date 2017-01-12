/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import mpm.data.entities.Request;
import mpm.data.logic.DBUtils;
import mpm.data.logic.GenericDataAccessObject;
import mpm.data.logic.IPreparedStatementFiller;

/**
 * Implements a DAO for the Request table.
 * @author Matteo Nardini
 */
public class RequestDAO extends GenericDataAccessObject<Request> {

    public RequestDAO()
    {
        super("request", "request_id");
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
        String sql = "INSERT INTO request(request_id, title, " + 
                            "project_id, technical_inf_id, handled_by_user) " + 
                            "VALUES (?, ?, ?, ?, ?)";

        DBUtils.performUID(sql, s -> {
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

    @Override
    protected Request parseSQLResult(ResultSet r) throws SQLException 
    {
        Request p = new Request(r.getInt("request_id"));
        p.setTitle(r.getString("title"));
        p.setProjectId(r.getInt("project_id"));
        p.setTechInfId(r.getInt("technical_inf_id"));
        p.setAssignedUserId(r.getInt("handled_by_user"));
        
        return p;
    }

}