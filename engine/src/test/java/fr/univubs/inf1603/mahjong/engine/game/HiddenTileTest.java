/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine.game;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author purpl
 */
public class HiddenTileTest {
    
    public HiddenTileTest() {
    }

    /**
     * Test of toNormalizedName method, of class HiddenTile.
     */
    @Test
    public void testToNormalizedName() {
        System.out.println("toNormalizedName");
        HiddenTile instance = null;
        String expResult = "";
        String result = instance.toNormalizedName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
