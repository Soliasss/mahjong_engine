/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author purpl
 */
public class SimpleHonorTest {
    
    public SimpleHonorTest() {
    }

    /**
     * Test of toString method, of class SimpleHonor.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        SimpleHonor instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toNormalizedName method, of class SimpleHonor.
     */
    @Test
    public void testToNormalizedName() {
        System.out.println("toNormalizedName");
        SimpleHonor instance = null;
        String expResult = "";
        String result = instance.toNormalizedName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
