/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.entities;

import java.math.BigDecimal;
import mpm.data.logic.IUniquelyIdentifiable;

/**
 * Represents a single purchase of a material from the makerspace.
 * Note: totalPrice has a scale of 2, that is, only two decimal digits are preserved
 * @author Matteo Nardini
 */
public class Purchase implements IUniquelyIdentifiable{

    private int id;
    private int projectId;
    private int materialId;
    private int units;
    private BigDecimal totalPrice;

    public Purchase(int id) {
        this.id = id;
    } 
    
    @Override
    public int getId() {
        return id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        
        if (units < 0)
        {
            throw new IllegalArgumentException("The number of units cannot be lower than zero. " 
                    + "Given number of units: " + units);
        }
        
        this.units = units;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        
        if (totalPrice.compareTo(BigDecimal.ZERO) < 0)
        {
            throw new IllegalArgumentException("The totalPrice cannot be lower than zero. " 
                    + "Given totalPrice: " + totalPrice);
        }
        
        this.totalPrice = totalPrice.setScale(2, BigDecimal.ROUND_HALF_DOWN);
    }
    
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        
        b.append("Purchase[");
        b.append(id);
        b.append(", project: ");
        b.append(projectId);
        b.append(", material: ");
        b.append(materialId);
        b.append(",");
        b.append(units);
        b.append(",");
        b.append(totalPrice);
        b.append("]");
        
        return b.toString();
        
    }
    
}