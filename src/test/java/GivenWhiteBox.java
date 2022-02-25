package test.java;

import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;

import main.java.Bear;
import main.java.BearWorkshop;
import main.java.Clothing;
import main.java.Embroidery;
import main.java.NoiseMaker;

import static org.junit.Assert.*;



public class GivenWhiteBox {
    BearWorkshop oneBear;

    @Before
    public void setUp() throws Exception {
    }


    @Test
    //This method was asking for the wrong amount: instead of asking for the base level of bear plus tax
    //It has instead asked for 5.36, a seemingly random number
    public void checkoutOneBear() {
        // One Student
        oneBear = new BearWorkshop("AZ");
        oneBear.addBear(new Bear());
        Double ans = oneBear.checkout();
        
        //$30 (stuffing) + $1 (casing) + tax
        Double expected = (30.0 + 1.0) * 1.07;
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
}
