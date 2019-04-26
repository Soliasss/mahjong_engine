package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.GameTile;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class GameTileTest {
    /**
     * Test of getTile method, of class GameTile.
     */
    @Test
    public void testGetTile() {
        System.out.println("getTile");
        AbstractTile tile = new CommonTile(CommonTile.Family.BAMBOO, CommonTile.Number.NINE);
        GameTile instance = new GameTile(1,tile);
        AbstractTile expResult = tile;
        AbstractTile result = instance.getTile();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGameID method, of class GameTile.
     */
    @Test
    public void testGetGameID() {
        System.out.println("getGameID");
        AbstractTile tile = new CommonTile(CommonTile.Family.BAMBOO, CommonTile.Number.NINE);
        GameTile instance = new GameTile(1,tile);
        int expResult = 1;
        int result = instance.getGameID();
        assertEquals(expResult, result);
    }
    
}
