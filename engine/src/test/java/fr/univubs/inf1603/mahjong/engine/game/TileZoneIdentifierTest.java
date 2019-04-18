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
public class TileZoneIdentifierTest {
    
    public TileZoneIdentifierTest() {
    }

    /**
     * Test of values method, of class TileZoneIdentifier.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        TileZoneIdentifier[] expResult = null;
        TileZoneIdentifier[] result = TileZoneIdentifier.values();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class TileZoneIdentifier.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "";
        TileZoneIdentifier expResult = null;
        TileZoneIdentifier result = TileZoneIdentifier.valueOf(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNormalizedName method, of class TileZoneIdentifier.
     */
    @Test
    public void testGetNormalizedName() {
        System.out.println("getNormalizedName");
        TileZoneIdentifier instance = null;
        String expResult = "";
        String result = instance.getNormalizedName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNormalizedNameFromIdentifier method, of class TileZoneIdentifier.
     */
    @Test
    public void testGetNormalizedNameFromIdentifier() {
        System.out.println("getNormalizedNameFromIdentifier");
        TileZoneIdentifier identifier = null;
        String expResult = "";
        String result = TileZoneIdentifier.getNormalizedNameFromIdentifier(identifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIdentifierFromNormalizedName method, of class TileZoneIdentifier.
     */
    @Test
    public void testGetIdentifierFromNormalizedName() throws Exception {
        System.out.println("getIdentifierFromNormalizedName");
        String normalizedName = "";
        TileZoneIdentifier expResult = null;
        TileZoneIdentifier result = TileZoneIdentifier.getIdentifierFromNormalizedName(normalizedName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
