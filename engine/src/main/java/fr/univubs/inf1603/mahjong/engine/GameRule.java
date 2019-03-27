package fr.univubs.inf1603.mahjong.engine;

/**
 * Grouping interface for {@link ScoringSystem} and {@link BoardRule}.
 * All rules should implement this interface since all rules have to fulfil these two contracts.
 * This choice was made because not all the methods from the both parent interfaces are needed to perform
 * simpler operations. For example, we don't need {@link BoardRule BoardRule's} methods to compute
 * the score of a hand with {@link ScoringSystem ScoringSystem's} methods. Additionally,
 * all the implementations of this interface should be singletons
 */
public interface GameRule extends ScoringSystem, BoardRule {}
