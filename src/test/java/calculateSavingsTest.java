package test.java;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.java.Bear;
import main.java.BearWorkshop;
import main.java.Clothing;
import main.java.Embroidery;
import main.java.NoiseMaker;
import main.java.Stuffing;
import main.java.NoiseMaker.Location;

public class calculateSavingsTest {

	 BearWorkshop oneBear;
	    Double oneBearExpected;

	    BearWorkshop threeBears;
	    Double threeBearsExpected;

	    BearWorkshop twoBears;
	    Double twoBearsExpected;

	    @Before
	    public void setUp() throws Exception {
	        
	    }

	    @After
	    public void tearDown() throws Exception {
	    }

	    // sample test

	    /**
	     * Test examines a BearFactory with 1 simple bear in it. The bear costs $30
	     * and there are no savings.
	     */
	    @Test
	    public void oneBearNoSavings() {
	    	// One Bear base stuffing, no saving expected
	        
	        BearWorkshop oneBear = null;
	        try {
	        	oneBear = new BearWorkshop("NY");
	        } catch (Exception e) {
	        }
	        
	        oneBear.addBear(new Bear(Stuffing.stuffing.BASE)); // $30 stuffing + $1 casing -- should be no savings at all
	        oneBearExpected = 0.00; // no savings since no clothing
	    	
	        Double ans = oneBear.calculateSavings();
	        assertEquals(oneBearExpected, ans);
	    }
	    
	    //Testing for the clothes
	    @Test
	    public void buy2Get3ClothesDifferentPriceHiFirst() {
	    	// One Bear base stuffing
	        
	        BearWorkshop oneBear = null;
	        try {
	        	oneBear = new BearWorkshop("NY");
	        } catch (Exception e) {
	        }
	        
	        Bear customBear = new Bear(Stuffing.stuffing.BASE);
	        oneBear.addBear(customBear); // $30 stuffing + $1 casing
	        
	        //adds $10 worth of clothing
	        customBear.clothing.add(new Clothing(4, "test1")); 
	        customBear.clothing.add(new Clothing(4, "test2")); 
	        customBear.clothing.add(new Clothing(2, "test3")); 
	        
	        oneBearExpected = 2.00;
	    	Double ans = oneBear.calculateSavings();
	    	//System.out.println("At " + (i + 1) + " clothes, expecting " + oneBearExpected + ", got " + ans);
	    	assertEquals(oneBearExpected, ans);
	    }
	    
	    @Test
	    public void buy2Get3Clothes() {
	    	// One Bear base stuffing
	        
	        BearWorkshop oneBear = null;
	        try {
	        	oneBear = new BearWorkshop("NY");
	        } catch (Exception e) {
	        }
	        
	      //complex looking but simple principle. I run this test ten times. 
	        for (int i = 1; i < 10; i++) {
	        	//new bear for each tests to ensure no artifacts turn weird.
	        	Bear customBear = new Bear(Stuffing.stuffing.BASE);
	            oneBear.addBear(customBear); // $30 stuffing + $1 casing
	            
	            //add a number of clothes to the bear, all $4.
	            for (int j = 1; j < i + 1; j++)
	            	customBear.clothing.add(new Clothing(4.00, j+1+"")); 
	           
	            //Calcuate the expected cost of the bear: I use integer division, which convenently rounds down.
	        	oneBearExpected = (double)((i/3) * 4);
	        	Double ans = oneBear.calculateSavings();
	        	//old test line:
	        	//System.out.println("At " + (i) + " clothes, expecting " + oneBearExpected + ", got " + ans);
	        	assertEquals(oneBearExpected, ans);
	        	
	        	//removes bear in preparation for new loop
	        	oneBear.removeBear(customBear);
	        }
	    }
	    
	    @Test
	    public void buy2Get3ClothesDifferentPriceHiNum() {
	    	// One Bear base stuffing
	        
	        BearWorkshop oneBear = null;
	        try {
	        	oneBear = new BearWorkshop("NY");
	        } catch (Exception e) {
	        }
	       
	      //complex looking but simple principle. I run this test ten times. 
	        for (int i = 1; i < 10; i++) {
	        	//new bear for each tests to ensure no artifacts turn weird.
	        	Bear customBear = new Bear(Stuffing.stuffing.BASE);
	            oneBear.addBear(customBear); // $30 stuffing + $1 casing
	            
	            //add a number of clothes to the bear, the prices of which go up in increments of one
	            for (int j = 1; j < i + 1; j++)
	            	customBear.clothing.add(new Clothing((double) j, j+"")); 
	            
	            //quick answer array; at this scale it's impractical to write an algorithm
	            double[] factorial = new double[] {0, 1, 3, 6, 10, 15};
	            
	            oneBearExpected = factorial[i/3];
	        	
	        	Double ans = oneBear.calculateSavings();
	        	//old test line:
	        	System.out.println("At " + (i) + " clothes, expecting " + oneBearExpected + ", got " + ans);
	        	assertEquals(oneBearExpected, ans);
	        	
	        	//removes bear in preparation for new loop
	        	oneBear.removeBear(customBear);
	        }
	    }

	    

	    // sample test
	    @Test
	    public void threeBearsSaveOnCheapest() {
	    	 // Three Bears expected to not pay for cheapest one
	    	BearWorkshop threeBears = null;
	        try {
	        	threeBears = new BearWorkshop("AZ");
	        } catch (Exception e) {
	        }
	    	
	        threeBears.addBear(new Bear(Stuffing.stuffing.BASE)); // this is the cheapest one
	        threeBears.addBear(new Bear(Stuffing.stuffing.DOWN));
	        threeBears.addBear(new Bear(Stuffing.stuffing.FOAM));
	        threeBearsExpected = 31.00;

	        Double ans = threeBears.calculateSavings();
	        assertEquals(threeBearsExpected, ans);
	    }
	    
	    //What happens when all three bears are equal price?
	    @Test
	    public void threeBearsSaveOnCheapestEqual() {
	    	 // Three Bears expected to not pay for cheapest one
	    	BearWorkshop threeBears = null;
	        try {
	        	threeBears = new BearWorkshop("AZ");
	        } catch (Exception e) {
	        }
	    	
	        threeBears.addBear(new Bear(Stuffing.stuffing.BASE)); 
	        threeBears.addBear(new Bear(Stuffing.stuffing.BASE));
	        threeBears.addBear(new Bear(Stuffing.stuffing.BASE));
	        
	        threeBearsExpected = 31.00;

	        Double ans = threeBears.calculateSavings();
	        assertEquals(threeBearsExpected, ans);
	    }
	    
	  //What happens when all three bears are equal price but have accessories of different prices?
	    @Test
	    public void threeBearsSaveOnCheapestAccessories() {
	    	 // Three Bears expected to not pay for cheapest one
	    	BearWorkshop threeBears = null;
	        try {
	        	threeBears = new BearWorkshop("AZ");
	        } catch (Exception e) {
	        }
	    	
	      //cheapest bear: $31 + $4 = $35
	        Bear bear1 = new Bear(Stuffing.stuffing.BASE);
	        bear1.clothing.add(new Clothing());
	        threeBears.addBear(bear1); 
	        
	        //expensive bear: $31 + $10 = $41
	        Bear bear2 = new Bear(Stuffing.stuffing.BASE);
	        bear2.noisemakers.add(new NoiseMaker());
	        threeBears.addBear(bear2);
	 
	        //expensive bear: $31 + $15 = 46
	        Bear bear3 = new Bear(Stuffing.stuffing.BASE);
	        bear3.ink = new Embroidery("long.embroidery"); //fifteen character embroidery is $15 
	        threeBears.addBear(bear3);
	        
	        //I included the price of the clothes with the bear, as it is part of the bear object. Should I not have?
	        threeBearsExpected = 35.00;

	        Double ans = threeBears.calculateSavings();
	        assertEquals(threeBearsExpected, ans);
	    }
	    
	    @Test
	    public void threeBearsSaveOnCheapestAccessoriesFoam() {
	    	 // Three Bears expected to not pay for cheapest one
	    	BearWorkshop threeBears = null;
	        try {
	        	threeBears = new BearWorkshop("AZ");
	        } catch (Exception e) {
	        }
	    	
	      //expensive bear: $31 + $4 + $10 = $45
	        Bear bear1 = new Bear(Stuffing.stuffing.BASE);
	        bear1.clothing.add(new Clothing());
	        bear1.noisemakers.add(new NoiseMaker());
	        threeBears.addBear(bear1); 
	        
	        //cheaper bear: $41
	        Bear bear2 = new Bear(Stuffing.stuffing.DOWN);
	        threeBears.addBear(bear2);
	 
	        //cheaper bear: $41
	        Bear bear3 = new Bear(Stuffing.stuffing.DOWN);
	        threeBears.addBear(bear3);
	        
	        //I included the price of the clothes with the bear, as it is part of the bear object. Should I not have?
	        threeBearsExpected = 41.00;

	        Double ans = threeBears.calculateSavings();
	        assertEquals(threeBearsExpected, ans);
	    }

	    // sample test
	 
	    @Test
	    public void oneBearTest3clothings() {
	        BearWorkshop bears = null;
	        try {
	            bears = new BearWorkshop("DC");
	        } catch (Exception e) {
	        }
	        
	        Bear customBear = new Bear(Stuffing.stuffing.BASE); // $31
	        bears.addBear(customBear);

		    customBear.clothing.add(new Clothing(4, "Hat")); //$35
		    customBear.clothing.add(new Clothing(4, "Sunglasses")); //$39
		    customBear.clothing.add(new Clothing(4, "Shoes")); // free
		    
	        Double bearsExpected = 4.0; // one cloth item for free
	        Double ans = bears.calculateSavings();
	        assertEquals(bearsExpected, ans, 0.005);
	    }
	    
	    @Test
	    //To test whether the 10 accessories rule applies to clothes
	    public void oneBearTest10Clothes() {
	        BearWorkshop bears = null;
	        try {
	            bears = new BearWorkshop("AZ");
	        } catch (Exception e) {
	        }
	        
	        Bear customBear = new Bear(Stuffing.stuffing.BASE); //$31
	        bears.addBear(customBear);

	        //add 40 dollars worth of clothes
	        for (int i = 0; i < 15; i++) {
	        	customBear.clothing.add(new Clothing(4, "test")); 
	        }
		    
	        //bear is now $71
	        Double bearsExpected = 27.10; // ten percent of cost of bear plus $20 of free clothes
	        Double ans = bears.calculateSavings();
	        assertEquals(bearsExpected, ans, 0.005);
	    }   
	    
	    @Test
	  //To test whether the 10 accessories rule applies to clothes
	    public void oneBearTest10ClothesDown() {
	        BearWorkshop bears = null;
	        try {
	            bears = new BearWorkshop("AZ");
	        } catch (Exception e) {
	        }
	        
	        Bear customBear = new Bear(Stuffing.stuffing.DOWN); //$41
	        bears.addBear(customBear);

	        //add 40 dollars worth of clothes
	        for (int i = 0; i < 15; i++) {
	        	customBear.clothing.add(new Clothing(4, "test")); 
	        }
		    
	        //bear is now $81
	        Double bearsExpected = 28.10; // ten percent of cost of bear plus $20 of free clothes
	        Double ans = bears.calculateSavings();
	        assertEquals(bearsExpected, ans, 0.005);
	    }   
	    
	    @Test
	  //To test whether the 10 accessories rule applies to clothes
	    public void oneBearTest10ClothesFoam() {
	        BearWorkshop bears = null;
	        try {
	            bears = new BearWorkshop("AZ");
	        } catch (Exception e) {
	        }
	        
	        Bear customBear = new Bear(Stuffing.stuffing.FOAM); //$51
	        bears.addBear(customBear);

	        //add 40 dollars worth of clothes
	        for (int i = 0; i < 15; i++) {
	        	customBear.clothing.add(new Clothing(4, "test")); 
	        }
		    
	        //bear is now $91
	        Double bearsExpected = 29.10; // ten percent of cost of bear plus $20 of free clothes
	        Double ans = bears.calculateSavings();
	        assertEquals(bearsExpected, ans, 0.005);
	    }  
	    
	    @Test
	  //To test whether the 10 accessories rule applies to noisemaker
	    public void oneBearTest10NoisemakersBase() {
	        BearWorkshop bears = null;
	        try {
	            bears = new BearWorkshop("AZ");
	        } catch (Exception e) {
	        }
	        
	        Bear customBear = new Bear(Stuffing.stuffing.BASE); //$31
	        bears.addBear(customBear);

	        //add 100 dollars worth of noisemakers
	        for (int i = 0; i < 10; i++) {
	        	customBear.noisemakers.add(new NoiseMaker()); 
	        }
		    
	        //bear is now $131
	        Double bearsExpected = 13.10; // ten percent of cost of bear
	        Double ans = bears.calculateSavings();
	        assertEquals(bearsExpected, ans, 0.005);
	    }   
	    
	    @Test
	    public void oneBearTest10NoisemakersDown() {
	        BearWorkshop bears = null;
	        try {
	            bears = new BearWorkshop("AZ");
	        } catch (Exception e) {
	        }
	        
	        Bear customBear = new Bear(Stuffing.stuffing.DOWN); //$41
	        bears.addBear(customBear);

	        //add 100 dollars worth of noisemakers
	        for (int i = 0; i < 10; i++) {
	        	customBear.noisemakers.add(new NoiseMaker()); 
	        }
		    
	        //bear is now $141
	        Double bearsExpected = 14.10; // ten percent of cost of bear
	        Double ans = bears.calculateSavings();
	        
	        assertEquals(bearsExpected, ans, 0.005);
	    }   
	    
	    @Test
	  //To test whether the 10 accessories rule applies to noisemakers
	    public void oneBearTest10NoisemakersFoam() {
	    	BearWorkshop bears = null;
	        try {
	            bears = new BearWorkshop("AZ");
	        } catch (Exception e) {
	        }
	        
	        Bear customBear = new Bear(Stuffing.stuffing.FOAM); //$51
	        bears.addBear(customBear);

	        //add 100 dollars worth of noisemakers
	        for (int i = 0; i < 10; i++) {
	        	customBear.noisemakers.add(new NoiseMaker()); 
	        }
		    
	        //bear is now $151
	        Double bearsExpected = 15.10; // ten percent of cost of bear
	        Double ans = bears.calculateSavings();
	        
	        assertEquals(bearsExpected, ans, 0.005);
	    }   
	    
	    @Test
	  //To test whether the 10 accessories rule applies to custom noisemakers
	    public void oneBearTest10CustomNoisemakers() {
	        BearWorkshop bears = null;
	        try {
	            bears = new BearWorkshop("AZ");
	        } catch (Exception e) {
	        }
	        
	        Bear customBear = new Bear(Stuffing.stuffing.BASE); //$31
	        bears.addBear(customBear);

	        //add 50 dollars worth of noisemakers
	        for (int i = 0; i < 10; i++) {
	        	customBear.noisemakers.add(new NoiseMaker("i", "o", Location.LEFT_FOOT)); 
	        }
		    
	        //bear is now $81
	        Double bearsExpected = 8.10; // ten percent of cost of bear
	        Double ans = bears.calculateSavings();
	        assertEquals(bearsExpected, ans, 0.005);
	    }   
	    
	    //My mistake- i misinterpreted the reqirements for the 10% off rule. 
	    //(I thought embroidery was an accessory)
	    /**
	    @Test
	    public void oneBearTest10LetterEmbroidery() {
	    	BearWorkshop bears = null;
	        try {
	            bears = new BearWorkshop("AZ");
	        } catch (Exception e) {
	        }
	        
	        Bear customBear = new Bear(Stuffing.stuffing.BASE); //$31
	        bears.addBear(customBear);
	        
	        //adding $10 worth of embroidery
	        customBear.ink = new Embroidery("1234567890");
	        
	      //bear is now $41
	        Double bearsExpected = 8.10; // ten percent of cost of bear
	        Double ans = bears.calculateSavings();
	        assertEquals(bearsExpected, ans, 0.005);
	    }
	    */

}