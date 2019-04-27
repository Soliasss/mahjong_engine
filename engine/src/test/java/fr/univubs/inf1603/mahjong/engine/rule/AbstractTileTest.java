package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.rule.AbstractTile;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
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
