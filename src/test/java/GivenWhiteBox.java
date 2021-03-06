package test.java;
import main.java.*;

import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;

import main.java.Bear;
import main.java.BearWorkshop;
import main.java.Clothing;
import main.java.Embroidery;
import main.java.NoiseMaker;

import static org.junit.Assert.*;

import main.java.BearWorkshop;


public class GivenWhiteBox {
    BearWorkshop oneBear;

    @Before
    public void setUp() throws Exception {
    }


    @Test
    public void checkoutOneBear() {
        // One Student
        oneBear = new BearWorkshop("AZ");
        oneBear.addBear(new Bear());
        Double ans = oneBear.checkout();
        Double expected = 31 * 1.07;
        assertEquals(expected, ans, 0.005);
    }
    
    @Test
    /**
     * test of Sequence 1 from my control flow graph
     * complete code coverage
     * bear has 3 clothing items, a noisemaker, and an embroidery
     * full node and edge coverage
     */
    public void getCostSequence1() {
        oneBear = new BearWorkshop("AZ");
        
        Bear newBear = new Bear(); //$31
        
        newBear.clothing.add(new Clothing(4, "Hat")); //$35
	    newBear.clothing.add(new Clothing(4, "Sunglasses")); //$39
	    newBear.clothing.add(new Clothing(4, "Shoes")); // free
	    
	    newBear.noisemakers.add(new NoiseMaker()); //$49
	    
	    newBear.ink = (new Embroidery("Test")); //$53
	    oneBear.addBear(newBear);
        
        Double ans = oneBear.checkout();
        
        //$53 + tax
        Double expected = 53 * 1.07;
        assertEquals(expected, ans, 0.005);
    }
    
    @Test
    /**
     * test of Sequence 2 from my control flow graph
     * partial code coverage
     * bear has no items
     * partial node and edge coverage
     */
    public void getCostSequence2() {
        oneBear = new BearWorkshop("AZ");
        
        Bear newBear = new Bear(); //$31
        
	    oneBear.addBear(newBear);
        
        Double ans = oneBear.checkout();
        
        //$31 + tax
        Double expected = 31 * 1.07;
        assertEquals(expected, ans, 0.005);
    }
    
    @Test
    /**
     * test of Sequence 3 from my control flow graph
     * partial code coverage
     * bear has one clothing item and a four letter embroidery
     * partial node and edge coverage
     */
    public void getCostSequence3() {
        oneBear = new BearWorkshop("AZ");
        
        Bear newBear = new Bear(); //$31
        
        newBear.clothing.add(new Clothing(4, "Hat")); //$35
	    
	    newBear.ink = (new Embroidery("Test")); //39
	    oneBear.addBear(newBear);
        
        Double ans = oneBear.checkout();
        
        //$39 + tax
        Double expected = 39 * 1.07;
        assertEquals(expected, ans, 0.005);
    }
    
    @Test
    /**
     * test of full node and edge coverage of getRawCost()
     * complete code coverage
     * bear has 3 clothing items, a noisemaker, and an embroidery
     */
    public void getRawCostFullCoverage() {
        oneBear = new BearWorkshop("AZ");
        
        Bear newBear = new Bear(); //$31
        
        newBear.clothing.add(new Clothing(4, "Hat")); //$35
	    newBear.clothing.add(new Clothing(4, "Sunglasses")); //$39
	    newBear.clothing.add(new Clothing(4, "Shoes")); // $39
	    
	    newBear.noisemakers.add(new NoiseMaker()); //$49
	    
	    newBear.ink = (new Embroidery("Test")); //$53
	    oneBear.addBear(newBear);
        
        Double ans = oneBear.checkout();
        
        //$53 + tax
        Double expected = 53 * 1.07;
        assertEquals(expected, ans, 0.005);
    }
    
    @Test
    /**
     * test of full node and edge coverage of getRawCost()
     * complete code coverage
     * bear has 3 clothing items, a noisemaker, and an embroidery
     */
    public void checkoutUnderageParent() {
        oneBear = new BearWorkshop("AZ");
        oneBear.addBear(new Bear());
        oneBear.setAge();
        
        Double ans = oneBear.checkout();
        
        //$57 + tax
        Double expected = -1.0;
        assertEquals(expected, ans, 0.005);
    }
    
    @Test
    /**
     * test of full node and edge coverage of getRawCost()
     * complete code coverage
     * bear has 3 clothing items, a noisemaker, and an embroidery
     */
    public void checkoutSuccessful() {
        oneBear = new BearWorkshop("AZ");
        
	    oneBear.addBear(new Bear());
        
        Double ans = oneBear.checkout();
        
        //$31 + tax
        Double expected = 31 * 1.07;
        assertEquals(expected, ans, 0.005);
    }
	
	@Test
    /**
     * test of the tax tree
     * complete code coverage
     * bear has 3 clothing items, a noisemaker, and an embroidery
     */
    public void TaxTest() {
        oneBear = new BearWorkshop("CA");
        
        Double ans = oneBear.calculateTax();
        
        Double expected = 1.1;
        assertEquals(expected, ans, 0.005);
    }
}

