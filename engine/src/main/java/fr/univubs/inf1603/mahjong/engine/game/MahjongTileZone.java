package fr.univubs.inf1603.mahjong.engine.game;

import fr.univubs.inf1603.mahjong.engine.persistence.Persistable;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Cette classe est une représentation d'une zone contenant des tuiles
 *
 * @author COGOLUEGNES Charles
 */
public class MahjongTileZone implements TileZone, Persistable{

    private final ArrayList<GameTile> tiles;
    private final UUID uuid;
    private final TileZoneIdentifier identifier;
    
    /**
     * Constructor allowing for an initialization of all fields.
     * @param tiles The list of tile we want this tile zone to have
     * @param uuid This tile zone's UUID
     * @param identifier This tile zone's identifier
     */
    public MahjongTileZone(ArrayList<GameTile> tiles,UUID uuid,TileZoneIdentifier identifier){
        this.tiles = tiles;
        this.uuid = uuid;
        this.identifier = identifier;
    }
    
    /**
     * Constructor for a new empty tile zones
     * @param identifier This tile zone's identifier
     */
    public MahjongTileZone(TileZoneIdentifier identifier){
        this(new ArrayList<GameTile>(),UUID.randomUUID(),identifier);
    }
    
    
    /**
     * Adds a GameTile to this TileZone's list of tiles
     * @param tile The tile we want to add to this TileZone
     */
    public void addTile(GameTile tile){
        this.tiles.add(tile);
    }
    
    /**
     * Removes a GameTile from this TileZone's list of tiles
     * @param tile The tile we want to remove from this TileZone
     */
    public void removeTile(GameTile tile){
        this.tiles.remove(tile);
    }
    
    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    @Override
    public PropertyChangeSupport getPropertyChangeSupport() {
        return this.propertyChangeSupport;
    }

    @Override
    public MahjongTileZone clone(){
        ArrayList<GameTile> cloneList = new ArrayList<>();
        this.tiles.forEach(elem->cloneList.add((GameTile)elem.clone()));
        return new MahjongTileZone(cloneList, UUID.randomUUID(), identifier);
    }

    @Override
    public ArrayList<GameTile> getTiles() {
        return this.tiles;
    }

    @Override
    public TileZoneIdentifier getIdentifier() {
        return this.identifier;
    }

}
