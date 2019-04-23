package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.GameTile;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

class InternationalScoringSystemTest {

    @Test
    void createSetsFromSituation() {
        //TODO
    }

    @Test
    void findMultipleHandArrangements() {
        //TODO
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

        Collection<Collection<Combination>> arrangements = InternationalScoringSystem.DEFAULT.normalHandArrangements(gameTiles);

        int nbOfArrangements = (6*6*6*6) + (6*(3*3*3+2*2*2+1));

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

        Collection<Combination> triples = InternationalScoringSystem.DEFAULT.findAllTriples(gameTiles);

        int nbOfTriples = (6*3) + (3*3*3);

        Assert.assertEquals(nbOfTriples, triples.size(), 0);
    }

    @Test
    void sevenPairsHandArrangement() {
        //TODO
    }

    @Test
    void splitIncompatiblePatterns() {
        //TODO
    }
}