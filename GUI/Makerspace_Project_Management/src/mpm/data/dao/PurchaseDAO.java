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
import java.util.List;
import mpm.data.entities.Purchase;
import mpm.data.logic.DBUtils;
import mpm.data.logic.GenericDataAccessObject;
import mpm.data.logic.IPreparedStatementFiller;

/**
 * Implements a DAO for the Purchase table.
 * @author Matteo Nardini
 */
public class PurchaseDAO extends GenericDataAccessObject<Purchase>{
    
    private String insertQuery;
    
    public PurchaseDAO()
    {
        super("purchase", "purchase_id");
        
        insertQuery = "INSERT INTO purchase(purchase_id, project_id, " + 
                    "material_id, units, total_price) " + 
                    "VALUES (?, ?, ?, ?, ?)";
    }
    
    @Override
    public void update(Purchase objToWrite) 
    {
        String sql = "UPDATE purchase SET project_id = ?, " +
                    "material_id = ?, units = ?, " +
                    "total_price = ? WHERE purchase_id = ?";
        
        DBUtils.performUID(sql, s -> {
            s.setInt(1, objToWrite.getProjectId());
            s.setInt(2, objToWrite.getMaterialId());
            s.setInt(3, objToWrite.getUnits());
            s.setBigDecimal(4, objToWrite.getTotalPrice());
            s.setInt(5, objToWrite.getId());
        });
    }

    @Override
    public void insert(Purchase objToInsert) 
    {
        DBUtils.performUID(insertQuery, s -> {
            s.setInt(1, objToInsert.getId());
            s.setInt(2, objToInsert.getProjectId());
            s.setInt(3, objToInsert.getMaterialId());
            s.setInt(4, objToInsert.getUnits());
            s.setBigDecimal(5, objToInsert.getTotalPrice());   
        });
    }
    
    public void insertAndUpdateMaterial(Purchase p)
    {
        DBUtils.beginTransaction();
        this.insert(p);
        DBUtils.performOperation(conn -> {
            String sql = "SELECT units_available FROM material "
                        + "WHERE material_id = ?";
            
            PreparedStatement s = conn.prepareStatement(sql);
            s.setInt(1, p.getMaterialId());
            ResultSet r = s.executeQuery();
            r.next();
            int currUnits = r.getInt("units_available");

            sql = "UPDATE material SET units_available = ? "
                    + "WHERE material_id = ?";
            s = conn.prepareStatement(sql);
            s.setInt(1, currUnits - p.getUnits());
            s.setInt(2, p.getMaterialId());
            s.executeUpdate();
        });
        DBUtils.endTranstaction();
    }
    
    public List<Purchase> findByProjectID(int projectID){
        
        String sql = "SELECT * FROM purchase " +
                    "WHERE project_id = ?";
        
        IPreparedStatementFiller f = s -> {s.setInt(1, projectID);};

        return DBUtils.performSelect(sql, f, this.defaultParser);
    }

    @Override
    public Purchase parseSQLResult(ResultSet r) throws SQLException 
    {
        Purchase p = new Purchase(r.getInt("purchase_id"));
        p.setProjectId(r.getInt("project_id"));
        p.setMaterialId(r.getInt("material_id"));
        p.setUnits(r.getInt("units"));
        p.setTotalPrice(r.getBigDecimal("total_price"));
        
        return p;
    }

}