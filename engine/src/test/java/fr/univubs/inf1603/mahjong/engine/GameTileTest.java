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
public class GameTileTest {
    
    public GameTileTest() {
    }

    /**
     * Test of getTile method, of class GameTile.
     */
    @Test
    public void testGetTile() {
        System.out.println("getTile");
        GameTile instance = null;
        AbstractTile expResult = null;
        AbstractTile result = instance.getTile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGameID method, of class GameTile.
     */
    @Test
    public void testGetGameID() {
        System.out.println("getGameID");
        GameTile instance = null;
        int expResult = 0;
        int result = instance.getGameID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
