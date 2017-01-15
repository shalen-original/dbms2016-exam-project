/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import mpm.data.entities.Material;
import mpm.data.logic.DBUtils;
import mpm.data.logic.GenericDataAccessObject;

/**
 * Implements a DAO for the Material table.
 * @author Matteo Nardini
 */
public class MaterialDAO extends GenericDataAccessObject<Material>{

    public MaterialDAO()
    {
        super("material" , "material_id");
    }

    @Override
    public void update(Material objToWrite) 
    {
        String sql = "UPDATE material SET name = ?, " +
                            "description = ?, unitary_price = ?, " +
                            "units_of_measure = ?, units_available = ? WHERE material_id = ?";
        
        DBUtils.performUID(sql, s -> {
            s.setString(1, objToWrite.getName());
            s.setString(2, objToWrite.getDescription());
            s.setBigDecimal(3, objToWrite.getUnitaryPrice());
            s.setString(4, objToWrite.getUnitsOfMeasure());
            s.setInt(5, objToWrite.getUnitsAvailable());
            s.setInt(6, objToWrite.getId());
        });
    }

    @Override
    public void insert(Material objToInsert) 
    {
        String sql = "INSERT INTO material(material_id, name, " + 
                    "description, unitary_price, units_of_measure, units_available) " + 
                    "VALUES (?, ?, ?, ?, ?, ?)";

        DBUtils.performUID(sql, s -> {
            s.setInt(1, objToInsert.getId());
            s.setString(2, objToInsert.getName());
            s.setString(3, objToInsert.getDescription());
            s.setBigDecimal(4, objToInsert.getUnitaryPrice());
            s.setString(5, objToInsert.getUnitsOfMeasure());  
            s.setInt(6, objToInsert.getUnitsAvailable());
        });
    }

    @Override
    public Material parseSQLResult(ResultSet r) throws SQLException 
    {
        Material p = new Material(r.getInt("material_id"));
        p.setName(r.getString("name"));
        p.setDescription(r.getString("description"));
        p.setUnitaryPrice(r.getBigDecimal("unitary_price"));
        p.setUnitsOfMeasure(r.getString("units_of_measure"));
        p.setUnitsAvailable(r.getInt("units_available"));
        
        return p; 
    }
    
    
}