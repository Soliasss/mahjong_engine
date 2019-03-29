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
 * @author maxime
 */
public class TileZoneTest {
    
    public TileZoneTest() {
    }

   
    /**
     * Test of setHidden method, of class TileZone.
     * @throws fr.univubs.inf1603.mahjong.engine.ZoneException
     */
    @Test
    public void testSetHidden() throws ZoneException {
        System.out.println("setHidden");
        TileZone instance = new TileZone("Test",true);
        boolean expResult = true;
        boolean result = instance.setHidden();
        assertEquals(expResult, result);
    }

      
    /**
     * Test of getClone method, of class TileZone.
     * @throws fr.univubs.inf1603.mahjong.engine.ZoneException
     */
    @Test
    public void testGetClone() throws ZoneException {
        System.out.println("getClone");
        ArrayList<GameTile> content = new ArrayList<>();
        content.add(new GameTile(0, new CommonTile(CommonTile.Family.CHARACTERS, CommonTile.Number.EIGHT)));
        content.add(new GameTile(1, new CommonTile(CommonTile.Family.BAMBOO, CommonTile.Number.NINE)));
        TileZone instance = new TileZone("Test",content,false,UUID.randomUUID());
        TileZone result = (TileZone) instance.getClone();
        assertNotEquals(instance.getUUID(), result.getUUID());
        assertEquals(instance.getName(),result.getName());
        assertEquals(instance.isHidden(),result.isHidden());
        assertEquals(instance.isHideable(),result.isHideable());
        assertTrue(instance.getTiles().equals(result.getTiles()));
    }

    
    /**
     * Test of getUUID method, of class TileZone.
     * @throws fr.univubs.inf1603.mahjong.engine.ZoneException
     */
    @Test
    public void testGetUUID() throws ZoneException {
        System.out.println("getUUID");
               
        UUID expResult = UUID.randomUUID();
        ArrayList<GameTile> content = new ArrayList<>();
        content.add(new GameTile(0, new CommonTile(CommonTile.Family.CHARACTERS, CommonTile.Number.EIGHT)));
        TileZone zone = new TileZone("Test",content,false,expResult);
        UUID result = zone.getUUID();
        assertEquals(expResult, result);
    }

    

    /**
     * Test of getZones method, of class TileZone.
     */
    @Test
    public void testGetZones() {
        System.out.println("getZones");
        boolean bool=false;
        TileZone instance = new TileZone("Test", true);
        ArrayList<GameZone> result;
        try {
            result = instance.getZones();
        } catch (ZoneException ex) {
            bool=true;
        }
        assertTrue(bool);
    }

    /**
     * Test of getTiles method, of class TileZone.
     * @throws fr.univubs.inf1603.mahjong.engine.ZoneException
     */
    @Test
    public void testGetTiles() throws ZoneException {
        System.out.println("getTiles");
        GameTile tile = new GameTile(0, new CommonTile(CommonTile.Family.CHARACTERS, CommonTile.Number.EIGHT));
        ArrayList<GameTile> expResult = new ArrayList<>();
        expResult.add(tile);
        TileZone instance = new TileZone("Test", expResult, true, UUID.randomUUID());
        ArrayList<GameTile> result = instance.getTiles();
        assertTrue(result.equals(expResult));
    }

    /**
     * Test of setZones method, of class TileZone.
     */
    @Test
    public void testSetZones() {
        System.out.println("setZones");
        boolean bool=false;
        GameZone zone = new TileZone("Test", true);
        ArrayList<GameZone> zones = new ArrayList<>();
        zones.add(zone);
        TileZone instance = new TileZone("Test", true);
        try {
            instance.setZones(zones);
        } catch (ZoneException ex) {
            bool=true;
        }
        assertTrue(bool);
    }

    /**
     * Test of setTiles method, of class TileZone.
     * @throws fr.univubs.inf1603.mahjong.engine.ZoneException
     */
    @Test
    public void testSetTiles() throws ZoneException {
        System.out.println("setTiles");
        GameTile tile = new GameTile(0, new CommonTile(CommonTile.Family.CHARACTERS, CommonTile.Number.EIGHT));
        ArrayList<GameTile> tiles = new ArrayList<>();
        tiles.add(tile);
        TileZone instance = new TileZone("Test", true);
        instance.setTiles(tiles);
        assertTrue(tiles.equals(instance.getTiles()));
    }

    /**
     * Test of addZone method, of class TileZone.
     */
    @Test
    public void testAddZone(){
        System.out.println("addZone");
        boolean bool=false;
        GameZone zone = new TileZone("Test", true);
        TileZone instance = new TileZone("Test", true);
        try {
            instance.addZone(zone);
        } catch (ZoneException ex) {
            bool=true;
        }
        assertTrue(bool);
    }

    /**
     * Test of removeZone method, of class TileZone.
     */
    @Test
    public void testRemoveZone() {
        System.out.println("removeZone");
        boolean bool=false;
        GameZone zone = new TileZone("Test", true);
        TileZone instance = new TileZone("Test", true);
        try {
            instance.addZone(zone);
        } catch (ZoneException ex) {
            
        }
        try {
            instance.removeZone(zone);
        } catch (ZoneException ex) {
            bool=true;
        }
        assertTrue(bool);
    }

    /**
     * Test of addTile method, of class TileZone.
     * @throws fr.univubs.inf1603.mahjong.engine.ZoneException
     */
    @Test
    public void testAddTile() throws ZoneException{
        System.out.println("addTile");
        GameTile tile = new GameTile(0, new CommonTile(CommonTile.Family.CHARACTERS, CommonTile.Number.EIGHT));
        TileZone instance = new TileZone("Test",true);
        boolean expResult = true;
        boolean result = instance.addTile(tile);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeTile method, of class TileZone.
     * @throws fr.univubs.inf1603.mahjong.engine.ZoneException
     */
    @Test
    public void testRemoveTile() throws ZoneException {
        System.out.println("removeTile");
        GameTile tile = new GameTile(0, new CommonTile(CommonTile.Family.CHARACTERS, CommonTile.Number.EIGHT));
        ArrayList<GameTile> tiles = new ArrayList<>();
        tiles.add(tile);
        TileZone instance = new TileZone("Test", tiles, true,UUID.randomUUID());
        boolean expResult = true;
        boolean result = instance.removeTile(tile);
        assertEquals(expResult, result);
    }
    
}
