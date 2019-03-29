package fr.univubs.inf1603.mahjong.engine;

/**
 * This class validates if we have a kong in the player's hand and of he's valid
 * @author anton
 */
public class Kong implements Combination{
    private GameTile[] tiles;

    public Kong(GameTile[] tiles) throws Exception{
        if(isValid(tiles)){
            this.tiles = tiles;
        } else {
            throw new Exception("Kong non valide");
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
    public boolean isValid(GameTile[] tiles) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
