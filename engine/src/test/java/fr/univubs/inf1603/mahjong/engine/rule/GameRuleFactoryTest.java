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
 */
public class GameRuleFactoryTest {
    
    public GameRuleFactoryTest() {
    }

    /**
     * Test of create method, of class GameRuleFactory.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreate_String_Search() throws Exception {
        System.out.println("create");
        String name = "INTERNATIONAL";
        GameRuleFactory instance = new GameRuleFactory();
        GameRule expResult = Rule.INTERNATIONAL;
        GameRule result = instance.create(name);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of create method, of class GameRuleFactory.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreate_String_Create() throws Exception {
        System.out.println("create");
        String expName = "TEST_RULE";
        GameRuleFactory instance = new GameRuleFactory();
        try {
            instance.create(expName);
            fail("Exception not thrown");
        } catch (RulesException e) {
        }
        

        
    }

    /**
     * Test of create method, of class GameRuleFactory.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreate_BoardRule_ScoringSystem() throws Exception {
        System.out.println("create");
        GameRule expResult = Rule.INTERNATIONAL;
        String expName = "INTERNATIONAL";
        BoardRule boardRule = expResult.getBoardRule() ;
        ScoringSystem scoringSystem = expResult.getScoringSystem();
        GameRuleFactory instance = new GameRuleFactory();
        GameRule result = instance.create(boardRule, scoringSystem);
        assertEquals(expResult, result);
        assertEquals(expName, result.getName());
    }
    
    
    
    
    
    
}
