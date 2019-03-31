package fr.univubs.inf1603.mahjong.engine;

/**
 *
 * @author anton
 */
public class Pung implements Combination{

    private GameTile[] tiles;
    
    public Pung(GameTile[] tiles) throws Exception{
        if(isValid(tiles)){
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
