package main.java;

import java.util.*;

// This class provides functionality for a BearWorkshop class.
public class BearWorkshop implements BearWorkshopInterface {
	// Workshop has a collection of bears
	// Workshop has a customer
	Customer customer;
	List<Bear> BearCart;

	/**
	 * Default constructor for the Bear Workshop
	 */
	public BearWorkshop() {
		this("AZ");
	}

	/**
	 * This is a parameterized ctor for a BearWorkshop
	 * 
	 * @param state customer is in
	 */
	public BearWorkshop(String state) {
		BearCart = new LinkedList<>();
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
		Collections.sort(bear.clothing);
		int numFree = bear.clothing.size() / 3;
		ArrayList<Clothing> freeClothes = new ArrayList<>();

		for (int i = 0; i < bear.clothing.size(); i++) {
			Clothing clothes = bear.clothing.get(i);
			if (i < numFree) {
				freeClothes.add(clothes);
			} else {
				bear.price += clothes.price;
			}
		}

		for (NoiseMaker noise : bear.noisemakers) {
			bear.price += noise.price;
		}

		if (bear.ink != null) {
			bear.price += bear.ink.price;
		}

		bear.price += bear.stuff.price;
		bear.price += bear.casing.priceModifier;

		return bear.price;
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
		if (this.BearCart.add(bear)) {
			return true;
		} else {
			return false;
		}
	}

	// Simple means to remove a bear from the shopping cart
	@Override
	public boolean removeBear(Bear bear) {
		if (this.BearCart.remove(bear)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This is a function to allow customers to checkout their shopping cart It
	 * should return the total cost of they purchase. 
	 * 
	 * Hannah's fix: This method duplicated too many of the steps from getCost() and getRawCost()
	 * So I instead fixed it to simply call getCost()
	 * 
	 * @return
	 */
	@Override
	public double checkout() {
		if (this.customer.age <= 13) {
			if (this.customer.parent.age < 18)
				System.out.println("Guardian is too young");
			return -1;
		}

		double cost = 0;
		for (Bear bear : this.BearCart) {
			cost = cost + getCost(bear);
		}

		return cost * calculateTax();
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
		double[] savingsArray = new double[BearCart.size()];
		
		// for each bear in the cart
		for (int i = 0; i < BearCart.size(); i++) {
			
			double individualSavings = 0;
			
			// if bear costs more than $70 the embroidery is free
			if (getRawCost(BearCart.get(i)) > 70.00) {
				individualSavings = individualSavings + BearCart.get(i).ink.price;
			}

			// for each two pieces of clothing, the third and cheapest one is free
			if (BearCart.get(i).clothing.size() > 2) {
				// how many free clothes
				int expectedFreeClothes = BearCart.get(i).clothing.size() / 3;

				for (int j = 0; j < expectedFreeClothes; j++) {
					// getting lowest prices of clothes
					double lowestValue = 100.00;
					int lowestIndex = 0;
					for (int k = 0; k < BearCart.get(i).clothing.size(); k++) {
						if (BearCart.get(i).clothing.get(k).price < lowestValue) {
							lowestValue = BearCart.get(i).clothing.get(k).price;
							lowestIndex = k;
						}
					}
					individualSavings = individualSavings + lowestValue;
					BearCart.get(i).clothing.remove(lowestIndex);
				}
			}

			// if a bear has ten or more accessories the bear is 10% off
			if ((BearCart.get(i).clothing.size() + BearCart.get(i).noisemakers.size()) >= 10) {
				individualSavings = individualSavings + (.1 * getRawCost(BearCart.get(i)));
			}
			// tallying everything up
			// general savings
			savings = savings + individualSavings;
			// cost of bear after savings have been applied
			savingsArray[i] = getRawCost(BearCart.get(i)) - individualSavings;
		}

		// Buy two bears get a cheaper one free
		if (BearCart.size() > 2) {
			int expectedFreeBears = BearCart.size() / 3;
			double costOfFreeBears = 0;
			for (int i = 0; i < expectedFreeBears; i++) {
				int lowestIndex = 0;
				double lowestPrice = savingsArray[0];
				for (int j = 0; j < savingsArray.length; j++) {
					if (lowestPrice > savingsArray[j]) {
						lowestIndex = j;
						lowestPrice = savingsArray[j];
					}
				}
				costOfFreeBears = costOfFreeBears + lowestPrice;
				// kind of hacky but i need to make sure not to count the lowest price twice
				savingsArray[lowestIndex] = 1000.00;
			}
			savings = savings + costOfFreeBears;
		}

		return savings;
	}

}