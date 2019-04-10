package fr.univubs.inf1603.mahjong.engine.game;

import fr.univubs.inf1603.mahjong.engine.persistence.Persistable;
import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.UUID;

/**
 * MahjongBoard represents the mahjong board during the game.
 *
 * @author Samuel LE BERRE
 */
public class MahjongBoard implements Persistable, Cloneable {

    /**
     * Represents the players defined by an integer and their zone. The zone is
     * the container of all the zone of the player.
     */
    private HashMap<Integer, Zone> playersZones;

    /**
     * Represents the Wall of the Mahjong
     */
    private Zone wallZone;
    private final UUID uuid;

    /**
     * Constructor of Board with a wall and the playersZones
     *
     * @param wZ The Wall of the Board
     * @param pZ The HashMap of the Player(int) and their Zone
     * @param uuid L identifiant unique du Board
     */
    public MahjongBoard(Zone wZ, HashMap<Integer, Zone> pZ, UUID uuid) {
        this.wallZone = wZ;
        this.playersZones = pZ;
        this.uuid = uuid;
    }

    /**
     * Constructor of Board with a wall and the playersZones
     *
     * @param wZ The Wall of the Board
     * @param pZ The HashMap of the Player(int) and their Zone
     */
    public MahjongBoard(Zone wZ, HashMap<Integer, Zone> pZ) {
        this(wZ, pZ, UUID.randomUUID());
    }

    /**
     * Constructor of Board with a wall
     *
     * @param wZ The Wall of the Board
     */
    private MahjongBoard(Zone wZ) {
        this(wZ, new HashMap<Integer, Zone>(), UUID.randomUUID());
    }

    /**
     * Add a player and his zone into the HashMap
     *
     * @param player The player we want to add in the HashMap
     * @param zone The zone of the player
     */
    public void addPlayerZone(int player, Zone zone) {
        this.playersZones.put(player, zone);
    }

    /**
     * Fait une copie du MahjongBoard
     * @return La copie du MahjongBoard
     */
    @Override
    public MahjongBoard clone(){
        /*
        Zone retWall = this.wallZone.clone();
        MahjongBoard retBoard = new MahjongBoard(retWall);
        retBoard.playersZones.entrySet().forEach(entry -> {
            retBoard.addPlayerZone(entry.getKey(), entry.getValue().clone());
        });
        return retBoard;*/
        return null;
    }

    /**
     * Allows to zone of a specified player
     *
     * @param player The player we want to get the zone
     * @return the zone of the given player
     */
    public Zone getPlayerZone(int player) {
        return this.playersZones.get(player);
    }

    /**
     * Accesseur sur la zone du mur
     * @return Le mur du MahjongBoard
     */
    public Zone getWallZone() {
        return this.wallZone;
    }

    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    @Override
    public PropertyChangeSupport getPropertyChangeSupport() {
        return propertyChangeSupport;
    }
}
