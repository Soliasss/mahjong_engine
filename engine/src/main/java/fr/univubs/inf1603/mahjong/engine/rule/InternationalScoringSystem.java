package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.GameTile;

import java.util.ArrayList;
import java.util.Collection;

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
     * @param toArrange hand to process
     * @return all the possible usable arrangements of the hand
     */
    Collection<Collection<Combination>> findMultipleHandArrangements(Collection<GameTile> toArrange) {
        Collection<Collection<Combination>> arrangements = new ArrayList<>();

        arrangements.addAll(normalHandArrangements(toArrange));
        arrangements.addAll(sevenPairsHandArrangement(toArrange));
//        TODO: all type of grouping


        /*
            remove unusable arrangements
            not sure if it should be used here or in sub-method
         */
        int handSize;
        for (Collection<Combination> arrangement : arrangements) {
            handSize = 0;
            for (Combination combination : arrangement)
                handSize += combination.getTiles().length;

            if (handSize < toArrange.size())
                arrangements.remove(arrangement);
        }

        return arrangements;
    }

    /**
     * Find all the "normal" arrangements in a hand, a normal arrangement has {@link Pung Pungs}, {@link Chow Chows}
     * and a {@link Pair}.
     * @param toArrange hand to process
     * @return all the possible normal arrangements of the hand. As of now, this method does not guarantee the return of
     * usable arrangements in a mahjong (some arrangements can be smaller than the hand), you should use
     * {@link InternationalScoringSystem#findMultipleHandArrangements(Collection) findMultipleHandArrangements} for that.
     */
    Collection<Collection<Combination>> normalHandArrangements(Collection<GameTile> toArrange) {
        ArrayList<Collection<Combination>> arrangements = new ArrayList<>();
        ArrayList<GameTile> rest = new ArrayList<>();
        ArrayList<Combination> currArrangement = new ArrayList<>();

        if (toArrange.size() > 2) {
            Collection<Combination> allTriples = findAllTriples(toArrange);
            for (Combination triple : allTriples) {
                rest.clear();
                rest.addAll(toArrange);

                for (GameTile gameTile : triple.getTiles()) {
                    rest.remove(gameTile);
                }

                Collection<Collection<Combination>> arrangementsLeft = normalHandArrangements(rest);

                if (arrangementsLeft.isEmpty()){
                    currArrangement.clear();
                    currArrangement.add(triple);
                    arrangements.add(new ArrayList<>(currArrangement));
                } else {
                    for (Collection<Combination> otherCombinations :
                            arrangementsLeft) {
                        currArrangement.clear();
                        currArrangement.add(triple);
                        currArrangement.addAll(otherCombinations);
                        arrangements.add(new ArrayList<>(currArrangement));
                    }
                }
            }
        }

        if (toArrange.size() == 2) {
            try {
                currArrangement.clear();
                currArrangement.add(new Pair((GameTile[]) toArrange.toArray()));
                arrangements.add(new ArrayList<>(currArrangement));
            } catch (RulesException ignored) {
            }
        }

        return arrangements;
    }

    /**
     * Construct all the possible 3-tiled {@link Combination Combinations} ({@link Pung Pungs} and {@link Chow Chows})
     * using a collection of {@link fr.univubs.inf1603.mahjong.engine.game.GameTile GameTiles}.
     * @param toSearch {@link fr.univubs.inf1603.mahjong.engine.game.GameTile GameTiles} we want to find triples in
     * @return all the possible 3-tiled {@link Combination Combinations} buildable
     */
    Collection<Combination> findAllTriples(Collection<GameTile> toSearch) {
        ArrayList<Combination> allTriples = new ArrayList<>();
        CombinationFactory factory = new CombinationFactory();


        for (GameTile tile1 : toSearch)
            for (GameTile tile2 : toSearch)
                for (GameTile tile3 : toSearch)
                    try {
                        allTriples.add(factory.newCombination(tile1, tile2, tile3));
                    } catch (RulesException ignored) {}

        return allTriples;
    }

    Collection<Collection<Combination>> sevenPairsHandArrangement(Collection<GameTile> toArrange) {
        return null;
    }

    @Override
    public Collection<Collection<IdentifiedPattern>> splitIncompatiblePatterns(Collection<IdentifiedPattern> patterns) {
        return null;
    }
}
