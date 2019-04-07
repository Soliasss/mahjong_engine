package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.GameTile;

/**
 *
 * @author anton
 */
public class Pair implements Combination {

    private GameTile[] tiles;

    public Pair(GameTile[] tiles) throws Exception {
        if (isValid(tiles)) {
            this.tiles = tiles;
        } else {
            throw new Exception("Pair non valide");
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
    public boolean isValid(GameTile[] tiles) {
        return tiles.length == 2
                && tiles[0].getTile() == tiles[1].getTile();
    }

}
