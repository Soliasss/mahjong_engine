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
public class AbstractTileTest {
    
    public AbstractTileTest() {
    }

    /**
     * Test of toNormalizedName method, of class AbstractTile.
     */
    @Test
    public void testToNormalizedName() {
        System.out.println("toNormalizedName");
        AbstractTile instance = new AbstractTileImpl();
        String expResult = "ok";
        String result = instance.toNormalizedName();
        assertEquals(expResult, result);
    }

    public class AbstractTileImpl implements AbstractTile {
        @Override
        public String toNormalizedName() {
            return "ok";
        }
    }
    
}
