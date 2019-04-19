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

    /**
     * Test of getNormalizedName method, of class TileZoneIdentifier.
     */
    @Test
    public void testGetNormalizedName() {
        System.out.println("getNormalizedName");
        TileZoneIdentifier instance = TileZoneIdentifier.Wall;
        String expResult = "Wall";
        String result = instance.getNormalizedName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNormalizedNameFromIdentifier method, of class TileZoneIdentifier.
     */
    @Test
    public void testGetNormalizedNameFromIdentifier() {
        System.out.println("getNormalizedNameFromIdentifier");
        TileZoneIdentifier identifier = TileZoneIdentifier.Wall;
        String expResult = "Wall";
        String result = TileZoneIdentifier.getNormalizedNameFromIdentifier(identifier);
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdentifierFromNormalizedName method, of class TileZoneIdentifier.
     */
    @Test
    public void testGetIdentifierFromNormalizedName() throws Exception {
        System.out.println("getIdentifierFromNormalizedName");
        String normalizedName = "Wall";
        TileZoneIdentifier expResult = TileZoneIdentifier.Wall;
        TileZoneIdentifier result = TileZoneIdentifier.getIdentifierFromNormalizedName(normalizedName);
        assertEquals(expResult, result);
    }
    
}
