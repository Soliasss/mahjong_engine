/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine.rule;

import org.apache.log4j.Logger;

/**
 * Implementation of AbstractGameRuleFactory
 */
public class GameRuleFactory implements AbstractGameRuleFactory{

    private GameRuleList ruleList = new GameRuleList();
    
        // Logger 
    private static final Logger LOGGER = Logger.getLogger(GameRuleFactory.class.getName());

    
    @Override
    public GameRule create(String name) throws RulesException {
        LOGGER.info("Debut methode.");
        LOGGER.debug("name : " + name );
        GameRule ret;
        ret=this.ruleList.getRule(name);
        if(ret==null){
            throw new RulesException("This GameRule not exist");
        }
        LOGGER.debug("ret.getName() : " + ret.getName() );
        LOGGER.info("Fin methode.");
        return ret;
    }

    @Override
    public GameRule create(BoardRule boardRule, ScoringSystem scoringSystem) throws RulesException {
        GameRule ret;
        ret=this.ruleList.getRule(boardRule, scoringSystem);
        if(ret==null){
            ret=new GameRuleImpl(boardRule, scoringSystem);
        }
        return ret;
            
    }

   
}
