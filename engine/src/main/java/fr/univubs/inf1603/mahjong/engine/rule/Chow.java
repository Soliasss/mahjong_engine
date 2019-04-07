package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.GameTile;

/**
 *
 * @author anton
 */
public class Chow implements Combination {

    private GameTile[] tiles;
    
    /**
     *
     * @param tiles
     * @throws Exception
     */
    public Chow(GameTile[] tiles) throws Exception{
        if(isValid(tiles)){
           this.tiles = tiles; 
        } else {
            throw new Exception("Chow non valide");
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
    public boolean isValid(GameTile[] tiles){
        //Works on the basis that the tiles are either ordered or not a Chow
        return tiles.length==3 &&
                tiles[1].getTile() == tiles[0].getTile().getNext() &&
                tiles[2].getTile() == tiles[1].getTile().getNext();
    }
}
