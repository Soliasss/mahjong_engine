/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine.game;

import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author purpl
 */
public class MoveTest{
    /**
     * Test of getSide method, of class Move.
     */
    @Test
    public void testGetSide() {
        System.out.println("getSide");
        Move instance = null;
        Side expResult = null;
        Side result = instance.getSide();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPriority method, of class Move.
     */
    @Test
    public void testGetPriority() {
        System.out.println("getPriority");
        Move instance = null;
        int expResult = 0;
        int result = instance.getPriority();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPath method, of class Move.
     */
    @Test
    public void testGetPath() {
        System.out.println("getPath");
        Move instance = null;
        HashMap<Integer, MahjongTileZone> expResult = null;
        HashMap<Integer, MahjongTileZone> result = instance.getPath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
