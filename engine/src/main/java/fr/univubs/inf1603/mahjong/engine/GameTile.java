package fr.univubs.inf1603.mahjong.engine;

import java.beans.ConstructorProperties;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.UUID;

/**
 * GameTile represents a tile once in an ongoing game of mahjong.
 * @author Malléjac Clément
 */
public class GameTile implements Serializable,Cloneable, UniqueIdentifiable{
    private AbstractTile tile;
    private final int gameID;
    private final UUID uuid;
    
    /**
     * 
     * @param gameID This GameTile's place in the deck before it gets shuffled
     * @param tile This GameTile's face
     */
    @ConstructorProperties({"gameID", "tile", "uuid"})
    public GameTile(int gameID, AbstractTile tile, UUID uuid){
        this.gameID = gameID;
        this.tile = tile;
        this.uuid = uuid;
    }
    public GameTile(int gameID, AbstractTile tile){
        this(gameID,tile,UUID.randomUUID());
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
    
    @Override
    public UUID getUUID(){
        return this.uuid;
    }
    
    @Override
    public GameTile clone(){
        return new GameTile(this.gameID, this.tile);
    }
    
    /**
     * Permet de set la tuile
     * @param newTile La nouvelle tuile
     */
    public void setTile(AbstractTile newTile){
        AbstractTile oldValue = this.tile;
        
        this.tile = newTile;   
        propertyChangeSupport.firePropertyChange("tile", oldValue, this.tile);
    }
    
  
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this); 
  
    public void addPropertyChangeListener(PropertyChangeListener listener) { 
        propertyChangeSupport.addPropertyChangeListener(listener); 
    } 
  
    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) { 
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener); 
    } 
  
    public void removePropertyChangeListener(PropertyChangeListener listener) { 
        propertyChangeSupport.removePropertyChangeListener(listener); 
    } 
  
    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) { 
        propertyChangeSupport.removePropertyChangeListener(propertyName, listener); 
    }
}
