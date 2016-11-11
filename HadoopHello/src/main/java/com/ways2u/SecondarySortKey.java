package com.ways2u;

import scala.math.Ordered;

import java.io.Serializable;

public class SecondarySortKey implements Ordered<SecondarySortKey>,Serializable {
    public String key;
    public Integer count;


    @Override
    public boolean $greater(SecondarySortKey arg0) {
        if(this.count>arg0.count)
            return true;
        return false;
    }


    @Override
    public boolean $greater$eq(SecondarySortKey arg0) {
        if(this.count>=arg0.count)
            return true;
        return false;
    }


    @Override
    public boolean $less(SecondarySortKey arg0) {
        if(this.count<arg0.count)
            return true;
        return false;
    }


    @Override
    public boolean $less$eq(SecondarySortKey arg0) {
        if(this.count<=arg0.count)
            return true;
        return false;
    }


    @Override
    public int compare(SecondarySortKey arg0) {
        return count.compareTo(arg0.count);
    }


    @Override
    public int compareTo(SecondarySortKey arg0) {
        return count.compareTo(arg0.count);
    }


    public int hashCode() {
        return key.hashCode();
    }

    public boolean equals(Object obj)
    {
        return key.equals(((SecondarySortKey)obj).key);
    }
}