package fr.univubs.inf1603.mahjong.engine;

import java.util.ArrayList;
import java.util.UUID;
/**
 * This class is a representation of a zone where the tiles will be contains
 * @author COGOLUEGNES Charles
 */
public class TileZone implements Zone{
	private UUID id;
	private String name;
	private ArrayList<AbstractTile> tiles;

	/**
	 * The constructor of TileZone, the id is generate in the constructor
	 * @param name The name of the zone
	 * @param tiles the collection of tiles contains in this zone
	 * @throws TileZoneException if the collection of tiles is null
	 */
	public TileZone(String name, ArrayList<AbstractTile> tiles) throws TileZoneException{
		this.id = UUID.randomUUID();
		this.name = name;

		if(tiles == null) throw new TileZoneException("The collection cannot be null.");
		this.tiles  = tiles;
	}

	/**
	 * Returns the collection that contains the tiles
	 * @return tiles
	 */
	public ArrayList<AbstractTile> getCollection(){
		return this.tiles;
	}

	/**
	 * Not suppose to be used in TileZone
	 * @return 0
	 */
	public int getMoney(){
		return 0;
	}

	/**
	 * Returns the id of the TileZone
	 * @return id
	 */
	public UUID getUUID(){
		return this.id;
	}

	/**
	 * Allows to add a tile in the collection
	 * @param tile The tile we want to add
	 * @return if the tile has been correctly added
	 */
	public boolean addTile(AbstractTile tile){
		return this.tiles.add(tile);
	}

	/**
	 * Allows to remove a tile from the collection
	 * @param tile The tile we want to remove
	 * @return if the tile has been correctly removed
	 */
	public boolean removeTile(AbstractTile tile){
		return this.tiles.remove(tile);
	}
}
