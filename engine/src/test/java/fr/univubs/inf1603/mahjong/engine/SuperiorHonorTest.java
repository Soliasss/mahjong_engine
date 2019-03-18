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
public class SuperiorHonorTest {
    
    public SuperiorHonorTest() {
    }

    /**
     * Test of getDragon method, of class SuperiorHonor.
     */
    @Test
    public void testGetDragon() {
        System.out.println("getDragon");
        SuperiorHonor instance = null;
        SuperiorHonor.Dragon expResult = null;
        SuperiorHonor.Dragon result = instance.getDragon();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class SuperiorHonor.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        SuperiorHonor instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toNormalizedName method, of class SuperiorHonor.
     */
    @Test
    public void testToNormalizedName() {
        System.out.println("toNormalizedName");
        SuperiorHonor instance = null;
        String expResult = "";
        String result = instance.toNormalizedName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
