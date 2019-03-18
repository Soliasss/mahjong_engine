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
public class MoneyZoneTest {
    
    public MoneyZoneTest() {
    }

    /**
     * Test of getTilesCollection method, of class MoneyZone.
     */
    @Test
    public void testGetTilesCollection() {
        System.out.println("getTilesCollection");
        MoneyZone instance = new MoneyZone();
        ArrayList<AbstractTile> expResult = null;
        ArrayList<AbstractTile> result = instance.getTilesCollection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getZonesCollection method, of class MoneyZone.
     */
    @Test
    public void testGetZonesCollection() {
        System.out.println("getZonesCollection");
        MoneyZone instance = new MoneyZone();
        ArrayList<Zone> expResult = null;
        ArrayList<Zone> result = instance.getZonesCollection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMoney method, of class MoneyZone.
     */
    @Test
    public void testGetMoney() {
        System.out.println("getMoney");
        MoneyZone instance = new MoneyZone();
        int expResult = 0;
        int result = instance.getMoney();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMoney method, of class MoneyZone.
     */
    @Test
    public void testAddMoney() {
        System.out.println("addMoney");
        int money = 0;
        MoneyZone instance = new MoneyZone();
        instance.addMoney(money);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeMoney method, of class MoneyZone.
     */
    @Test
    public void testRemoveMoney() throws Exception {
        System.out.println("removeMoney");
        int money = 0;
        MoneyZone instance = new MoneyZone();
        instance.removeMoney(money);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
