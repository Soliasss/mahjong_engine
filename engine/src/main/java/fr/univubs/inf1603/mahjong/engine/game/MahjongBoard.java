package fr.univubs.inf1603.mahjong.engine.game;

import fr.univubs.inf1603.mahjong.engine.persistence.Persistable;
import fr.univubs.inf1603.mahjong.engine.rule.Wind;
import java.beans.PropertyChangeSupport;
import java.util.EnumMap;
import java.util.Map.Entry;
import java.util.UUID;

/**
 * MahjongBoard represents the mahjong board during the game.
 *
 * @author Samuel LE BERRE
 */
public class MahjongBoard implements Board,Persistable, Cloneable {

    private Wind currentWind;
    private final UUID uuid;
    private final EnumMap<TileZoneIdentifier, TileZone> zones;

    /**
     * This constructor allows initialization of all fields and should only be
     * used by a persistence framework, factories, or other constructors of this
     * class
     *
     * @param wind
     * @param uuid
     * @param zones
     */
    public MahjongBoard(Wind wind, UUID uuid, EnumMap<TileZoneIdentifier, TileZone> zones) {
        this.currentWind = wind;
        this.uuid = uuid;
        this.zones = zones;
    }

    public MahjongBoard(Wind wind) {
        this.currentWind = wind;
        this.uuid = UUID.randomUUID();
        this.zones = new EnumMap<>(TileZoneIdentifier.class);
        for(Entry<TileZoneIdentifier,TileZone> entry : zones.entrySet()){
            entry.setValue(new MahjongTileZone(entry.getKey()));
        }
    }

    @Override
    public Wind getCurrentWind() throws GameException {
        return this.currentWind;
    }
    
    public void setWind(Wind newWind) {
        this.currentWind = newWind;
    }

    
    
    Board getViewFromWind(Wind wind) throws ZoneException {
        throw new UnsupportedOperationException("not implemented yet"); //TODO : implement view computing
    }

    @Override
    public UUID getUUID(){
        return this.uuid;
    }

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    @Override
    public PropertyChangeSupport getPropertyChangeSupport() {
        return this.propertyChangeSupport;
    }

    @Override
    public GameTileInterface getTile(int gameIndex) throws GameException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TileZone getTileZoneOfTile(int gameIndex) throws GameException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TileZone getTileZoneOfTile(GameTileInterface tile) throws GameException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TileZone getTileZone(TileZoneIdentifier identifier) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TileZone getTileZone(String normalizedName) throws ZoneException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
