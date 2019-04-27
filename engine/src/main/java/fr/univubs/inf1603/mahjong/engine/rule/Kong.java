package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.GameTile;

import java.util.Arrays;
import java.util.Comparator;

/**
 * This class validates if we have a kong in the player's hand and of he's valid
 *
 */
public class Kong implements Combination {

    private GameTile[] tiles;

    public Kong(GameTile[] tiles) throws RulesException {
        if (isValid(tiles)) {
            this.tiles = tiles;
            Arrays.sort(this.tiles, Comparator.comparing(GameTile::getGameID));
        } else {
            throw new RulesException("Invalid kong");
        }
    }

    @Override
    public GameTile[] getTiles() {
        return this.tiles;
    }

    @Override
    public boolean isKong() {
        return true;
    }

    @Override
    public final boolean isValid(GameTile[] tiles) {
        return tiles.length == 4
                && tiles[0].getTile() == tiles[1].getTile()
                && tiles[0].getTile() == tiles[2].getTile()
                && tiles[0].getTile() == tiles[3].getTile();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kong kong = (Kong) o;
        return Arrays.equals(getTiles(), kong.getTiles());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getTiles());
    }
}
