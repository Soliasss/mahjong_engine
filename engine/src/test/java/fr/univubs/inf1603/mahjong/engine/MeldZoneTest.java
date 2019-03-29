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
public class MeldZoneTest {
    
    public MeldZoneTest() {
    }

    /**
     * Test of add method, of class MeldZone.
     * @throws fr.univubs.inf1603.mahjong.engine.ZoneException
     */
    @Test
    public void testAdd() throws ZoneException {
        System.out.println("add");
        GameTile tile = new GameTile(0, new CommonTile(CommonTile.Family.CHARACTERS, CommonTile.Number.EIGHT));
        GameTile addTile = new GameTile(0, new CommonTile(CommonTile.Family.BAMBOO, CommonTile.Number.NINE));
        ArrayList<GameTile> content = new ArrayList<>();
        content.add(tile);
        MeldZone instance = new MeldZone(Meld.KONG,content, true, UUID.randomUUID());
        boolean expResult = true;
        boolean result = instance.addTile(addTile, Meld.CHOW);
        assertEquals(expResult, result);
    }

    /**
     * Test of remove method, of class MeldZone.
     * @throws fr.univubs.inf1603.mahjong.engine.ZoneException
     */
    @Test
    public void testRemove() throws ZoneException {
        System.out.println("remove");
        GameTile tile = new GameTile(0, new CommonTile(CommonTile.Family.CHARACTERS, CommonTile.Number.EIGHT));
        ArrayList<GameTile> content = new ArrayList<>();
        content.add(tile);
        MeldZone instance = new MeldZone(Meld.KONG,content, true, UUID.randomUUID());
        boolean expResult = true;
        boolean result = instance.removeTile(tile, Meld.PONG);
        assertEquals(expResult, result);
    }
    
}
