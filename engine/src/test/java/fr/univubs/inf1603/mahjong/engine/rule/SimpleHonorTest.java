package fr.univubs.inf1603.mahjong.engine.rule;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 */
public class SimpleHonorTest {
    /**
     * Test of toNormalizedName method, of class SimpleHonor.
     */
    @Test
    public void testToNormalizedName() {
        System.out.println("toNormalizedName");
        SimpleHonor instance = new SimpleHonor(Wind.WEST);
        String expResult = "Ww";
        String result = instance.toNormalizedName();
        assertEquals(expResult, result);
    }
    
}
