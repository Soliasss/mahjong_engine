/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine.game;

import fr.univubs.inf1603.mahjong.engine.rule.Wind;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        Wind expResult = Wind.EAST;
        HashMap path = new HashMap<>();
        path.put(0, TileZoneIdentifier.Wall);
        Move instance=null;
        try {
            instance = new Move(expResult, 0, path);
        } catch (MoveException ex) {
            fail("Move threw an exception: "+ex);
        }
        Wind result = instance.getWind();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPriority method, of class Move.
     */
    @Test
    public void testGetPriority() {
        System.out.println("getPriority");
        int expResult = 5;
        HashMap path = new HashMap<>();
        path.put(0, TileZoneIdentifier.Wall);
        Move instance=null;
        try {
            instance = new Move(Wind.EAST, expResult, path);
        } catch (MoveException ex) {
            fail("Move threw an exception: "+ex);
        }
        int result = instance.getPriority();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPath method, of class Move.
     */
    @Test
    public void testGetPath() {
        System.out.println("getPath");
        HashMap<Integer, TileZoneIdentifier> expResult = new HashMap<>();
        expResult.put(0, TileZoneIdentifier.Wall);
        Move instance=null;
        try {
            instance = new Move(Wind.WEST, 0, expResult);
        } catch (MoveException ex) {
            fail("Move threw an exception: "+ex);
        }
        HashMap<Integer, TileZoneIdentifier> result = instance.getPath();
        assertEquals(expResult, result);
    }
    
}
