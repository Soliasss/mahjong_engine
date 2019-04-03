package fr.univubs.inf1603.mahjong.engine;

import fr.univubs.inf1603.mahjong.engine.rule.SimpleHonor;
import fr.univubs.inf1603.mahjong.engine.rule.WindHonor;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Malléjac Clément
 */
public class SimpleHonorTest {
    /**
     * Test of toNormalizedName method, of class SimpleHonor.
     */
    @Test
    public void testToNormalizedName() {
        System.out.println("toNormalizedName");
        SimpleHonor instance = new SimpleHonor(WindHonor.Wind.WEST);
        String expResult = "Ww";
        String result = instance.toNormalizedName();
        assertEquals(expResult, result);
    }
    
}
