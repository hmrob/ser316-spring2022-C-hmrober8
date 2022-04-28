package main.java;

public class Stuffing {
    public enum StuffingType {
        BASE, DOWN, FOAM
    }
    int price;

/**
* Simple constructor for the different types and prices of StuffingType
*/

//SER316 TASK 2 SPOT-BUGS FIX’ 
    public Stuffing(StuffingType interiorStuffing) {

        switch (interiorStuffing) {
            case BASE:
                this.price = 30;
                break;
            case DOWN:
                this.price = 40;
                break;
            case FOAM:
                this.price = 50;
                break;
        }
    }
}
