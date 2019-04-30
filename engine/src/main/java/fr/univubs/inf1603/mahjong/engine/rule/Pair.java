package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.GameTile;

import java.util.Arrays;
import java.util.Comparator;
import org.apache.log4j.Logger;

/**
 *
 */
public class Pair implements Combination {

    private GameTile[] tiles;
    
    // Logger
    private static final Logger LOGGER = Logger.getLogger(Pair.class.getName());

    public Pair(GameTile[] tiles) throws RulesException {
        if (isValid(tiles)) {
            this.tiles = tiles;
            Arrays.sort(this.tiles, Comparator.comparing(GameTile::getGameID));
        } else {
            throw new RulesException("Invalid pair");
        }
    }

    @Override
    public GameTile[] getTiles() {
        LOGGER.info("Enter to getTiles()");
        return this.tiles;
    }

    @Override
    public boolean isPair() {
        LOGGER.info("Enter to isPair()");
        return true;
    }

    @Override
    public final boolean isValid(GameTile[] tiles) {
        LOGGER.info("Enter to isValid(GameTile[] tiles)");
        return  tiles.length == 2 &&
                tiles[0].getTile() == tiles[1].getTile();
    }

    @Override
    public boolean equals(Object o) {
        LOGGER.info("Enter to equals(Object o)");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return Arrays.equals(getTiles(), pair.getTiles());
    }

    @Override
    public int hashCode() {
        LOGGER.info("Enter to hashcode()");
        return Arrays.hashCode(getTiles());
    }
}
