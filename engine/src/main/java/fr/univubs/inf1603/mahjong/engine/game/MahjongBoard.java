package fr.univubs.inf1603.mahjong.engine.game;

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
public class MahjongBoard implements Board, Cloneable {

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

    
    /**
     * Retourne la vision du board du point de vue d un joueur
     * @param wind Le cote du joueur
     * @return Un board du poiunt de vue du joueur
     * @throws GameException Si le vent courrant est null
     */
    Board getViewFromWind(Wind wind) throws GameException{
        MahjongBoard  retBoard = new MahjongBoard(this.getCurrentWind());
        String nameHand = "Hand"+wind.toString();
        String meld1 = "Meld"+wind.toString()+"1";
        String meld2 = "Meld"+wind.toString()+"2";
        String meld3 = "Meld"+wind.toString()+"3";
        String supreme = "Supreme"+wind.toString();
        String discard = "Discard"+wind.toString();
        for(Entry<TileZoneIdentifier,TileZone> entry : this.zones.entrySet()){
            String tziName = entry.getKey().getNormalizedName();
            if(tziName.equals(nameHand)){
                retBoard.zones.put(entry.getKey(), entry.getValue());
            }else if(tziName.equals(meld1)){
                retBoard.zones.put(entry.getKey(), entry.getValue());
            }else if(tziName.equals(meld2)){
                retBoard.zones.put(entry.getKey(), entry.getValue());
            }else if(tziName.equals(meld3)){
                retBoard.zones.put(entry.getKey(), entry.getValue());
            }else if(tziName.equals(supreme)){
                retBoard.zones.put(entry.getKey(), entry.getValue());
            }else if(tziName.equals(discard)){
                retBoard.zones.put(entry.getKey(), entry.getValue());
            }else if(tziName.equals("Wall")){
                retBoard.zones.put(entry.getKey(), entry.getValue());
            }else{
                retBoard.zones.put(entry.getKey(), entry.getValue());
                for(GameTile tile : retBoard.getTileZone(tziName).getTiles()){
                    tile.setTile(HiddenTile.HIDDENTILE);
                }
            }
        }
        return retBoard;
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
        GameTileInterface gTile = null;
        TileZone tmp = null;
        boolean stop = false;
        for(TileZoneIdentifier tzi : this.zones.keySet()){
            tmp = this.zones.get(tzi);
            for(GameTile tile : tmp.getTiles()){
                if(tile.getGameID() == gameIndex){
                    gTile = tile;
                    stop = true;
                    break;
                }
            }
            if(stop)break;
        } 
        if(gTile == null) throw new GameException("The gameIndex isn't in the Board");
        return gTile;
    }

    @Override
    public TileZone getTileZoneOfTile(int gameIndex) throws GameException {
        TileZone tz = null;
        TileZone tmp = null;
        boolean stop = false;
        for(TileZoneIdentifier tzi : this.zones.keySet()){
            tmp = this.zones.get(tzi);
            for(GameTile tile : tmp.getTiles()){
                if(tile.getGameID() == gameIndex){
                    tz = tmp;
                    stop = true;
                    break;
                }
            }
            if(stop)break;
        } 
        if(tz == null) throw new GameException("The gameIndex isn't in the Board");
        return tz;
    }

    @Override
    public TileZone getTileZoneOfTile(GameTileInterface tile) throws GameException {
        return this.getTileZoneOfTile(tile.getGameID());
    }

    @Override
    public TileZone getTileZone(TileZoneIdentifier identifier) throws GameException{
        TileZone tz =  this.zones.get(identifier);
        if(tz == null) throw new GameException("The identifier isn't in the Board");
        return tz;
    }

    @Override
    public TileZone getTileZone(String normalizedName) throws ZoneException {
        TileZone tz = null;
        TileZone tmp = null;
        boolean stop = false;
        for(TileZoneIdentifier tzi : this.zones.keySet()){
            tmp = this.zones.get(tzi);
            for(GameTile tile : tmp.getTiles()){
                if(tile.getTile().toNormalizedName().equals(normalizedName)){
                    tz = tmp;
                    stop = true;
                    break;
                }
            }
            if(stop)break;
        } 
        if(tz == null) throw new ZoneException("The normalized do not correspond to any tile of the Board");
        return tz;
    }

}
