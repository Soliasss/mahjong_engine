package fr.univubs.inf1603.mahjong.engine.rule;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class SeasonTileTest {
    /**
     * Test of getSeason method, of class SeasonTile.
     */
    @Test
    public void testGetSeason() {
        System.out.println("getSeason");
        SeasonTile instance = new SeasonTile(SeasonTile.Season.SPRING);
        SeasonTile.Season expResult = SeasonTile.Season.SPRING;
        SeasonTile.Season result = instance.getSeason();
        assertEquals(expResult, result);
    }

    /**
     * Test of toNormalizedName method, of class SeasonTile.
     */
    @Test
    public void testToNormalizedName() {
        System.out.println("toNormalizedName");
        SeasonTile instance = new SeasonTile(SeasonTile.Season.SPRING);
        String expResult = "S1";
        String result = instance.toNormalizedName();
        assertEquals(expResult, result);
    }    
}
