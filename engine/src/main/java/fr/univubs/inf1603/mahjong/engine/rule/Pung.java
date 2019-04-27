package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.GameTile;

import java.util.Arrays;
import java.util.Comparator;

/**
 */
public class Pung implements Combination {

    private GameTile[] tiles;

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
        return this.tiles;
    }

    @Override
    public boolean isPung() {
        return true;
    }

    @Override
    public final boolean isValid(GameTile[] tiles) {
        return  tiles.length == 3 &&
                tiles[0].getTile() == tiles[1].getTile() &&
                tiles[0].getTile() == tiles[2].getTile();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pung pung = (Pung) o;
        return Arrays.equals(getTiles(), pung.getTiles());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getTiles());
    }
}
