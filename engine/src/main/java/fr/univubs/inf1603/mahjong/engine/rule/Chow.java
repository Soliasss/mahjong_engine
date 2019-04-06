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
        for(int i=0; i < tiles.length; i++){
            if( (tiles[i] == tiles[i+1]) && (tiles[i+1] == tiles[i+2]) )
                return true;
        }
        return false;
    }
}
