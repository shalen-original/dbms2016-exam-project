/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */
package mpm.data.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import mpm.data.entities.Material;

/**
 * Tests for the MaterialDAO.java class.
 * @author Matteo Nardini
 */
public class MaterialDAOTest extends GenericDAOTestHelper<Material>{
    
    public MaterialDAOTest() 
    {   
        this.dao = new MaterialDAO();
      
        // Filling initial list
        this.fullTableList = new ArrayList<>();
        Material p;
        
        p = new Material(200);
        p.setName("Paper");
        p.setDescription("");
        p.setUnitaryPrice(BigDecimal.valueOf(0.02));
        p.setUnitsOfMeasure("Sheet");
        p.setUnitsAvailable(1000);
        this.fullTableList.add(p);
        
        p = new Material(201);
        p.setName("Red Raw Plastic");
        p.setDescription("Input for 3D printer");
        p.setUnitaryPrice(BigDecimal.valueOf(3.00));
        p.setUnitsOfMeasure("m");
        p.setUnitsAvailable(2000);
        this.fullTableList.add(p);
        
        p = new Material(203);
        p.setName("Blue Raw Plastic");
        p.setDescription("Input for 3D printer");
        p.setUnitaryPrice(BigDecimal.valueOf(3.00));
        p.setUnitsOfMeasure("m");
        p.setUnitsAvailable(1650);
        this.fullTableList.add(p);
        
        p = new Material(205);
        p.setName("Green Raw Plastic");
        p.setDescription("Input for 3D printer");
        p.setUnitaryPrice(BigDecimal.valueOf(3.00));
        p.setUnitsOfMeasure("m");
        p.setUnitsAvailable(905);
        this.fullTableList.add(p);
        
         p = new Material(206);
        p.setName("Dependecies free");
        p.setDescription("Material never bought by any project. For test purposes");
        p.setUnitaryPrice(BigDecimal.valueOf(1.2));
        p.setUnitsOfMeasure("test");
        p.setUnitsAvailable(10);
        this.fullTableList.add(p);
        
        // Setting up update test
        this.objectToUpdate = new Material(200);
        this.objectToUpdate.setName("NEW NAME");
        this.objectToUpdate.setDescription("NEW DESCRIPTION");
        this.objectToUpdate.setUnitaryPrice(BigDecimal.valueOf(15.18));
        this.objectToUpdate.setUnitsOfMeasure("NEW UNIT OF MEASURE");
        this.objectToUpdate.setUnitsAvailable(69516);
        
        this.objectToRevertUpdate = this.fullTableList.get(0);
        
        // Setting up insert test
        this.objectToInsert = new Material(199);
        this.objectToInsert.setName("INSERTED NAME");
        this.objectToInsert.setDescription("INSERTED DESCRIPTION");
        this.objectToInsert.setUnitaryPrice(BigDecimal.valueOf(14.10));
        this.objectToInsert.setUnitsOfMeasure("INSERTED UNIT OF MEASURE");
        this.objectToInsert.setUnitsAvailable(1024);
        
        // Setting up delete test
        this.objectToDelete = this.fullTableList.get(4);  
    }
    
    @Override
    protected boolean testEquals(Material a, Material b)
    {
        if (a.getId() != b.getId())
            return false;
        
        if (!a.getName().equals(b.getName()))
            return false;
        
        if (!a.getDescription().equals(b.getDescription()))
            return false;
        
        if (!a.getUnitaryPrice().equals(b.getUnitaryPrice()))
           return false;
        
        if (!a.getUnitsOfMeasure().equals(b.getUnitsOfMeasure()))
            return false;
        
        if (a.getUnitsAvailable() != b.getUnitsAvailable())
            return false;
        
        return true;
    }
}
