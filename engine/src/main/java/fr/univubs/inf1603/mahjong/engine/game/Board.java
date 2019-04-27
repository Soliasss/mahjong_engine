package fr.univubs.inf1603.mahjong.engine.game;

import fr.univubs.inf1603.mahjong.engine.persistence.Persistable;
import fr.univubs.inf1603.mahjong.engine.rule.Wind;

/**
 *
 */
public interface Board extends Persistable {

    /**
     * @param gameIndex Game index of the tile we want to
     * @return If it exists, the associated gameTileInterface
     * @throws GameException If the tile does not exist.
     */
    public GameTileInterface getTile(int gameIndex) throws GameException;

    /**
     * @param gameIndex This tile's game id
     * @return This tile's TileZone
     * @throws GameException If the tile doe snot exist.
     */
    public TileZone getTileZoneOfTile(int gameIndex) throws GameException;

    /**
     * @param tile The tile we want to locate
     * @return The provided tile's TileZone
     * @throws GameException If the provided tile is not present in the game or
     * in the case on incoherent information in the provided tile.
     */
    public TileZone getTileZoneOfTile(GameTileInterface tile) throws GameException;

    /**
     * @param identifier The identifier of the TileZone we want
     * @return The zone corresponding to this identifier
     * @throws GameException If the wanted tilezone is not in the board.
     */
    public TileZone getTileZone(TileZoneIdentifier identifier)throws GameException;

    /**
     * 
     * @param normalizedName The normalized name of the zone we want to get
     * @return Returns the zone corresponding to this normalized name
     * @throws GameException If the provided String does not correspond to any zone
     */
    public TileZone getTileZone(String normalizedName) throws GameException;
    
    /**
     * Retourne le vent courrant du board
     * @return Le vent courant
     * @throws fr.univubs.inf1603.mahjong.engine.game.GameException
     */
    public Wind getCurrentWind() throws GameException;
    
}
