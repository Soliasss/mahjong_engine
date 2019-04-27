package fr.univubs.inf1603.mahjong.engine.rule;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class WindHonorTest {
    /**
     * Test of getWind method, of class WindHonor.
     */
    @Test
    public void testGetWind() {
        System.out.println("getWind");
        WindHonor instance = new WindHonorImpl();
        Wind expResult = Wind.EAST;
        Wind result = instance.getWind();
        assertEquals(expResult, result);
    }

    public class WindHonorImpl extends WindHonor {

        public WindHonorImpl(){
            super(Wind.EAST);
        }

        @Override
        public String toNormalizedName() {
            return "ok";
        }
    }
    
}
