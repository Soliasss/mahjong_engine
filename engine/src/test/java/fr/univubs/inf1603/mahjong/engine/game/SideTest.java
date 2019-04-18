/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine.game;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author purpl
 */
public class SideTest {
    
    public SideTest() {
    }

    /**
     * Test of values method, of class Side.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        Side[] expResult = null;
        Side[] result = Side.values();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class Side.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "";
        Side expResult = null;
        Side result = Side.valueOf(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
