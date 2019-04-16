package fr.univubs.inf1603.mahjong.engine.rule;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 */
public interface ScoringSystem {
    /**
     * Each {@link ScoringSystem} has its own list of patterns, witch is an implementation of {@link AbstractPatternList}.
     * Since {@link AbstractPatternList} implementations have to be singletons,
     * we return the instance of the singleton.
     * @return a reference to the instance of the {@link AbstractPatternList}
     */
    AbstractPatternList getPatternList();

    /**
     * In order to compute scores, we need to know how the player wants its tile to be arranged.
     * We use this method to find all the possible hand arrangements from a situation.
     * <br>
     * <b>Example :</b><pre>situation.hand = {2b, 1b, 3b, 1b, 3b, 1b, 3b, 2b, 2b, [...]}
     * set1.hand = {[1b, 2b, 3b], [1b, 2b, 3b], [1b, 2b, 3b], [...]} <-- 3 chows
     * set2.hand = {[1b, 1b, 1b], [2b, 2b, 2b], [3b, 3b, 3b], [...]} <-- 3 pungs
     * </pre>
     *
     * @param situation situation where the player wants to call the mahjong
     * @return a collection of sets arranged from the situation
     */
    Collection<PlayerSet> createSetsFromSituation(PlayerSituation situation);

    /**
     * Given a specific tile arrangement, we can identify the patterns in it.
     * @param set a specific tile arrangement
     * @return a collection of all the pattern found in the set,
     * some patterns that cannot coexist could be listed
     */
    default Collection<IdentifiedPattern> identifyPatterns(PlayerSet set){
        ArrayList<IdentifiedPattern> result = new ArrayList<>();

        for (IdentifiablePattern pattern : getPatternList().getPatterns())
            result.addAll(pattern.identify(set));

        return result;
    }

    /**
     * Since some patterns cannot be called together, we need to split all the possible calls in different collections
     * @param patterns collection of patterns to split
     * @return collection of collections of non-exclusive patterns ordered by point value
     */
    Collection<Collection<IdentifiedPattern>> splitIncompatiblePatterns(Collection<IdentifiedPattern> patterns);

    /**
     *
     * @param patterns
     * @return
     */
    default int computeScore(Collection<IdentifiedPattern> patterns){
        int result = 0;

        for (IdentifiedPattern pattern : patterns)
            result += pattern.getPattern().getValue();

        return result;
    }
}
