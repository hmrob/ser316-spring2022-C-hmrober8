package main.java;

public class Casing {
    double priceModifier;

//SER316 TASK 2 SPOT-BUGS FIX’ 
    //String description;

    public Casing() {
        this(1.00);
    }

    public Casing(double price) {
        this.priceModifier = price;
        //this.description = descr;
    }
}
