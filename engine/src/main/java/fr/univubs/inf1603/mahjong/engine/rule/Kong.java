package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.GameTile;

/**
 * This class validates if we have a kong in the player's hand and of he's valid
 *
 */
public class Kong implements Combination {

    private GameTile[] tiles;

    public Kong(GameTile[] tiles) throws RulesException {
        if (isValid(tiles)) {
            this.tiles = tiles;
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

}
