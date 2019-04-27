package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.GameTile;

import java.util.Arrays;

/**
 *
 */
public class Chow implements Combination {

    private GameTile[] tiles;
    
    /**
     *
     * @param tiles
     * @throws RulesException
     */
    public Chow(GameTile[] tiles) throws RulesException{
        if(isValid(tiles)){
           this.tiles = tiles; 
        } else {
            throw new RulesException("Invalid chow");
        }
    }
    @Override
    public GameTile[] getTiles() {
        return this.tiles;
    }

    @Override
    public boolean isChow() {
        return true;
    }
    
    @Override
    public final boolean isValid(GameTile[] tiles){
        //Works on the basis that the tiles are either ordered or not a Chow
        return tiles.length==3 &&
                tiles[0].getTile().getNext() == tiles[1].getTile() &&
                tiles[1].getTile().getNext() == tiles[2].getTile();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chow chow = (Chow) o;
        return Arrays.equals(getTiles(), chow.getTiles());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getTiles());
    }
}
