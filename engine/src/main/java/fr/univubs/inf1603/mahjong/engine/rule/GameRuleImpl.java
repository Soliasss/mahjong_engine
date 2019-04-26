/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine.rule;

/**
 * Implemantation of GameRule
 */
public class GameRuleImpl implements GameRule {

    private BoardRule boardRule;
    private ScoringSystem scoringSystem;
    private String name;
    private String description;
        
    /**
    * Constructor of GameRuleImpl with a boardRule, scoringSystem and a name
    * @param boardRule the boardRule of the rule
    * @param scoringSystem the scoringSystem of the rule
    * @param name the name of the rule
    */
    GameRuleImpl(BoardRule boardRule, ScoringSystem scoringSystem,String name){
        this.boardRule = boardRule;
        this.scoringSystem = scoringSystem;
        this.name = name;
    }
        
    /**
    * Constructor of GameRuleImpl with a boardRule and a scoringSystem
    * @param boardRule
    * @param scoringSystem 
    */
    GameRuleImpl(BoardRule boardRule, ScoringSystem scoringSystem){
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


