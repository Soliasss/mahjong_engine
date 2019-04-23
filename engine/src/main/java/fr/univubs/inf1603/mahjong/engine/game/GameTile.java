package fr.univubs.inf1603.mahjong.engine.game;

import fr.univubs.inf1603.mahjong.engine.persistence.Persistable;
import fr.univubs.inf1603.mahjong.engine.rule.AbstractTile;
import fr.univubs.inf1603.mahjong.engine.rule.Wind;

import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.UUID;

/**
 * GameTile represents a tile once in an ongoing game of mahjong.
 *
 * @author Malléjac Clément
 */
public class GameTile implements GameTileInterface, Serializable, Cloneable, Persistable {

    private AbstractTile tile;
    private final int gameID;
    private final UUID uuid;

    /**
     *
     * @param gameID This GameTile's place in the deck before it gets shuffled
     * @param tile This GameTile's face
     * @param uuid This tile's UUID
     */
    public GameTile(int gameID, AbstractTile tile, UUID uuid) {
        this.gameID = gameID;
        this.tile = tile;
        this.uuid = uuid;
    }

    public GameTile(int gameID, AbstractTile tile) {
        this(gameID, tile, UUID.randomUUID());
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
        return this.gameID;
    }

    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    @Override
    public GameTile clone() {
        return new GameTile(this.gameID, this.tile);
    }

    /**
     * Permet de set la tuile
     *
     * @param newTile La nouvelle tuile
     */
    public void setTile(AbstractTile newTile) {
        AbstractTile oldValue = this.tile;

        this.tile = newTile;
        propertyChangeSupport.firePropertyChange("tile", oldValue, this.tile);
    }

    @Override
    public String toString(){
        return gameID + ":" + tile.toNormalizedName();
    }

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    @Override
    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }
}
