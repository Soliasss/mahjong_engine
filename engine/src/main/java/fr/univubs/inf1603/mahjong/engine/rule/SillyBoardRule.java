package fr.univubs.inf1603.mahjong.engine.rule;

// pblm ligne 49

import fr.univubs.inf1603.mahjong.engine.game.GameTile;
import fr.univubs.inf1603.mahjong.engine.game.Move;
import fr.univubs.inf1603.mahjong.engine.game.TileZone;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.EnumMap;
import java.util.Random;
import javafx.geometry.Side;

/**
 *
 * @author Abdelilah MOULIDA
 */
public class SillyBoardRule {
    
    /**
     * Roll the dice to find the order of the players
     * @return an array of sides, each element is associated with a player (its index)
     */
    Side getPlayerOrder()
    {
        /* retourner un random d'un enum Side */
        return Side.values()[new Random().nextInt(Side.values().length)];
    }

    /**
     * Roll the dice to build a {@link StartingWall}
     * @return a new random starting wall
     */
    StartingWall buildWall()
    {
        // Intializing an deque 
        ArrayDeque<GameTile> cut = new ArrayDeque<GameTile>(); 
                 
        return new StartingWall(Wind.EAST, 10, cut); 
    }

    /**
     * Distribute the tiles in the right {@link TileZone}
     * @param startingWall the starting wall we are distributing from
     * @return the tile zones filled with the distributed tiles
     */
    Collection<TileZone> distributeTiles(StartingWall startingWall)
    {
        /*
        
        */
        return null;
    }

    /**
     * Check if a move is valid according to the rules in the given game state
     * @param tileArrangement how the tiles are on the board, part of the game state
     * @param lastMove the last move played, part of the game state
     * @param move the move we need to check for validity
     * @return true if the move is valid, false otherwise
     */
    boolean isMoveValid(Collection<TileZone> tileArrangement, Move lastMove, Move move)
    {
        Random random = new Random();
        return random.nextBoolean();
    }
    /**
     * Give all the valid move for each {@link Side} according to the rules in the given game state
     * @param tileArrangement how the tiles are on the board, part of the game state
     * @param lastMove the last move played, part of the game state
     * @return the moves for each side
     */
    EnumMap<Wind, Move> findValidMoves(Collection<TileZone> tileArrangement, Move lastMove)
    {
        // Create an EnumMap to attribute for each Wind valid moves
        EnumMap<Wind, Move> ValidMoves = new EnumMap<>(Wind.class);
        
        // for the player in WEST Side
        ValidMoves.put(Wind.WEST, lastMove);
        
        // for the player in EAST Side
        ValidMoves.put(Wind.EAST, lastMove);
        
        // for the player in NORTH Side
        ValidMoves.put(Wind.NORTH, lastMove);
        
        // for the player in SOUTH Side
        ValidMoves.put(Wind.SOUTH, lastMove);
        
        return ValidMoves;
    }

    /**
     * Check from a given game state if the game can/should end.
     * @param tileArrangement the "game state", how the tiles are on the board
     * @param lastMove the last move played, part of the game state
     * @return true if the game can/should end.
     */
    boolean isGameFinished(Collection<TileZone> tileArrangement, Move lastMove)
    {
        // I choice that the Game never finishe
        return false;
    }
}
