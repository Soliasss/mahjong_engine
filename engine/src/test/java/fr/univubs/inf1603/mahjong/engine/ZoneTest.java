/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author purpl
 */
public class ZoneTest {
    
    public ZoneTest() {
    }

    /**
     * Test of getTilesCollection method, of class Zone.
     */
    @Test
    public void testGetTilesCollection() {
        System.out.println("getTilesCollection");
        Zone instance = new ZoneImpl();
        ArrayList<AbstractTile> expResult = null;
        ArrayList<AbstractTile> result = instance.getTilesCollection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getZonesCollection method, of class Zone.
     */
    @Test
    public void testGetZonesCollection() {
        System.out.println("getZonesCollection");
        Zone instance = new ZoneImpl();
        ArrayList<Zone> expResult = null;
        ArrayList<Zone> result = instance.getZonesCollection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMoney method, of class Zone.
     */
    @Test
    public void testGetMoney() {
        System.out.println("getMoney");
        Zone instance = new ZoneImpl();
        int expResult = 0;
        int result = instance.getMoney();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ZoneImpl implements Zone {

        public ArrayList<AbstractTile> getTilesCollection() {
            return null;
        }

        public ArrayList<Zone> getZonesCollection() {
            return null;
        }

        public int getMoney() {
            return 0;
        }
    }
    
}
