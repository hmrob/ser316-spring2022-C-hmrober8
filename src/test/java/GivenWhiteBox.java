package test.java;

import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;

import main.java.Bear;
import main.java.BearWorkshop;

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
}
