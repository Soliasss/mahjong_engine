package fr.univubs.inf1603.mahjong.engine.rule;

/**
 * Interface used to represent multiple {@link IdentifiablePattern IdentifiablePatterns}.
 * An implementation of this class should be a singleton
 */
public interface AbstractPatternList{
    /**
     * Getter on the patterns
     * @return the patterns of the list
     */
    IdentifiablePattern[] getPatterns();
}
