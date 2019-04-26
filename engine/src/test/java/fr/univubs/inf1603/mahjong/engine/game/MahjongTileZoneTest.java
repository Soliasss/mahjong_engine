/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine.game;

import fr.univubs.inf1603.mahjong.engine.rule.InternationalTiles;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class MahjongTileZoneTest {
    
    /**
     * Test of addTile method, of class MahjongTileZone.
     */
    @Test
    public void testAddTile() {
        System.out.println("addTile");
        GameTile tile = new GameTile(1,InternationalTiles.BAMBOO_1);
        MahjongTileZone instance = new MahjongTileZone(new ArrayList<>(), UUID.randomUUID(), TileZoneIdentifier.Wall);
        instance.addTile(tile);
        assertTrue(instance.getTiles().contains(tile));
    }

    /**
     * Test of removeTile method, of class MahjongTileZone.
     */
    @Test
    public void testRemoveTile() {
        System.out.println("removeTile");
        GameTile tile = new GameTile(1,InternationalTiles.BAMBOO_1);
        MahjongTileZone instance = new MahjongTileZone(new ArrayList<>(), UUID.randomUUID(), TileZoneIdentifier.Wall);
        instance.addTile(tile);
        instance.removeTile(tile);
        assertFalse(instance.getTiles().contains(tile));
    }

    /**
     * Test of getUUID method, of class MahjongTileZone.
     */
    @Test
    public void testGetUUID() {
        System.out.println("getUUID");
        UUID expResult = UUID.randomUUID();
        MahjongTileZone instance = new MahjongTileZone(new ArrayList<>(), expResult, TileZoneIdentifier.Wall);
        UUID result = instance.getUUID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPropertyChangeSupport method, of class MahjongTileZone.
     */
    @Test
    public void testGetPropertyChangeSupport() {
        System.out.println("getPropertyChangeSupport");
        MahjongTileZone instance = new MahjongTileZone(TileZoneIdentifier.Wall);
        PropertyChangeSupport result = instance.getPropertyChangeSupport();
        assertNotNull(result);
    }


    /**
     * Test of getTiles method, of class MahjongTileZone.
     */
    @Test
    public void testGetTiles() {
        System.out.println("getTiles");
        ArrayList<GameTileInterface> expResult = new ArrayList<>();
        MahjongTileZone instance = new MahjongTileZone(expResult, UUID.randomUUID(), TileZoneIdentifier.HandEast);
        ArrayList<GameTileInterface> result = instance.getTiles();
        assertEquals(expResult, result);
    }

    /**
     * Test of getIdentifier method, of class MahjongTileZone.
     */
    @Test
    public void testGetIdentifier() {
        System.out.println("getIdentifier");
        TileZoneIdentifier expResult = TileZoneIdentifier.HandEast;
        MahjongTileZone instance = new MahjongTileZone(new ArrayList<>(), UUID.randomUUID(), expResult);
        TileZoneIdentifier result = instance.getIdentifier();
        assertEquals(expResult, result);
    }
    
}
