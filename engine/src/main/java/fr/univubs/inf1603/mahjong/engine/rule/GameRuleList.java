/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine.rule;

/**
 * Class used to represent the multiple GameRule
 * @author maxime
 */
public class GameRuleList {
    
    /**
     * Return a array of gameRule 
     * @return the array of gameRule
     */
    public GameRule[] getRuleArray() {
        return Rule.values();
    }
    
    /**
     * Return the gameRule with the given name
     * @param name the name of the gameRule to search   
     * @return the gameRule
     */
    public GameRule getRule(String name) {
        GameRule ruleArray[] = Rule.values();
        GameRule ret = null;
        int i=0;
        boolean ok = false;
        while(i<ruleArray.length && !ok){
            if(ruleArray[i].getName().equals(name)){
                ret=ruleArray[i];
                ok=true;
            }
            i++;   
        }
        return ret;
        
    }
    
    /**
     * Return the gameRule with a given BoardRule and ScoringSystem
     * @param boardRule the boardRule of the gameRule to search
     * @param scoringSystem the scoringSystem of the gameRule to search
     * @return the gameRule
     */
    public GameRule getRule(BoardRule boardRule, ScoringSystem scoringSystem) {
        GameRule ruleArray[] = Rule.values();
        GameRule ret = null;
        int i=0;
        boolean ok = false;
        while(i<ruleArray.length && !ok){
            if(ruleArray[i].getBoardRule().equals(boardRule)){
                if(ruleArray[i].getScoringSystem().equals(scoringSystem)){
                    ret=ruleArray[i];
                    ok=true;
                }
            }
            i++;   
        }
        return ret;
    }
    
        
    /**
     * Enumeration of GameRule
     */
    public enum Rule implements GameRule{
        SILLY(new SillyBoardRule(),new InternationalScoringSystem(InternationalPatternList.DEFAULT),"SILLY"),
        INTERNATIONAL(new InternationalBoardRule(),new InternationalScoringSystem(InternationalPatternList.DEFAULT),"INTERNATIONAL");
        
        private BoardRule boardRule;
        private ScoringSystem scoringSystem;
        private String name;
        private String description;
        Rule(BoardRule boardRule, ScoringSystem scoringSystem,String name){
            this.boardRule = boardRule;
            this.scoringSystem = scoringSystem;
            this.name = name;
        }
        
        Rule(BoardRule boardRule, ScoringSystem scoringSystem){
            this.boardRule = boardRule;
            this.scoringSystem = scoringSystem;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public String getDescription() {
            return this.description;
        }

        @Override
        public ScoringSystem getScoringSystem() {
            return this.scoringSystem;
        }

        @Override
        public BoardRule getBoardRule() {
            return this.boardRule;
        }
    }

   
}

