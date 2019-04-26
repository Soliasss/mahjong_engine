package fr.univubs.inf1603.mahjong.engine.rule;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class FlowerTileTest {
    @Test
    public void testGetFlower() {
        System.out.println("getFlower");
        FlowerTile instance = new FlowerTile(FlowerTile.Flower.PLUM);
        FlowerTile.Flower expResult = FlowerTile.Flower.PLUM;
        FlowerTile.Flower result = instance.getFlower();
        assertEquals(expResult, result);
    }

    /**
     * Test of toNormalizedName method, of class FlowerTile.
     */
    @Test
    public void testToNormalizedName() {
        System.out.println("toNormalizedName");
        FlowerTile instance = new FlowerTile(FlowerTile.Flower.PLUM);
        String expResult = "F1";
        String result = instance.toNormalizedName();
        assertEquals(expResult, result);
    }    
}
