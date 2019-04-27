package fr.univubs.inf1603.mahjong.engine.rule;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class CommonTileTest {
    /**
     * Test of getFamily method, of class CommonTile.
     */
    @Test
    public void testGetFamily() {
        System.out.println("getFamily");
        CommonTile instance = new CommonTile(CommonTile.Family.BAMBOO, CommonTile.Number.NINE);
        CommonTile.Family expResult = CommonTile.Family.BAMBOO;
        CommonTile.Family result = instance.getFamily();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumber method, of class CommonTile.
     */
    @Test
    public void testGetNumber() {
        System.out.println("getNumber");
        CommonTile instance = new CommonTile(CommonTile.Family.BAMBOO, CommonTile.Number.NINE);
        CommonTile.Number expResult = CommonTile.Number.NINE;
        CommonTile.Number result = instance.getNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of isMajor method, of class CommonTile.
     */
    @Test
    public void testIsMajor() {
        System.out.println("isMajor");
        CommonTile instance1 = new CommonTile(CommonTile.Family.BAMBOO, CommonTile.Number.NINE);
        CommonTile instance2 = new CommonTile(CommonTile.Family.DOT, CommonTile.Number.ONE);
        CommonTile instance3 = new CommonTile(CommonTile.Family.CHARACTER, CommonTile.Number.TWO);
        CommonTile instance4 = new CommonTile(CommonTile.Family.BAMBOO, CommonTile.Number.EIGHT);

        boolean expResult = true;
        boolean result = instance1.isMajor();
                assertEquals(expResult, result);        
                
                expResult = true;
                result = instance2.isMajor();
                assertEquals(expResult, result);        
                
                expResult = false;
                result = instance3.isMajor();
                assertEquals(expResult, result);        
                
                expResult = false;
                result = instance4.isMajor();
                assertEquals(expResult, result);        
    }

    /**
     * Test of toNormalizedName method, of class CommonTile.
     */
    @Test
    public void testToNormalizedName() {
        System.out.println("toNormalizedName");
        CommonTile instance = new CommonTile(CommonTile.Family.BAMBOO, CommonTile.Number.NINE);
        String expResult = "b9";
        String result = instance.toNormalizedName();
        assertEquals(expResult, result);
    }
    
}
