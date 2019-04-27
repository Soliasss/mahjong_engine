package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.GameTile;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

class InternationalScoringSystemTest {

    @Test
    void createSetsFromSituation() {
        System.out.println("createSetsFromSituationTest");

        AbstractCombinationFactory factory = new InternationalCombinationFactory();

        Collection<GameTile> hand = new HashSet<>();
        hand.add(new GameTile(1, InternationalTiles.BAMBOO_1));
        hand.add(new GameTile(2, InternationalTiles.BAMBOO_1));
        hand.add(new GameTile(3, InternationalTiles.BAMBOO_1));
        hand.add(new GameTile(4, InternationalTiles.BAMBOO_2));
        hand.add(new GameTile(5, InternationalTiles.BAMBOO_2));
        hand.add(new GameTile(6, InternationalTiles.BAMBOO_2));
        hand.add(new GameTile(7, InternationalTiles.BAMBOO_3));
        hand.add(new GameTile(8, InternationalTiles.BAMBOO_3));
        hand.add(new GameTile(9, InternationalTiles.BAMBOO_3));

        hand.add(new GameTile(10, InternationalTiles.BAMBOO_1));

        Collection<GameTile> supreme = new HashSet<>();
        supreme.add(new GameTile(56, InternationalTiles.SEASON_AUTUMN));
        supreme.add(new GameTile(44, InternationalTiles.FLOWER_BAMBOO));

        Collection<Combination> melds = new HashSet<>();
        try {
            melds.add(factory.newCombination(
                    new GameTile(12, InternationalTiles.DOT_1),
                    new GameTile(13, InternationalTiles.DOT_2),
                    new GameTile(14, InternationalTiles.DOT_3)));
        } catch (RulesException e) {
            Assert.fail("bad combination in melds");
        }

        GameTile winningTile = new GameTile(11, InternationalTiles.BAMBOO_2);


        PlayerSituation playerSituation = new PlayerSituation(
                winningTile,
                hand,
                new HashSet<>(),
                melds,
                supreme,
                false,
                true,
                Wind.EAST,
                Wind.EAST);

        Collection<PlayerSet> sets = InternationalScoringSystem.DEFAULT.createSetsFromSituation(playerSituation);

        int nbOfSet = (4 * 4 * 3);
        Assert.assertEquals(nbOfSet, sets.size(), 0);

        int handSize;
        for (PlayerSet set : sets) {
            handSize = 0;
            for (Combination combination : set.getHand())
                handSize += combination.getTiles().length;

            Assert.assertEquals(hand.size() + 1, handSize, 0);

            Assert.assertEquals(winningTile, set.getWinningTile());
            Assert.assertTrue(set.getConcealed().isEmpty());
            Assert.assertEquals(melds, set.getMelds());
            Assert.assertEquals(supreme, set.getSupremeHonors());
            Assert.assertFalse(set.isDrawnForWall());
            Assert.assertTrue(set.isTakenFromDiscard());
            Assert.assertEquals(Wind.EAST, set.getPlayerWind());
            Assert.assertEquals(Wind.EAST, set.getRoundWind());
        }
    }

    @Test
    void findMultipleHandArrangements() {
        System.out.println("findMultipleHandArrangementsTest");
        ArrayList<GameTile> gameTiles = new ArrayList<>();
        gameTiles.add(new GameTile(1, InternationalTiles.BAMBOO_1));
        gameTiles.add(new GameTile(2, InternationalTiles.BAMBOO_1));
        gameTiles.add(new GameTile(3, InternationalTiles.BAMBOO_2));
        gameTiles.add(new GameTile(4, InternationalTiles.BAMBOO_2));
        gameTiles.add(new GameTile(5, InternationalTiles.BAMBOO_3));
        gameTiles.add(new GameTile(6, InternationalTiles.BAMBOO_3));
        gameTiles.add(new GameTile(7, InternationalTiles.BAMBOO_4));
        gameTiles.add(new GameTile(8, InternationalTiles.BAMBOO_4));
        gameTiles.add(new GameTile(9, InternationalTiles.BAMBOO_5));
        gameTiles.add(new GameTile(10, InternationalTiles.BAMBOO_5));
        gameTiles.add(new GameTile(11, InternationalTiles.BAMBOO_6));
        gameTiles.add(new GameTile(12, InternationalTiles.BAMBOO_6));

        gameTiles.add(new GameTile(13, InternationalTiles.BAMBOO_1));
        gameTiles.add(new GameTile(14, InternationalTiles.BAMBOO_1));

        Collection<Collection<Combination>> arrangements =
                InternationalScoringSystem.DEFAULT.findMultipleHandArrangements(gameTiles);

        int nbOfArrangements = 3 + 6 * (2 * (2 * 2 * 2));

        for (Collection<Combination> combinations :
                arrangements) {
            System.out.print("{ ");
            for (Combination combination :
                    combinations) {
                System.out.print(Arrays.toString(combination.getTiles()) + " ");
            }
            System.out.println("}");
        }

        Assert.assertEquals(nbOfArrangements, arrangements.size(), 0);
    }

    @Test
    void normalHandArrangements() {
        System.out.println("normalHandArrangementsTest");
        ArrayList<GameTile> gameTiles = new ArrayList<>();
        gameTiles.add(new GameTile(1, InternationalTiles.BAMBOO_1));
        gameTiles.add(new GameTile(2, InternationalTiles.BAMBOO_1));
        gameTiles.add(new GameTile(3, InternationalTiles.BAMBOO_1));
        gameTiles.add(new GameTile(4, InternationalTiles.BAMBOO_2));
        gameTiles.add(new GameTile(5, InternationalTiles.BAMBOO_2));
        gameTiles.add(new GameTile(6, InternationalTiles.BAMBOO_2));
        gameTiles.add(new GameTile(7, InternationalTiles.BAMBOO_3));
        gameTiles.add(new GameTile(8, InternationalTiles.BAMBOO_3));
        gameTiles.add(new GameTile(9, InternationalTiles.BAMBOO_3));

        gameTiles.add(new GameTile(10, InternationalTiles.BAMBOO_1));
        gameTiles.add(new GameTile(11, InternationalTiles.BAMBOO_2));


        Collection<Collection<Combination>> arrangements = InternationalScoringSystem.DEFAULT.normalHandArrangements(gameTiles);

        int nbOfArrangements = (4 * 4 * 3);

/*
        for (Collection<Combination> combinations :
                arrangements) {
            System.out.print("{ ");
            for (Combination combination:
                 combinations) {
                System.out.print(Arrays.toString(combination.getTiles()) + " ");
            }
            System.out.println("}");
        }
*/

        Assert.assertEquals(nbOfArrangements, arrangements.size(), 0);
    }

    @Test
    void findAllTriples() {
        System.out.println("findAllTriplesTest");
        ArrayList<GameTile> gameTiles = new ArrayList<>();
        gameTiles.add(new GameTile(1, InternationalTiles.BAMBOO_1));
        gameTiles.add(new GameTile(2, InternationalTiles.BAMBOO_1));
        gameTiles.add(new GameTile(3, InternationalTiles.BAMBOO_1));
        gameTiles.add(new GameTile(4, InternationalTiles.BAMBOO_2));
        gameTiles.add(new GameTile(5, InternationalTiles.BAMBOO_2));
        gameTiles.add(new GameTile(6, InternationalTiles.BAMBOO_2));
        gameTiles.add(new GameTile(7, InternationalTiles.BAMBOO_3));
        gameTiles.add(new GameTile(8, InternationalTiles.BAMBOO_3));
        gameTiles.add(new GameTile(9, InternationalTiles.BAMBOO_3));

        gameTiles.add(new GameTile(10, InternationalTiles.BAMBOO_1));
        gameTiles.add(new GameTile(11, InternationalTiles.BAMBOO_2));

        Collection<Combination> triples = InternationalScoringSystem.DEFAULT.findAllTriples(gameTiles);

        int nbOfTriples = 4 * 2 + 1 + (4 * 4 * 3);

/*
        for (Combination combination :
                triples) {
            System.out.println(Arrays.toString(combination.getTiles()));
        }
*/

        Assert.assertEquals(nbOfTriples, triples.size(), 0);
    }

    @Test
    void sevenPairsHandArrangement() {
        System.out.println("sevenPairsHandArrangementTest");
        ArrayList<GameTile> gameTiles = new ArrayList<>();
        gameTiles.add(new GameTile(1, InternationalTiles.BAMBOO_1));
        gameTiles.add(new GameTile(2, InternationalTiles.BAMBOO_1));
        gameTiles.add(new GameTile(3, InternationalTiles.BAMBOO_2));
        gameTiles.add(new GameTile(4, InternationalTiles.BAMBOO_2));
        gameTiles.add(new GameTile(5, InternationalTiles.BAMBOO_3));
        gameTiles.add(new GameTile(6, InternationalTiles.BAMBOO_3));
        gameTiles.add(new GameTile(7, InternationalTiles.BAMBOO_4));
        gameTiles.add(new GameTile(8, InternationalTiles.BAMBOO_4));
        gameTiles.add(new GameTile(9, InternationalTiles.BAMBOO_5));
        gameTiles.add(new GameTile(10, InternationalTiles.BAMBOO_5));
        gameTiles.add(new GameTile(11, InternationalTiles.BAMBOO_6));
        gameTiles.add(new GameTile(12, InternationalTiles.BAMBOO_6));

        gameTiles.add(new GameTile(13, InternationalTiles.BAMBOO_1));
        gameTiles.add(new GameTile(14, InternationalTiles.BAMBOO_1));

        Collection<Collection<Combination>> arrangements = InternationalScoringSystem.DEFAULT.sevenPairsHandArrangement(gameTiles);

        int nbOfArrangements = 3;

/*
        for (Collection<Combination> combinations :
                arrangements) {
            System.out.print("{ ");
            for (Combination combination:
                 combinations) {
                System.out.print(Arrays.toString(combination.getTiles()) + " ");
            }
            System.out.println("}");
        }
*/

        Assert.assertEquals(nbOfArrangements, arrangements.size(), 0);
    }

    @Test
    void findAllPairs() {
        System.out.println("findAllPairsTest");
        ArrayList<GameTile> gameTiles = new ArrayList<>();
        gameTiles.add(new GameTile(1, InternationalTiles.BAMBOO_1));
        gameTiles.add(new GameTile(2, InternationalTiles.BAMBOO_1));
        gameTiles.add(new GameTile(3, InternationalTiles.BAMBOO_2));
        gameTiles.add(new GameTile(4, InternationalTiles.BAMBOO_2));
        gameTiles.add(new GameTile(5, InternationalTiles.BAMBOO_3));
        gameTiles.add(new GameTile(6, InternationalTiles.BAMBOO_3));
        gameTiles.add(new GameTile(7, InternationalTiles.BAMBOO_4));
        gameTiles.add(new GameTile(8, InternationalTiles.BAMBOO_4));
        gameTiles.add(new GameTile(9, InternationalTiles.BAMBOO_5));
        gameTiles.add(new GameTile(10, InternationalTiles.BAMBOO_5));
        gameTiles.add(new GameTile(11, InternationalTiles.BAMBOO_6));
        gameTiles.add(new GameTile(12, InternationalTiles.BAMBOO_6));

        gameTiles.add(new GameTile(13, InternationalTiles.BAMBOO_1));
        gameTiles.add(new GameTile(14, InternationalTiles.BAMBOO_1));

        Collection<Combination> pairs = InternationalScoringSystem.DEFAULT.findAllPairs(gameTiles);

        int nbOfTriples = 7 + 4;

/*
        for (Combination combination :
                pairs) {
            System.out.println(Arrays.toString(combination.getTiles()));
        }
*/

        Assert.assertEquals(nbOfTriples, pairs.size(), 0);
    }


    @Test
    void identifyPatterns() {
        System.out.println("identifyPatternsTest");

        AbstractCombinationFactory factory = new InternationalCombinationFactory();

        Collection<GameTile> supreme = new HashSet<>();
        supreme.add(new GameTile(56, InternationalTiles.SEASON_AUTUMN));
        supreme.add(new GameTile(44, InternationalTiles.FLOWER_BAMBOO));

        Collection<Combination> melds = new HashSet<>();
        try {
            melds.add(factory.newCombination(
                    new GameTile(1, InternationalTiles.DOT_1),
                    new GameTile(2, InternationalTiles.DOT_2),
                    new GameTile(3, InternationalTiles.DOT_3)));
            melds.add(factory.newCombination(
                    new GameTile(4, InternationalTiles.DOT_1),
                    new GameTile(5, InternationalTiles.DOT_2),
                    new GameTile(6, InternationalTiles.DOT_3)));
        } catch (RulesException e) {
            Assert.fail("bad combination in melds");
        }

        GameTile winningTile = new GameTile(14, InternationalTiles.DRAGON_GREEN);
        Collection<Combination> hand = new HashSet<>();
        try {
            hand.add(factory.newCombination(
                    new GameTile(7, InternationalTiles.BAMBOO_1),
                    new GameTile(8, InternationalTiles.BAMBOO_1),
                    new GameTile(9, InternationalTiles.BAMBOO_1)));
            hand.add(factory.newCombination(
                    new GameTile(10, InternationalTiles.DOT_1),
                    new GameTile(11, InternationalTiles.DOT_2),
                    new GameTile(12, InternationalTiles.DOT_3)));
            hand.add(factory.newCombination(
                    new GameTile(13, InternationalTiles.DRAGON_GREEN),
                    winningTile
            ));
        } catch (RulesException e) {
            Assert.fail("bad combination in hand");
        }



        PlayerSet set = new PlayerSet(
                winningTile,
                hand,
                new HashSet<>(),
                melds,
                supreme,
                false,
                true,
                Wind.EAST,
                Wind.EAST);


        Collection<IdentifiedPattern> patterns = InternationalScoringSystem.DEFAULT.identifyPatterns(set);
        int supremeCount = 0;
        boolean singleWait = false;
        boolean pureDoubleChow = false;

        for (IdentifiedPattern pattern : patterns) {
            System.out.println(pattern.getTiles().toString() + pattern.getPattern().getValue());
            if (pattern.getPattern() == InternationalPatterns.FLOWER_TILES)
                supremeCount++;
            if (pattern.getPattern() == InternationalPatterns.SINGLE_WAIT)
                singleWait = true;
            if (pattern.getPattern() == InternationalPatterns.PURE_DOUBLE_CHOW)
                pureDoubleChow = true;
        }

        Assert.assertEquals(supreme.size(), supremeCount, 0);
        Assert.assertTrue(singleWait);
        Assert.assertTrue(pureDoubleChow);
    }

    @Test
    void splitIncompatiblePatterns() {
        //TODO
    }
}