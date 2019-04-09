package fr.univubs.inf1603.mahjong.engine.rule;

/**
 * Interface representing the notion of a rule in its broadest sense.
 * All rules will implement this interface.
 * Additionally, all the implementations of this interface should be singletons
 */
public interface GameRule {

    /**
     * @return the name of the rule
     */
    String getName();

    /**
     * @return the description of the rule
     */
    String getDescirption();

    /**
     * @return a reference to the {@link ScoringSystem} of the rule
     */
    ScoringSystem getScoringSystem();

    /**
     * @return a reference to the {@link BoardRule} of the rule
     */
    BoardRule getBoardRule();
}
