package fr.univubs.inf1603.mahjong.engine.game;

import fr.univubs.inf1603.mahjong.engine.rule.AbstractTile;
import fr.univubs.inf1603.mahjong.engine.rule.InternationalTiles;
import java.beans.PropertyChangeSupport;
import java.util.UUID;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author purpl
 */
public class GameTileTest {

    /**
     * Test of getTile method, of class GameTile.
     */
    @Test
    public void testGetTile() {
        System.out.println("getTile");
        GameTile instance = new GameTile(1, InternationalTiles.BAMBOO_1);
        AbstractTile expResult = InternationalTiles.BAMBOO_1;
        AbstractTile result = instance.getTile();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGameID method, of class GameTile.
     */
    @Test
    public void testGetGameID() {
        System.out.println("getGameID");
        GameTile instance = new GameTile(1, InternationalTiles.BAMBOO_1);
        int expResult = 1;
        int result = instance.getGameID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUUID method, of class GameTile.
     */
    @Test
    public void testGetUUID() {
        System.out.println("getUUID");
        UUID u = UUID.randomUUID();
        GameTile instance = new GameTile(0, InternationalTiles.BAMBOO_1, u);
        UUID expResult = u;
        UUID result = instance.getUUID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTile method, of class GameTile.
     */
    @Test
    public void testSetTile() {
        System.out.println("setTile");
        AbstractTile newTile = InternationalTiles.BAMBOO_1;
        GameTile instance = new GameTile(0, InternationalTiles.DOT_1);
        instance.setTile(newTile);
        AbstractTile result = instance.getTile();
        assertEquals(newTile, result);
    }

    /**
     * Test of toString method, of class GameTile.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        GameTile instance = new GameTile(0, InternationalTiles.DOT_1);
        String expResult = "0:d1";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPropertyChangeSupport method, of class GameTile.
     */
    @Test
    public void testGetPropertyChangeSupport() {
        System.out.println("getPropertyChangeSupport");
        GameTile instance = new GameTile(0, InternationalTiles.BAMBOO_1);
        PropertyChangeSupport result = instance.getPropertyChangeSupport();
        assertNotNull(result);
    }
    
}
