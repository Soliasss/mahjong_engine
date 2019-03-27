package fr.univubs.inf1603.mahjong.engine;

/**
 * GameTile represents a tile once in an ongoing game of mahjong.
 * @author Malléjac Clément
 */
public class GameTile implements GameElement{
    private AbstractTile tile;
    private final int gameID;
    
    /**
     * 
     * @param gameID This GameTile's place in the deck before it gets shuffled
     * @param tile This GameTile's face
     */
    public GameTile(int gameID, AbstractTile tile){
        this.gameID = gameID;
        this.tile = tile;
    }
    
    /**
     * 
     * @return Returns this GameTile's AbstractTile
     */
    public AbstractTile getTile(){
        return this.tile;
    }
    
    /**
     * 
     * @return Returns this tiles order in the deck
     */
    public int getGameID(){
        return this.gameID;
    }
    
    /**
     * Permet de set la tuile
     * @param newTile La nouvelle tuile
     */
    public void setTile(AbstractTile newTile){
        this.tile = newTile;   
    }
}
