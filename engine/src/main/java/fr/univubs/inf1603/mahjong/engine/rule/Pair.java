package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.GameTile;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 */
public class Pair implements Combination {

    private GameTile[] tiles;

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
        return this.tiles;
    }

    @Override
    public boolean isPair() {
        return true;
    }

    @Override
    public final boolean isValid(GameTile[] tiles) {
        return  tiles.length == 2 &&
                tiles[0].getTile() == tiles[1].getTile();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return Arrays.equals(getTiles(), pair.getTiles());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getTiles());
    }
}
