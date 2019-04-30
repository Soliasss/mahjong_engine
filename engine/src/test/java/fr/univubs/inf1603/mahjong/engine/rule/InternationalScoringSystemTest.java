package fr.univubs.inf1603.mahjong.engine.rule;
import fr.univubs.inf1603.mahjong.Wind;

import fr.univubs.inf1603.mahjong.engine.game.GameTile;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class InternationalScoringSystemTest {
    
        public InternationalScoringSystemTest(){}

    @Test
    public void createSetsFromSituation() {
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
    public void findMultipleHandArrangements() {
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
    public void normalHandArrangements() {
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
    public void findAllTriples() {
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
    public void sevenPairsHandArrangement() {
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
    public void findAllPairs() {
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
    public void identifyPatterns() {
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
        int pureDoubleChowCount = 0;
        boolean singleWait = false;
        boolean outsideHand = false;

        for (IdentifiedPattern pattern : patterns) {
            System.out.printf("%30s %s%n", pattern.getPattern().toString(), pattern.getTiles().toString());

            if (pattern.getPattern() == InternationalPatterns.FLOWER_TILES)
                supremeCount += pattern.getTiles().size();

            if (pattern.getPattern() == InternationalPatterns.SINGLE_WAIT)
                singleWait = true;

            if (pattern.getPattern() == InternationalPatterns.PURE_DOUBLE_CHOW)
                pureDoubleChowCount++;

            if (pattern.getPattern() == InternationalPatterns.OUTSIDE_HAND)
                outsideHand = true;
        }


        Assert.assertEquals(supreme.size(), supremeCount, 0);
        Assert.assertEquals(3, pureDoubleChowCount, 0);
        Assert.assertTrue(singleWait);
        Assert.assertTrue(outsideHand);
    }

    @Test
    public void splitIncompatiblePatterns() {
        System.out.println("splitIncompatiblePatternsTest");

        Collection<IdentifiedPattern> patterns = new HashSet<>();

        GameTile
                gameTile1 = new GameTile(1, InternationalTiles.BAMBOO_1),
                gameTile2 = new GameTile(2, InternationalTiles.BAMBOO_2),
                gameTile3 = new GameTile(3, InternationalTiles.BAMBOO_3),
                gameTile4 = new GameTile(4, InternationalTiles.BAMBOO_1),
                gameTile5 = new GameTile(5, InternationalTiles.BAMBOO_2),
                gameTile6 = new GameTile(6, InternationalTiles.BAMBOO_3),
                gameTile7 = new GameTile(7, InternationalTiles.BAMBOO_1),
                gameTile8 = new GameTile(8, InternationalTiles.BAMBOO_2),
                gameTile9 = new GameTile(9, InternationalTiles.BAMBOO_3);


        patterns.add(new IdentifiedPattern(
                InternationalPatterns.PURE_DOUBLE_CHOW,
                gameTile1, gameTile2, gameTile3,
                gameTile4, gameTile5, gameTile6));

        patterns.add(new IdentifiedPattern(
                InternationalPatterns.PURE_DOUBLE_CHOW,
                gameTile4, gameTile5, gameTile6,
                gameTile7, gameTile8, gameTile9));

        patterns.add(new IdentifiedPattern(
                InternationalPatterns.PURE_DOUBLE_CHOW,
                gameTile1, gameTile2, gameTile3,
                gameTile7, gameTile8, gameTile9));


        patterns.add(new IdentifiedPattern(
                InternationalPatterns.SINGLE_WAIT,
                new GameTile(10, InternationalTiles.DRAGON_GREEN),
                new GameTile(11, InternationalTiles.DRAGON_GREEN)));

        Collection<Collection<IdentifiedPattern>> splits = InternationalScoringSystem.DEFAULT.splitIncompatiblePatterns(patterns);

/*
        int i = 0;
        for (Collection<IdentifiedPattern> split : splits) {
            System.out.println("split " + i);
            for (IdentifiedPattern pattern : split)
                System.out.printf("%30s %s%n", pattern.getPattern().toString(), pattern.getTiles().toString());

            i++;
        }
*/

        Assert.assertEquals(3, splits.size(), 0);
        for (Collection<IdentifiedPattern> split : splits)
            Assert.assertEquals(2, split.size(), 0);
    }

    @Test
    public void computeScore(){
        System.out.println("computeScoreTest");

        Collection<IdentifiedPattern> patterns = new HashSet<>();

        patterns.add(new IdentifiedPattern(
                InternationalPatterns.PURE_DOUBLE_CHOW,
                new GameTile(-1, InternationalTiles.BAMBOO_1)));


        patterns.add(new IdentifiedPattern(
                InternationalPatterns.SINGLE_WAIT,
                new GameTile(-1, InternationalTiles.BAMBOO_1)));

        int score = InternationalScoringSystem.DEFAULT.computeScore(patterns);

        Assert.assertEquals(2, score);
    }
}