package fr.univubs.inf1603.mahjong.engine;

/**
 * GameTile
 * @author Malléjac Clément
 */
public class GameTile{
    private AbstractTile tile;
    private int gameID;
    
    public GameTile(int gameID, AbstractTile tile){
        this.gameID = gameID;
        this.tile = tile;
    }
    
    public AbstractTile getTile(){
        return this.tile;
    }
    
    public int getGameID(){
        return this.gameID;
    }
}
