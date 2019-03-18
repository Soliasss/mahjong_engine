/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine;

import java.util.ArrayList;
import java.util.UUID;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author purpl
 */
public class TileZoneTest {
    
    public TileZoneTest() {
    }

    /**
     * Test of getTilesCollection method, of class TileZone.
     */
    @Test
    public void testGetTilesCollection() {
        System.out.println("getTilesCollection");
        TileZone instance = null;
        ArrayList<AbstractTile> expResult = null;
        ArrayList<AbstractTile> result = instance.getTilesCollection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getZonesCollection method, of class TileZone.
     */
    @Test
    public void testGetZonesCollection() {
        System.out.println("getZonesCollection");
        TileZone instance = null;
        ArrayList<Zone> expResult = null;
        ArrayList<Zone> result = instance.getZonesCollection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMoney method, of class TileZone.
     */
    @Test
    public void testGetMoney() {
        System.out.println("getMoney");
        TileZone instance = null;
        int expResult = 0;
        int result = instance.getMoney();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUUID method, of class TileZone.
     */
    @Test
    public void testGetUUID() {
        System.out.println("getUUID");
        TileZone instance = null;
        UUID expResult = null;
        UUID result = instance.getUUID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addTile method, of class TileZone.
     */
    @Test
    public void testAddTile() {
        System.out.println("addTile");
        AbstractTile tile = null;
        TileZone instance = null;
        boolean expResult = false;
        boolean result = instance.addTile(tile);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeTile method, of class TileZone.
     */
    @Test
    public void testRemoveTile() {
        System.out.println("removeTile");
        AbstractTile tile = null;
        TileZone instance = null;
        boolean expResult = false;
        boolean result = instance.removeTile(tile);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addZone method, of class TileZone.
     */
    @Test
    public void testAddZone() {
        System.out.println("addZone");
        Zone zone = null;
        TileZone instance = null;
        boolean expResult = false;
        boolean result = instance.addZone(zone);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeZone method, of class TileZone.
     */
    @Test
    public void testRemoveZone() {
        System.out.println("removeZone");
        Zone zone = null;
        TileZone instance = null;
        boolean expResult = false;
        boolean result = instance.removeZone(zone);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
