package main.java;

import java.lang.Double;

public class Clothing implements Comparable<Clothing> {
    public double price;
    public String description;

    // you can assume that the price of $4 per clothing item is correct
    public Clothing() {
        this(4.00, "Generic Offtrack Separate");

    }

    public Clothing(double price, String descr) {
        this.price = price;
        this.description = descr;
    }
    
    //SER316 TASK 2 SPOT-BUGS FIX’
    public int compareTo(Clothing clothes) {
        return Double.compare(clothes.price, this.price);
    }
        
    //SER316 TASK 2 SPOT-BUGS FIX’
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        
        if (!(obj instanceof Clothing)) {
            return false;
        }
        
        Clothing cloth = (Clothing) obj;
        
        return (Double.compare(cloth.price, this.price) == 0);
    }
    
    public int hashCode() {
        assert false : "hashCode not designed";
        return 42; // any arbitrary constant will do
    }
}
