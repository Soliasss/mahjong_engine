/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.rule.GameRuleList.Rule;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author maxime
 */
public class GameRuleListTest {
    
    public GameRuleListTest() {
    }

    /**
     * Test of getRuleArray method, of class GameRuleList.
     */
    @Test
    public void testGetRuleArray() {
        System.out.println("getRuleArray");
        GameRuleList instance = new GameRuleList();
        GameRule[] expResult = {Rule.SILLY_RULE,Rule.INTERNATIONAL_RULE};
        GameRule[] result = instance.getRuleArray();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of getRule method, of class GameRuleList.
     */
    @Test
    public void testGetRule_String() {
        System.out.println("getRule");
        GameRule expResult = Rule.SILLY_RULE;
        String name = "SILLY_RULE";
        GameRuleList instance = new GameRuleList();
        GameRule result = instance.getRule(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of getRule method, of class GameRuleList.
     */
    @Test
    public void testGetRule_BoardRule_ScoringSystem() {
        System.out.println("getRule");
        GameRule expResult = Rule.INTERNATIONAL_RULE;
        BoardRule boardRule = expResult.getBoardRule();
        ScoringSystem scoringSystem = expResult.getScoringSystem();
        GameRuleList instance = new GameRuleList();
        
        GameRule result = instance.getRule(boardRule, scoringSystem);
        assertEquals(expResult, result);
    }
    
}
