package fr.univubs.inf1603.mahjong.engine.game;

import fr.univubs.inf1603.mahjong.engine.rule.Wind;
import java.beans.PropertyChangeSupport;
import java.util.EnumMap;
import java.util.HashMap;
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
    private HashMap<Integer,GameTileInterface> indexToTile;
    private HashMap<GameTileInterface,TileZone> tileToZone;

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
        indexToTile=null;
        tileToZone=null;
    }

    public MahjongBoard(Wind wind) {
        this.currentWind = wind;
        this.uuid = UUID.randomUUID();
        this.zones = new EnumMap<>(TileZoneIdentifier.class);
        for( TileZoneIdentifier tzi : TileZoneIdentifier.values()){
            this.zones.put(tzi,new MahjongTileZone(tzi));
        }
        indexToTile=null;
        tileToZone=null;
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
        if(indexToTile == null){
            buildReverseSearch();
        }
        GameTileInterface result = indexToTile.get(gameIndex);
        if(result==null){
            throw new GameException("Unable to find tile of gameIndex "+gameIndex);
        }
        return result;
    }

    @Override
    public TileZone getTileZoneOfTile(int gameIndex) throws GameException {
        return this.getTileZoneOfTile(getTile(gameIndex));        
    }

    @Override
    public TileZone getTileZoneOfTile(GameTileInterface tile) throws GameException {
        if(tileToZone == null){
            buildReverseSearch();
        }
        TileZone result = this.tileToZone.get(tile);
        if(result.getTiles().contains(tile)){
            return result;
        }
        //At this point the tile that was asked for is either not present on the Board or the reverse search is broken
        throw new GameException("Tile does not exist within this Board");
    }

    @Override
    public TileZone getTileZone(TileZoneIdentifier identifier) throws GameException{
        TileZone tz = this.zones.get(identifier);
        if(tz == null) throw new GameException("The identifier isn't in the Board");
        return tz;
    }

    @Override
    public TileZone getTileZone(String normalizedName) throws GameException {
        return getTileZone(TileZoneIdentifier.getIdentifierFromNormalizedName(normalizedName));
    }
    
    private void buildReverseSearch() throws GameException{
        this.indexToTile = new HashMap<>();
        this.tileToZone = new HashMap<>();
        for(TileZone t : this.zones.values()){
            for(GameTileInterface tile : t.getTiles()){
                this.indexToTile.put(tile.getGameID(), tile);
                this.tileToZone.put(tile, t);
            }
        } 
    }
    
    
    /**
     * This applies given Move to the Board moving tiles from zones to other ones
     * @param move Move to be applied
     * @throws GameException If the move is incoherent or appears to be forged
     */
    public void applyMove(Move move) throws GameException{
        isMoveCoherent(move);
        for(Entry<Integer, TileZoneIdentifier> t : move.getPath().entrySet()){
            GameTileInterface buf = getTile(t.getKey());
            getTileZoneOfTile(buf).getTiles().remove(buf);
            getTileZone(t.getValue()).getTiles().add(buf);
            this.tileToZone.put(buf,getTileZone(t.getValue()));//Updating the reverse search
        }
    }
    
    private void isMoveCoherent(Move move) throws GameException{
        for(Entry<Integer, TileZoneIdentifier> t : move.getPath().entrySet()){
            if(getTileZoneOfTile(t.getKey())==null){
                throw new GameException("Move "+move.getUUID()+" is trying to move a tile that does not exist");
            }
            if(getTileZoneOfTile(t.getKey()) == getTileZone(t.getValue())){
                throw new GameException("Move "+move.getUUID()+" is trying to move a tile back into the same zone");
            }
        }
    }
    
}
