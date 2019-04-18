/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine.game;

import fr.univubs.inf1603.mahjong.engine.rule.Wind;
import java.beans.PropertyChangeSupport;
import java.util.UUID;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author purpl
 */
public class MahjongBoardTest {
    
    public MahjongBoardTest() {
    }

    /**
     * Test of getCurrentWind method, of class MahjongBoard.
     */
    @Test
    public void testGetCurrentWind() throws Exception {
        System.out.println("getCurrentWind");
        MahjongBoard instance = null;
        Wind expResult = null;
        Wind result = instance.getCurrentWind();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWind method, of class MahjongBoard.
     */
    @Test
    public void testSetWind() {
        System.out.println("setWind");
        Wind newWind = null;
        MahjongBoard instance = null;
        instance.setWind(newWind);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getViewFromWind method, of class MahjongBoard.
     */
    @Test
    public void testGetViewFromWind() throws Exception {
        System.out.println("getViewFromWind");
        Wind wind = null;
        MahjongBoard instance = null;
        Board expResult = null;
        Board result = instance.getViewFromWind(wind);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUUID method, of class MahjongBoard.
     */
    @Test
    public void testGetUUID() {
        System.out.println("getUUID");
        MahjongBoard instance = null;
        UUID expResult = null;
        UUID result = instance.getUUID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPropertyChangeSupport method, of class MahjongBoard.
     */
    @Test
    public void testGetPropertyChangeSupport() {
        System.out.println("getPropertyChangeSupport");
        MahjongBoard instance = null;
        PropertyChangeSupport expResult = null;
        PropertyChangeSupport result = instance.getPropertyChangeSupport();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTile method, of class MahjongBoard.
     */
    @Test
    public void testGetTile() throws Exception {
        System.out.println("getTile");
        int gameIndex = 0;
        MahjongBoard instance = null;
        GameTileInterface expResult = null;
        GameTileInterface result = instance.getTile(gameIndex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTileZoneOfTile method, of class MahjongBoard.
     */
    @Test
    public void testGetTileZoneOfTile_int() throws Exception {
        System.out.println("getTileZoneOfTile");
        int gameIndex = 0;
        MahjongBoard instance = null;
        TileZone expResult = null;
        TileZone result = instance.getTileZoneOfTile(gameIndex);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTileZoneOfTile method, of class MahjongBoard.
     */
    @Test
    public void testGetTileZoneOfTile_GameTileInterface() throws Exception {
        System.out.println("getTileZoneOfTile");
        GameTileInterface tile = null;
        MahjongBoard instance = null;
        TileZone expResult = null;
        TileZone result = instance.getTileZoneOfTile(tile);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTileZone method, of class MahjongBoard.
     */
    @Test
    public void testGetTileZone_TileZoneIdentifier() throws Exception {
        System.out.println("getTileZone");
        TileZoneIdentifier identifier = null;
        MahjongBoard instance = null;
        TileZone expResult = null;
        TileZone result = instance.getTileZone(identifier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTileZone method, of class MahjongBoard.
     */
    @Test
    public void testGetTileZone_String() throws Exception {
        System.out.println("getTileZone");
        String normalizedName = "";
        MahjongBoard instance = null;
        TileZone expResult = null;
        TileZone result = instance.getTileZone(normalizedName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
