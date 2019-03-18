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
public class FlowerTileTest {
    
    public FlowerTileTest() {
    }

    /**
     * Test of getFlower method, of class FlowerTile.
     */
    @Test
    public void testGetFlower() {
        System.out.println("getFlower");
        FlowerTile instance = null;
        FlowerTile.Flower expResult = null;
        FlowerTile.Flower result = instance.getFlower();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toNormalizedName method, of class FlowerTile.
     */
    @Test
    public void testToNormalizedName() {
        System.out.println("toNormalizedName");
        FlowerTile instance = null;
        String expResult = "";
        String result = instance.toNormalizedName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class FlowerTile.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        FlowerTile instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
