import java.util.HashMap;

/** 
 * This class allows to represent a move of a tile or some tiles from a position to another position
 */
public class Move{
	private int player;
	private int priority;
	private HashMap<int,TileZone> path;
	
	/**
	 * 
	 * @param player The number which represents the player who is making the move
	 * @param priority The priority of the move regarding other moves (0 is the biggest priority, from the biggest priority to the lowest priority)
	 * @param path A map that contains one or more transition of one or more tile(s). The int is the number of the tile in the deck. The TileZone is the zone where the tile has to go.
	 */
	public Move(int player, int priority, HashMap<int,TileZone> path){
		if(player <= 0 || player > 4) throw new MoveException("The player's number has to be between 1 and 4.");
		this.player = player;
		
		if(priority < 0) throw new MoveException("The priority has to be positive or O");
		this.priority = priority;
		
		if(path.isEmpty()) throw new MoveException("The path can not be empty.");
		this.path = path.clone(); 
	}
	
	/**
	 * Returns the number of the player
	 * @return player
	 */
	public int getPlayer(){
		return this.player;
	}
	
	/**
	 * Returns the priority of the move
	 * @return priority
	 */
	public int getPriority(){
		return this.priority;
	}
	
	/**
	 * Returns the path
	 * @return path
	 */
	public HashMap<int,TileZone> getPath(){
		return this.path.clone();
	}
}
