package fr.univubs.inf1603.mahjong.engine;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Malléjac Clément
 */
public class WindHonorTest {
    /**
     * Test of getWind method, of class WindHonor.
     */
    @Test
    public void testGetWind() {
        System.out.println("getWind");
        WindHonor instance = new WindHonorImpl();
        WindHonor.Wind expResult = WindHonor.Wind.EAST;
        WindHonor.Wind result = instance.getWind();
        assertEquals(expResult, result);
    }

    public class WindHonorImpl extends WindHonor {

        public WindHonorImpl(){
            super(WindHonor.Wind.EAST);
        }

        @Override
        public String toNormalizedName() {
            return "ok";
        }
    }
    
}
