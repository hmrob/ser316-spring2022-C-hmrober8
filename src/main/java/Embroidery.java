package main.java;

// You can assume the price of $1 per letter is correct

public class Embroidery {
    static double pricePerLetter = 1.00;
    double price;

    public Embroidery(String embroidery) {
        this.price = embroidery.length() * pricePerLetter;
    }
}
