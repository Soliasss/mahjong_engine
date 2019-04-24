/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.MahjongBoard;
import fr.univubs.inf1603.mahjong.engine.game.Move;
import fr.univubs.inf1603.mahjong.engine.rule.GameRuleList.Rule;
import java.util.Collection;
import java.util.EnumMap;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author maxime
 */
public class GameRuleFactoryTest {
    
    public GameRuleFactoryTest() {
    }

    /**
     * Test of create method, of class GameRuleFactory.
     */
    @Test
    public void testCreate_String_Search() throws Exception {
        System.out.println("create");
        String name = "SILLY_RULE";
        GameRuleFactory instance = new GameRuleFactory();
        GameRule expResult = Rule.SILLY_RULE;
        GameRule result = instance.create(name);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of create method, of class GameRuleFactory.
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
     */
    @Test
    public void testCreate_BoardRule_ScoringSystem() throws Exception {
        System.out.println("create");
        GameRule expResult = Rule.INTERNATIONAL_RULE;
        String expName = "INTERNATIONAL_RULE";
        BoardRule boardRule = expResult.getBoardRule() ;
        ScoringSystem scoringSystem = expResult.getScoringSystem();
        GameRuleFactory instance = new GameRuleFactory();
        GameRule result = instance.create(boardRule, scoringSystem);
        assertEquals(expResult, result);
        assertEquals(expName, result.getName());
    }
    
    /**
     * Test of create method, of class GameRuleFactory.
     */
    @Test
    public void testCreate_BoardRule_ScoringSystem_Create() throws Exception {
        System.out.println("create");
        BoardRule boardRule = new TestBoardRule();
        ScoringSystem scoringSystem = new InternationalScoringSystem(InternationalPatternList.DEFAULT);
        GameRule expResult = new GameRuleImpl(boardRule, scoringSystem);
        GameRuleFactory instance = new GameRuleFactory();
        GameRule result = instance.create(boardRule, scoringSystem);
        assertEquals(expResult.getBoardRule(), result.getBoardRule());
        assertEquals(expResult.getScoringSystem(), result.getScoringSystem());

    }

    private static class TestBoardRule implements BoardRule {

        public TestBoardRule() {
        }

        @Override
        public Wind[] getPlayerOrder() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public StartingWall buildWall() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public MahjongBoard distributeTiles(StartingWall startingWall) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isMoveValid(MahjongBoard board, Move lastMove, Move move) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public EnumMap<Wind, Collection<Move>> findValidMoves(MahjongBoard board, Move lastMove) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isGameFinished(MahjongBoard board, Move lastMove) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
    
    
    
}
