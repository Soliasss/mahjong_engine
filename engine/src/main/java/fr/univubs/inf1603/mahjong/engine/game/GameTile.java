package fr.univubs.inf1603.mahjong.engine.game;

import fr.univubs.inf1603.mahjong.engine.persistence.Persistable;
import fr.univubs.inf1603.mahjong.engine.rule.AbstractTile;
import fr.univubs.inf1603.mahjong.Wind;

import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.UUID;
import org.apache.log4j.Logger;

/**
 * GameTile represents a tile once in an ongoing game of mahjong.
 *
 */
public class GameTile implements GameTileInterface, Serializable, Cloneable, Persistable {

    private AbstractTile tile;
    private final int gameID;
    private final UUID uuid;
    private boolean publiclyVisible;
    private Wind orientation;
    
        /**
     * This class contain the ROOTLOGGER
     * Definition of LOGGER
     */
    public static final Logger ROOTLOGGER = Logger.getRootLogger();    
    private static final Logger LOGGER = Logger.getLogger(GameTile.class.getName());

    /**
     *
     * @param gameID This GameTile's place in the deck before it gets shuffled
     * @param tile This GameTile's face
     * @param uuid This tile's UUID
     * @param publiclyVisible if the tile is visible or not
     * @param orientation the tile's orientation
     */
    public GameTile(int gameID, AbstractTile tile, UUID uuid, boolean publiclyVisible, Wind orientation) {
        this.gameID = gameID;
        this.tile = tile;
        this.uuid = uuid;
        this.publiclyVisible = publiclyVisible;
        this.orientation = orientation;
    }

    /**
     * @param gameID This GameTile's place in the deck before it gets shuffled
     * @param tile This GameTile's face
     */
    public GameTile(int gameID, AbstractTile tile) {
        this(gameID, tile, UUID.randomUUID(),false,Wind.EAST);
    }

    @Override
    public AbstractTile getTile() {
        return this.tile;
    }

    /**
     * 
     * @return Returns this tiles order in the deck
     */
    @Override
    public int getGameID() {
        LOGGER.info("Enter to getGameID() method");
        return this.gameID;
    }

    @Override
    public UUID getUUID() {
        LOGGER.info("Enter to getUUID() method");
        return this.uuid;
    }

    /**
     * Return is the tile is visible or not
     * @return true if is visible, else return false
     */
    public boolean isPubliclyVisible(){
        LOGGER.info("Enter to isPubliclyVisible() method");
        return this.publiclyVisible;
    }
    
    void setPubliclyVisible(boolean value){
        //I don't see a case where you would have to rehide a tile after it has been discovered
        //TODO : maybe log if the case above happens
        LOGGER.info("Enter to setPubliclyVisible(boolean value) method");
        this.publiclyVisible = value;
    }
    
    @Override
    public GameTile clone() {
        LOGGER.info("Start of method");
        LOGGER.info("End of method");
        return new GameTile(this.gameID, this.tile);
    }

    /**
     * Permet de set la tuile
     *
     * @param newTile La nouvelle tuile
     */
    public void setTile(AbstractTile newTile) {
        LOGGER.info("Enter to setTile(AbstractTile newTile) method");
        AbstractTile oldValue = this.tile;

        this.tile = newTile;
        propertyChangeSupport.firePropertyChange("tile", oldValue, this.tile);
    }

    @Override
    public String toString(){
        LOGGER.info("Enter to toString() method");
        return gameID + ":" + tile.toNormalizedName();
    }

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    @Override
    public PropertyChangeSupport getPropertyChangeSupport() {
        LOGGER.info("Enter to setOrientation(Wind wind) method");
        return propertyChangeSupport;
    }

    /**
     * Set the orientation of the tile
     * @param wind the wind which define the orientation of the tile
     */
    void setOrientation(Wind wind){
        LOGGER.info("Enter to setOrientation(Wind wind) method");
        this.orientation = wind;
    }
    
    /**
     * Return the Wind which define the orientation of the tile
     * @return 
     */
    @Override
    public Wind getOrientation() {
        LOGGER.info("Enter to getOrientation() method");
        return this.orientation;
    }
    
    
}
