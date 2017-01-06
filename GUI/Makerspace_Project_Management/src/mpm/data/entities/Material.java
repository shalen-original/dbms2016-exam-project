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
 * Represents a single Material available for purchase in the Makerspace.
 * Note: unitaryPrice has a scale of 2, that is, only two decimal digits are preserved
 * @author Matteo Nardini
 */
public class Material implements IUniquelyIdentifiable{

    private int id;
    private String name;
    private String description;
    private BigDecimal unitaryPrice;
    private String unitsOfMeasure;
    private int unitsAvailable;

    public Material(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnitaryPrice() {
        return unitaryPrice;
    }

    public void setUnitaryPrice(BigDecimal unitaryPrice) {
        
        if (unitaryPrice.compareTo(BigDecimal.ZERO) < 0)
        {
            throw new IllegalArgumentException("The unitaryPrice cannot be lower than zero. " 
                    + "Given unitaryPrice: " + unitaryPrice);
        }
        
        this.unitaryPrice = unitaryPrice.setScale(2);
    }

    public String getUnitsOfMeasure() {
        return unitsOfMeasure;
    }

    public void setUnitsOfMeasure(String unitsOfMeasure) {
        this.unitsOfMeasure = unitsOfMeasure;
    }

    public int getUnitsAvailable() {
        return unitsAvailable;
    }

    public void setUnitsAvailable(int unitsAvailable) {
        
        if (unitsAvailable < 0)
        {
            throw new IllegalArgumentException("The unitaryPrice cannot be lower than zero. " 
                    + "Given unitaryPrice: " + unitsAvailable);
        }
        
        this.unitsAvailable = unitsAvailable;
    }
    
    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        
        b.append("Material[");
        b.append(id);
        b.append(",");
        b.append(name);
        b.append(",");
        b.append(description);
        b.append(",");
        b.append(unitaryPrice);
        b.append(",");
        b.append(unitsOfMeasure);
        b.append(",");
        b.append(unitsAvailable);
        b.append("]");
        
        return b.toString();
        
    }
}