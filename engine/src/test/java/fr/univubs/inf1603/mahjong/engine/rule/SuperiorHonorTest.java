package fr.univubs.inf1603.mahjong.engine.rule;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class SuperiorHonorTest {
    
    /**
     * Test of getDragon method, of class SuperiorHonor.
     */
    @Test
    public void testGetDragon() {
        System.out.println("getDragon");
        SuperiorHonor instance = new SuperiorHonor(SuperiorHonor.Dragon.GREEN);
        SuperiorHonor.Dragon expResult = SuperiorHonor.Dragon.GREEN;
        SuperiorHonor.Dragon result = instance.getDragon();
        assertEquals(expResult, result);
    }

    /**
     * Test of toNormalizedName method, of class SuperiorHonor.
     */
    @Test
    public void testToNormalizedName() {
        System.out.println("toNormalizedName");
        SuperiorHonor instance = new SuperiorHonor(SuperiorHonor.Dragon.GREEN);
        String expResult = "Dg";
        String result = instance.toNormalizedName();
        assertEquals(expResult, result);
    }
    
}
