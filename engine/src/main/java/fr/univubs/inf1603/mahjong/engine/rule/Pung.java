package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.GameTile;

import java.util.Arrays;
import java.util.Comparator;
import org.apache.log4j.Logger;

/**
 */
public class Pung implements Combination {

    private GameTile[] tiles;
    
    // Logger
    private static final Logger LOGGER = Logger.getLogger(Pung.class.getName());

    public Pung(GameTile[] tiles) throws RulesException {
        if (isValid(tiles)) {
            this.tiles = tiles;
            Arrays.sort(this.tiles, Comparator.comparing(GameTile::getGameID));
        } else {
            throw new RulesException("Invalid pung");
        }
    }

    @Override
    public GameTile[] getTiles() {
        LOGGER.info("Enter to getTiles()");
        return this.tiles;
    }

    @Override
    public boolean isPung() {
        LOGGER.info("Enter to isPung()");
        return true;
    }

    @Override
    public final boolean isValid(GameTile[] tiles) {
        LOGGER.info("Enter to isValid(GameTile[] tiles)");
        return  tiles.length == 3 &&
                tiles[0].getTile() == tiles[1].getTile() &&
                tiles[0].getTile() == tiles[2].getTile();
    }

    @Override
    public boolean equals(Object o) {
        LOGGER.info("Enter to equals(Object o)");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pung pung = (Pung) o;
        return Arrays.equals(getTiles(), pung.getTiles());
    }

    @Override
    public int hashCode() {
        LOGGER.info("Enter to hashCode()");
        return Arrays.hashCode(getTiles());
    }
}
