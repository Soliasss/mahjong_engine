package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.GameTile;

/**
 *
 * @author anton
 */
public class Pung implements Combination {

    private GameTile[] tiles;

    public Pung(GameTile[] tiles) throws Exception {
        if (isValid(tiles)) {
            this.tiles = tiles;
        } else {
            throw new Exception("Pung non valide");
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
    public boolean isValid(GameTile[] tiles) {
        return tiles.length == 3
                && tiles[0].getTile().getNext() == tiles[1].getTile()
                && tiles[0].getTile().getNext() == tiles[2].getTile();
    }

}
