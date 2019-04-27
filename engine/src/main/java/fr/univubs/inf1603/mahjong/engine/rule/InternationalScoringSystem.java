package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.GameTile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class InternationalScoringSystem implements ScoringSystem {
    public final static InternationalScoringSystem DEFAULT = new InternationalScoringSystem(InternationalPatternList.DEFAULT);

    private AbstractPatternList patternList;

    public InternationalScoringSystem(InternationalPatternList patternList) {
        this.patternList = patternList;
    }

    @Override
    public AbstractPatternList getPatternList() {
        return this.patternList;
    }

    @Override
    public Collection<PlayerSet> createSetsFromSituation(PlayerSituation situation) {
        ArrayList<PlayerSet> sets = new ArrayList<>();
        ArrayList<GameTile> handToProcess = new ArrayList<>(situation.getHand());
        handToProcess.add(situation.getWinningTile());

        Collection<Collection<Combination>> foundHands = findMultipleHandArrangements(handToProcess);

        for (Collection<Combination> currFoundHand : foundHands) {
            sets.add(new PlayerSet(situation, currFoundHand));
        }

        return sets;
    }

    /**
     * Find all the arrangements of a hand using all the groupings in the international ruleset
     *
     * @param toArrange hand to process
     * @return all the possible usable arrangements of the hand
     */
    Collection<Collection<Combination>> findMultipleHandArrangements(Collection<GameTile> toArrange) {
        Collection<Collection<Combination>> arrangements = new HashSet<>();

        arrangements.addAll(normalHandArrangements(toArrange));
        if (toArrange.size() == 14){
            arrangements.addAll(sevenPairsHandArrangement(toArrange));
        }
//        arrangements.addAll(otherArrangement);

        return arrangements;
    }

    /**
     * Find all the "normal" arrangements in a hand, a normal arrangement has {@link Pung Pungs}, {@link Chow Chows}
     * and a {@link Pair}.
     *
     * @param toArrange hand to process
     * @return all the possible normal arrangements of the hand.
     * {@link InternationalScoringSystem#findMultipleHandArrangements(Collection) findMultipleHandArrangements} for that.
     */
    Collection<Collection<Combination>> normalHandArrangements(Collection<GameTile> toArrange) {
        Collection<Collection<Combination>> arrangements = new HashSet<>();
        Collection<GameTile> rest = new HashSet<>();
        Collection<Combination> currArrangement = new HashSet<>();
        Collection<Collection<Combination>> arrangementsLeft;

        if (toArrange.size() <= 14) {
            if (toArrange.size() > 2) {
                Collection<Combination> allTriples = findAllTriples(toArrange);
                for (Combination triple : allTriples) {
                    rest.clear();
                    rest.addAll(toArrange);

                    for (GameTile gameTile : triple.getTiles())
                        rest.remove(gameTile);

                    arrangementsLeft = normalHandArrangements(rest);

                    arrangements.addAll(ScoringSystem.newCollectionsWithAddedObject(arrangementsLeft, triple));
                }
            }

            if (toArrange.size() == 2) {
                try {
                    GameTile[] pair = new GameTile[toArrange.size()];
                    currArrangement.clear();
                    currArrangement.add(new Pair(toArrange.toArray(pair)));
                    arrangements.add(new ArrayList<>(currArrangement));
                } catch (RulesException ignored) {
                }
            }
        }

        arrangements.removeIf(arrangement -> {
            int arrangementSize = 0;
            for (Combination combination : arrangement) {
                arrangementSize += combination.getTiles().length;
            }
            return arrangementSize < toArrange.size();
        });

        return arrangements;
    }

    /**
     * Construct all the possible 3-tiled {@link Combination Combinations} ({@link Pung Pungs} and {@link Chow Chows})
     * using a collection of {@link fr.univubs.inf1603.mahjong.engine.game.GameTile GameTiles}.
     *
     * @param toSearch {@link fr.univubs.inf1603.mahjong.engine.game.GameTile GameTiles} we want to find triples in
     * @return all the possible 3-tiled {@link Combination Combinations} buildable
     */
    Collection<Combination> findAllTriples(Collection<GameTile> toSearch) {
        Collection<Combination> allTriples = new HashSet<>();
        AbstractCombinationFactory factory = new InternationalCombinationFactory();

        for (GameTile tile1 : toSearch)
            for (GameTile tile2 : toSearch)
                for (GameTile tile3 : toSearch)
                    try {
                        allTriples.add(factory.newCombination(tile1, tile2, tile3));
                    } catch (RulesException ignored) {
                    }

        return allTriples;
    }

    /**
     * Find all the "seven pairs" arrangements in a hand
     *
     * @param toArrange hand to process
     * @return all the possible normal arrangements of the hand.
     * {@link InternationalScoringSystem#findMultipleHandArrangements(Collection) findMultipleHandArrangements} for that.
     */
    Collection<Collection<Combination>> sevenPairsHandArrangement(Collection<GameTile> toArrange) {
        Collection<Collection<Combination>> arrangements = new HashSet<>();
        Collection<GameTile> rest = new HashSet<>();
        Collection<Collection<Combination>> arrangementsLeft;

        Collection<Combination> allPairs = findAllPairs(toArrange);
        if (toArrange.size() <= 14) {
            for (Combination pair : allPairs) {
                rest.clear();
                rest.addAll(toArrange);

                for (GameTile gameTile : pair.getTiles())
                    rest.remove(gameTile);

                arrangementsLeft = sevenPairsHandArrangement(rest);

                arrangements.addAll(ScoringSystem.newCollectionsWithAddedObject(arrangementsLeft, pair));
            }
        }

        return arrangements;
    }

    Collection<Combination> findAllPairs(Collection<GameTile> toArrange) {
        Collection<Combination> allPairs = new HashSet<>();
        AbstractCombinationFactory factory = new InternationalCombinationFactory();

        for (GameTile tile1 : toArrange)
            for (GameTile tile2 : toArrange)
                try {
                    allPairs.add(factory.newCombination(tile1, tile2));
                } catch (RulesException ignored) {
                }

        return allPairs;
    }

    @Override
    public Collection<Collection<IdentifiedPattern>> splitIncompatiblePatterns(Collection<IdentifiedPattern> patterns) {
        Collection<IdentifiedPattern> rest = new HashSet<>();
        Collection<Collection<IdentifiedPattern>> splittedPatterns = new HashSet<>();
        Collection<Collection<IdentifiedPattern>> splittedPatternsLeft;

        for (IdentifiedPattern currPattern : patterns) {
            rest.clear();
            rest.addAll(patterns);

            if (currPattern.getPattern() != InternationalPatterns.LAST_TILE_DRAW &&
                currPattern.getPattern() != InternationalPatterns.SELF_DRAW)

                rest.removeIf(pattern -> {
                    for (GameTile tile : currPattern.getTiles())
                        if (pattern.getTiles().contains(tile))
                            return true;

                    return false;
                });

            splittedPatternsLeft = splitIncompatiblePatterns(rest);

            splittedPatterns.addAll(ScoringSystem.newCollectionsWithAddedObject(splittedPatternsLeft, currPattern));
        }

        return splittedPatterns;
    }
}
