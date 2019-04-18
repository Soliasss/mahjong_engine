/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine.game;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author purpl
 */
public class MahjongTileZoneTest {
    
    public MahjongTileZoneTest() {
    }

    /**
     * Test of addTile method, of class MahjongTileZone.
     */
    @Test
    public void testAddTile() {
        System.out.println("addTile");
        GameTile tile = null;
        MahjongTileZone instance = null;
        instance.addTile(tile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeTile method, of class MahjongTileZone.
     */
    @Test
    public void testRemoveTile() {
        System.out.println("removeTile");
        GameTile tile = null;
        MahjongTileZone instance = null;
        instance.removeTile(tile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUUID method, of class MahjongTileZone.
     */
    @Test
    public void testGetUUID() {
        System.out.println("getUUID");
        MahjongTileZone instance = null;
        UUID expResult = null;
        UUID result = instance.getUUID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPropertyChangeSupport method, of class MahjongTileZone.
     */
    @Test
    public void testGetPropertyChangeSupport() {
        System.out.println("getPropertyChangeSupport");
        MahjongTileZone instance = null;
        PropertyChangeSupport expResult = null;
        PropertyChangeSupport result = instance.getPropertyChangeSupport();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class MahjongTileZone.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        MahjongTileZone instance = null;
        MahjongTileZone expResult = null;
        MahjongTileZone result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTiles method, of class MahjongTileZone.
     */
    @Test
    public void testGetTiles() {
        System.out.println("getTiles");
        MahjongTileZone instance = null;
        ArrayList<GameTile> expResult = null;
        ArrayList<GameTile> result = instance.getTiles();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIdentifier method, of class MahjongTileZone.
     */
    @Test
    public void testGetIdentifier() {
        System.out.println("getIdentifier");
        MahjongTileZone instance = null;
        TileZoneIdentifier expResult = null;
        TileZoneIdentifier result = instance.getIdentifier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
