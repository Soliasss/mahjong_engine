/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine.game;

import fr.univubs.inf1603.mahjong.engine.rule.InternationalTiles;
import fr.univubs.inf1603.mahjong.engine.rule.Wind;
import java.beans.PropertyChangeSupport;
import java.util.EnumMap;
import java.util.UUID;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class MahjongBoardTest {

    /**
     * Test of getCurrentWind method, of class MahjongBoard.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetCurrentWind() throws Exception {
        System.out.println("getCurrentWind");
        MahjongBoard instance = new MahjongBoard(Wind.NORTH);
        Wind expResult = Wind.NORTH;
        Wind result = instance.getCurrentWind();
        assertEquals(expResult, result);
    }

    /**
     * Test of setWind method, of class MahjongBoard.
     */
    @Test
    public void testSetWind() {
        System.out.println("setWind");
        Wind newWind = Wind.NORTH;
        MahjongBoard instance = new MahjongBoard(Wind.EAST);
        instance.setWind(newWind);
        Wind result = null;
        try {
            result = instance.getCurrentWind();
        } catch (GameException ex) {
            fail("MahjongBoard threw an exception : "+ex);
        }
        assertEquals(newWind, result);
    }

    /**
     * Test of getUUID method, of class MahjongBoard.
     */
    @Test
    public void testGetUUID() {
        System.out.println("getUUID");
        UUID u  = UUID.randomUUID();
        MahjongBoard instance = new MahjongBoard(Wind.WEST, u, new EnumMap<>(TileZoneIdentifier.class));
        UUID expResult = u;
        UUID result = instance.getUUID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPropertyChangeSupport method, of class MahjongBoard.
     */
    @Test
    public void testGetPropertyChangeSupport() {
        System.out.println("getPropertyChangeSupport");
        MahjongBoard instance = new MahjongBoard(Wind.WEST);
        PropertyChangeSupport result = instance.getPropertyChangeSupport();
        assertNotNull(result);
    }

    /**
     * Test of getTile method, of class MahjongBoard.
     */
    @Test
    public void testGetTile(){
        System.out.println("getTile");
        int gameIndex = 1;
        MahjongBoard instance = new MahjongBoard(Wind.WEST);
        GameTile gameTile = new GameTile(gameIndex, InternationalTiles.BAMBOO_1);
        try {
            ((MahjongTileZone)instance.getTileZone(TileZoneIdentifier.DiscardEast)).addTile(gameTile);
        } catch (GameException ex) {
            fail("MahjongBoard threw an exception :"+ex);
        }
        GameTileInterface expResult = gameTile;
        GameTileInterface result = null;
        try {
            result = instance.getTile(gameIndex);
        } catch (GameException ex) {
            fail("MahjongBoard threw an exception :"+ex);
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of getTileZoneOfTile method, of class MahjongBoard.
     */
    @Test
    public void testGetTileZoneOfTile_int(){
        System.out.println("getTileZoneOfTile");
        int gameIndex = 1;
        MahjongBoard instance = new MahjongBoard(Wind.WEST);
        GameTile gameTile = new GameTile(gameIndex, InternationalTiles.BAMBOO_1);
        try {
            ((MahjongTileZone)instance.getTileZone(TileZoneIdentifier.DiscardEast)).addTile(gameTile);
        } catch (GameException ex) {
            fail("MahjongBoard threw an exception :"+ex);
        }
        TileZone expResult1 = null;
        try {
            expResult1 = instance.getTileZone(TileZoneIdentifier.DiscardEast);
        } catch (GameException ex) {
            fail("MahjongBoard threw an exception : "+ex);
        }
        TileZone result1=null;
        try {
            result1 = instance.getTileZoneOfTile(gameIndex);
        } catch (GameException ex) {
            fail("MahjongBoard threw an exception : "+ex);
        }
        
        TileZoneIdentifier expResult2 = TileZoneIdentifier.DiscardEast;
        TileZoneIdentifier result2 = result1.getIdentifier();
        assertEquals(expResult1, result1);
        assertEquals(expResult2, result2);

    }

    /**
     * Test of getTileZoneOfTile method, of class MahjongBoard.
     */
    @Test
    public void testGetTileZoneOfTile_GameTileInterface(){
        System.out.println("getTileZoneOfTile");
        GameTile tile = new GameTile(0, InternationalTiles.BAMBOO_1);
        MahjongBoard instance = new MahjongBoard(Wind.WEST);
        try {
            ((MahjongTileZone)instance.getTileZone(TileZoneIdentifier.DiscardEast)).addTile(tile);
        } catch (GameException ex) {
            fail("MahjongBoard threw an exception :"+ex);
        }

        TileZone expResult=null;
        try {
            expResult = instance.getTileZone(TileZoneIdentifier.DiscardEast);
        } catch (GameException ex) {
            fail("MahjongBoard threw an exception :"+ex);
        }
        TileZone result=null;
        try {
            result = instance.getTileZoneOfTile(tile);
        } catch (GameException ex) {
            fail("MahjongBoard threw an exception :"+ex);
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of getTileZone method, of class MahjongBoard.
     */
    @Test
    public void testGetTileZone_TileZoneIdentifier(){
        System.out.println("getTileZone");
        GameTile tile = new GameTile(0, InternationalTiles.BAMBOO_1);
        MahjongBoard instance = new MahjongBoard(Wind.WEST);
        try {
            ((MahjongTileZone)instance.getTileZone(TileZoneIdentifier.DiscardEast)).addTile(tile);
        } catch (GameException ex) {
            fail("MahjongBoard threw an exception :"+ex);
        }

        TileZoneIdentifier result=null;
        try {
            result = instance.getTileZone(TileZoneIdentifier.DiscardEast).getIdentifier();
        } catch (GameException ex) {
            fail("MahjongBoard threw an exception :"+ex);
        }
        TileZoneIdentifier expResult = TileZoneIdentifier.DiscardEast;
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getTileZone method, of class MahjongBoard.
     */
    @Test
    public void testGetTileZone_String(){
        System.out.println("getTileZone");
        String normalizedName = "Wall";
        MahjongBoard instance = new MahjongBoard(Wind.WEST);
        TileZoneIdentifier expResult = TileZoneIdentifier.Wall;
        TileZoneIdentifier result=null;
        try {
            result = instance.getTileZone(normalizedName).getIdentifier();
        } catch (GameException ex) {
            fail("MahjongBoard threw an exception :"+ex);
        }
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getZones method, of class MahjongBoard.
     */
    public void testGetZones(){
        System.out.println("getZone");
        UUID u  = UUID.randomUUID();
        EnumMap<TileZoneIdentifier, TileZone> expResult = new EnumMap<>(TileZoneIdentifier.class);
        MahjongBoard instance = new MahjongBoard(Wind.WEST, UUID.randomUUID(), expResult );
        EnumMap<TileZoneIdentifier, TileZone> result = instance.getZones();
        assertEquals(expResult, result);
    }

}
