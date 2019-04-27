package fr.univubs.inf1603.mahjong.engine.rule;

/**
 * This interface represent the ability of its implementations to create or to give a reference of a GameRule
 */
public interface AbstractGameRuleFactory {
    /**
     * Creates or searches for a GameRule based on a given name
     * @param name Provided name
     * @return A GameRule found or created based on this provided name
     * @throws RulesException If the factory is unable to find or create a GameRule based on this provided name
     */
    public GameRule create(String name) throws RulesException;
    
    /**
     * Creates or searches for a GameRule based on a BoardRule and a ScoringSystem
     * @param boardRule 
     * @param scoringSystem
     * @return A GameRule 
     * @throws RulesException 
     */
    public GameRule create(BoardRule boardRule, ScoringSystem scoringSystem) throws RulesException;
}
