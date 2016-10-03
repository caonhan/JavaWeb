package com.serp.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LimitInventoryEntity_Dat implements Serializable{
    private Integer limitInventoryId;

    public Integer getLimitInventoryId() {
        return limitInventoryId;
    }

    public void setLimitInventoryId(Integer limitInventoryId) {
        this.limitInventoryId = limitInventoryId;
    }

    public LimitInventoryEntity_Dat() {
        
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((limitInventoryId == null) ? 0 : limitInventoryId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LimitInventoryEntity_Dat other = (LimitInventoryEntity_Dat) obj;
        if (limitInventoryId == null) {
            if (other.limitInventoryId != null)
                return false;
        } else if (!limitInventoryId.equals(other.limitInventoryId))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "LimitInventoryEntity_Dat [limitInventoryId=" + limitInventoryId + "]";
    }
    
    
}
