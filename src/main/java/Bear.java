package main.java;

import main.java.Stuffing.StuffingType;
import java.util.LinkedList;

public class Bear implements Comparable<Bear> {
    public Casing casing;
    public Stuffing stuff;
    public Embroidery ink;
    public LinkedList<NoiseMaker> noisemakers; // accessory
    public LinkedList<Clothing> clothing; // accessory
    public double price;
    // bear has a shell (requ)
    // bear has StuffingType (req)
    // bear has a tattoo/emroider or not (opt)
    // bear has a noisemaker (opt)

/**
* Default constructor for bear
*/
    public Bear() {
        this.casing = new Casing();
        this.stuff = new Stuffing(StuffingType.BASE);
        noisemakers = new LinkedList<>();
        clothing = new LinkedList<>();
        ink = new Embroidery("");
        price = 0;
    }

/**
* constructor with stuffing paramter
*/
    public Bear(StuffingType stuff) {
        this.casing = new Casing();
        this.stuff = new Stuffing(stuff);
        noisemakers = new LinkedList<>();
        clothing = new LinkedList<>();
        ink = new Embroidery("");
        price = 0;
    }

    public void setPrice(double incomingPrice) {
        this.price = incomingPrice;
    }

/**
* Adds noisemaker object
*/
    public boolean addNoise(NoiseMaker noise) {
        if (this.noisemakers.size() >= 5) {
            return false;
        } else {
            for (NoiseMaker noisemaker : noisemakers) {
                if (noise.spot == noisemaker.spot) {
                    return false;
                }
            }
            noisemakers.add(noise);
            return true;
        }
    }
/**
* Implements compare method and overrides object.compareTo
*/
    @Override
    //SER316 TASK 2 SPOT-BUGS FIX’
    public int compareTo(Bear bear) {
         return Double.compare(bear.price, this.price);
    }

/**
*overrides object.equals for sorting purposes
*/
    //SER316 TASK 2 SPOT-BUGS FIX’    
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        
        if (!(obj instanceof Bear)) {
            return false;
        }
        
        Bear bear = (Bear) obj;
        
        return (Double.compare(bear.price, this.price) == 0) 
        && 
        (this.stuff) == (bear.stuff) 
        && 
        (this.ink.embroidText).equals(bear.ink.embroidText);
    }
    //SER316 TASK 2 SPOT-BUGS FIX
    public int hashCode() {
        assert false : "hashCode not designed";
        return 42; // any arbitrary constant will do
    }
}