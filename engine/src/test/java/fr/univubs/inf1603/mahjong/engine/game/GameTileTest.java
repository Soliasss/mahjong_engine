/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine.game;

import fr.univubs.inf1603.mahjong.engine.rule.AbstractTile;
import java.beans.PropertyChangeSupport;
import java.util.UUID;
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

    /**
     * Test of getUUID method, of class GameTile.
     */
    @Test
    public void testGetUUID() {
        System.out.println("getUUID");
        GameTile instance = null;
        UUID expResult = null;
        UUID result = instance.getUUID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clone method, of class GameTile.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        GameTile instance = null;
        GameTile expResult = null;
        GameTile result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTile method, of class GameTile.
     */
    @Test
    public void testSetTile() {
        System.out.println("setTile");
        AbstractTile newTile = null;
        GameTile instance = null;
        instance.setTile(newTile);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class GameTile.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        GameTile instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPropertyChangeSupport method, of class GameTile.
     */
    @Test
    public void testGetPropertyChangeSupport() {
        System.out.println("getPropertyChangeSupport");
        GameTile instance = null;
        PropertyChangeSupport expResult = null;
        PropertyChangeSupport result = instance.getPropertyChangeSupport();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
