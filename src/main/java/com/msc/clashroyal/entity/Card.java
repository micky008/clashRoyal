package com.msc.clashroyal.entity;

/**
 *
 * @author Michael
 */
public class Card {

    public String name;
    public Integer maxLevel;
    public String rarity;
    public int id;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        return this.id == other.id;
    }
    
    
    
}
