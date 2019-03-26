package fr.univubs.inf1603.mahjong.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Board represents the mahjong board during the game.
 * @author Samuel LE BERRE
 */
public class Board {

    /**
     * Represents the players defined by an integer and their zone.
     * The zone is the container of all the zone of the player.
     */
    private HashMap<Integer,Zone> playersZones;
    /**
     * Represents the Wall of the Mahjong
     */
    private Zone wallZone;

    /**
     * Constructor of Board with a wall and the playersZones
     * @param wZ The Wall of the Board
     * @param pZ The HashMap of the Player(int) and their Zone
     */
    public Board(Zone wZ, HashMap<Integer,Zone> pZ) {
        this.wallZone = wZ;
        this.playersZones = pZ;
    }

    /**
     * Constructor of Board with a wall
     * @param wZ The Wall of the Board
     */
    public Board(Zone wZ) {
        this.wallZone = wZ;
        this.playersZones = new HashMap<Integer,Zone>();
    }

    /**
     * Add a player and his zone into the HashMap
     * @param player The player we want to add in the HashMap
     * @param zone The zone of the player
     */
    public void addPlayerZone(int player, Zone zone) {
        this.playersZones.put(player,zone);
    }
    
    /**
     * 
     */
    @Override 
    public Board clone(){
        Zone retWall = this.wallZone.clone();
        Board retBoard = new Board(retWall);
        retBoard.playersZones.entrySet().forEach(entry -> {
            retBoard.addPlayerZone(entry.getKey(), entry.getValue().clone());
        }); 
        return null;
    }

    /**
     * Allows to return an instance of the Board
     * @return The board we want to return
     */
    public Board getBoard() {
        return this;
    }

    /**
     * Allows to zone of a specified player
     * @param player The player we want to get the zone
     */
    public Zone getPlayerZone(int player){
        return this.playersZones.get(player);
    }

    /**
     * 
     */
    public Zone getWallZone(){
        return this.wallZone;
    }
}