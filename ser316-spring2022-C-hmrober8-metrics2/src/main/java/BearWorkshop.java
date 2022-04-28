package main.java;

import java.util.*;

// This class provides functionality for a BearWorkshop class.
public class BearWorkshop implements BearWorkshopInterface {
    // Workshop has a collection of bears
    // Workshop has a customer
    Customer customer;
    List<Bear> bearCart;
	final int BUY_CLOTHES_GET_FREE = 3;
	final int BUY_BEAR_GET_FREE = 2;
	final int TEN_PERCENT_ACCESSORIES = 10;
	final double FREE_EMBROIDERY = 70.00;
	
	
	
    /**
     * Default constructor for the Bear Workshop.
     */
    public BearWorkshop() {
        this("AZ");
    }

    /**
     * This is a parameterized ctor for a BearWorkshop.
     * 
     * @param state customer is in
     */
    public BearWorkshop(String state) {
        bearCart = new LinkedList<>();
        customer = new Customer(state);
    }

    /**
     * This is a convenience method to calculate the cost of one bears in the
     * shopping cart for a customer in the BearFactory. It should take the accessory
     * discounts into account correctly.
     * 
     * @param bear to get cost of
     * @return double representation of bear cost 
     */
    @Override
    public double getCost(Bear bear) {
    	double price = 0;
        
        // for each two pieces of clothing, the third and cheapest one is free
        if (bear.clothing.size() > BUY_CLOTHES_GET_FREE - 1) {
            // how many free clothes
            int expectedFreeClothes = bear.clothing.size() / BUY_CLOTHES_GET_FREE;

            for (int j = 0; j < expectedFreeClothes; j++) {
                // getting lowest prices of clothes
                int lowest = -1;
                double lowestValue = 200.00;
                for (int k = 0; k < bear.clothing.size(); k++) {
                    if (bear.clothing.get(k).price < lowestValue) {
                        lowest = k;
                        lowestValue = bear.clothing.get(k).price;
                    }
                }
                bear.clothing.remove(lowest);
            }
        }
        
        // tally up the clothing price
        for (int i = 0; i < bear.clothing.size(); i++) {
        	price = price + bear.clothing.get(i).price;
        }
    	
        for (NoiseMaker noise : bear.noisemakers) {
            price += noise.price;
        }

        price += bear.stuff.price;
        price += bear.casing.priceModifier;
        
     // if bear costs more than $70 the embroidery is free
        if (price < FREE_EMBROIDERY && bear.ink != null) {
            price = price + bear.ink.price;
        }
        
        // if a bear has ten or more accessories the bear is 10% off
        if ((bear.clothing.size() + bear.noisemakers.size()) >= TEN_PERCENT_ACCESSORIES) {
            price = price * .9;
        }

        return price;
    }
    
    // Function to get the raw cost of a bear without any discounts
    public double getRawCost(Bear bear) {
        double bearPrice = 0;
        for (Clothing clothes : bear.clothing) {
            bearPrice += clothes.price;
        }

        for (NoiseMaker noise : bear.noisemakers) {
            bearPrice += noise.price;
        }

        if (bear.ink != null) {
            bearPrice += bear.ink.price;
        }

        bearPrice += bear.stuff.price;
        bearPrice += bear.casing.priceModifier;
        
        bear.price = bearPrice;

        return bearPrice;
    }

    /**
     * Utility method to calculate tax for purchases by customers in different
     * states. You can assume these taxes are what we want, so they are not wrong.
     * 
     * @return
     */
    public double calculateTax() {
        double tax;
        switch (customer.state) {
            case "AZ":
                tax = 1.07;
                break;
            case "NY":
                tax = 1.09;
                break;
            case "VA":
                tax = 1.05;
                break;
            case "DC":
                tax = 1.105;
                break;
            case "CA":
                tax = 1.1;
                break;
            default:
                tax = 1.05;
                break;
            }
        return tax;
    }

    /**
     * Utility method to add a bear to the BearFactory - so ti the shopping cart.
     * 
     * @param bear to add
     * @return true if successful, false otherwise TODO: test me and fix me in
     *         assignment 3
     */
    @Override
    public boolean addBear(Bear bear) {
        if (this.bearCart.add(bear)) {
            return true;
        } else {
            return false;
        }
    }

    // Simple means to remove a bear from the shopping cart
    @Override
    public boolean removeBear(Bear bear) {
        if (this.bearCart.remove(bear)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This is a function to allow customers to checkout their shopping cart It
     * should return the total cost of they purchase. 
     * Hannah's fix: This method duplicated too many of the steps from getCost() and getRawCost()
     * So I instead fixed it to simply call getCost()
     * 
     * @return
     */
    @Override
    public double checkout() {
        if (this.customer.age <= 13) {
            if (this.customer.parent.age < 18) {
                System.out.println("Guardian is too young");
            }
            return -1;
        }

        double cost = 0;
        for (Bear bear : this.bearCart) {
            cost = cost + getRawCost(bear);
        }
        
        cost = cost - calculateSavings();

        return cost * calculateTax();
    }

    /**
     * age setter for customer and customer parent for testing purposes.
     */
    public void setAge() {
        this.customer.age = 12;
        //this.customer.parent.age = 16;
    }
    
    /**
     * This method returns the savings for advertised bundle savings. Specifically,
     * - Bears are Buy 2 bears, get a third one free. It is always the cheapest bear
     * that is free. The price here is meant when all discounts for a single bear
     * are applied - It is 10% off the cost of a bear when a single bear has 10 or
     * more accessories (anything on a bear is an accessory) that the customer pays
     * for (so if clothes are free these do not count). - Clothes are buy 2, get one
     * free on each bear. Always the cheapest clothes are free. So if a bear has 6
     * clothes, then the two cheapest ones will be free and it would count as 4
     * accessories (see above). - Inking on a specific bear is free if and only if
     * the bear without discounts applied to it costs more than $70. TIP: the
     * implemented savings method in the BearWorkshop1-5 do not use the getCost
     * method implemented in this base class. They implement their own savings
     * calculation All of them do however use the getRawCost method implemented in
     * other two have 4 clothing items. Non of them have embroidery or noise makers
     * and they have the same stuffing. Now, on each bear one clothing item will be
     * free, since buy 2 get 1 free on a bear. So for costs we have the bear with
     * stuffing. For one we pay only 2 clothing items, for 2 we still pay for 3
     * clothing items. Since all clothing is the same priece the bear with only 2
     * paid clothing items is cheapest. So we will get that bear for free. We will
     * only have to pay for 2 bears, with 3 clothing items each.
     * 
     * @return the savings if the customer would check with the current bears in the
     *         workshop out as double
     */
    public double calculateSavings() {
        double savings = 0.00;
        
        // for each bear in the cart
        for (int i = 0; i < bearCart.size(); i++) {
            savings = savings + (getRawCost(bearCart.get(i)) - getCost(bearCart.get(i)));
        }
        
        
        // Buy two bears get a cheaper one free
        if (bearCart.size() > BUY_BEAR_GET_FREE) {
            int expectedFreeBears = bearCart.size() / 3;
            double costOfFreeBears = 0;
            for (int i = 0; i < expectedFreeBears; i++) {
                double lowestPrice = 100000.0;
                for (int j = 0; j < bearCart.size(); j++) {
                    if (lowestPrice > getCost(bearCart.get(j))) {
                        lowestPrice = getCost(bearCart.get(j));
                    }
                }
                costOfFreeBears = costOfFreeBears + lowestPrice;
                // kind of hacky but i need to make sure not to count the lowest price twice
            }
            savings = savings + costOfFreeBears;
        }
        return savings;
    }
}