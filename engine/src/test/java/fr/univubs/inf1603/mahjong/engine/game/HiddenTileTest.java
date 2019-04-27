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
 */
public class HiddenTileTest {

    /**
     * Test of toNormalizedName method, of class HiddenTile.
     */
    @Test
    public void testToNormalizedName() {
        System.out.println("toNormalizedName");
        HiddenTile instance = HiddenTile.HIDDENTILE;
        String expResult = "XX";
        String result = instance.toNormalizedName();
        assertEquals(expResult, result);
    }
    
}
