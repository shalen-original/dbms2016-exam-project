/*
 * Makerspace Project Management
 * Database Systems 2016-2017
 * Copyright 2016 (C) Grabocka Mikel, Nardini Matteo, Scolati Remo.
 * All rights reserved.
 */

package mpm.data.logic;

/**
 * Represents a generic pair
 * @author Matteo Nardini
 * @param <K> The type of the first element of the pair
 * @param <V> The type of the second element of the pair.
 */
public class Pair<K,V> {

    K first;
    V second;
    
    public Pair(K first, V second)
    {
        this.first = first;
        this.second = second;
    }
    
    public K getFirst() {
        return first;
    }

    public void setFirst(K first) {
        this.first = first;
    }

    public V getSecond() {
        return second;
    }

    public void setSecond(V second) {
        this.second = second;
    }
    
}