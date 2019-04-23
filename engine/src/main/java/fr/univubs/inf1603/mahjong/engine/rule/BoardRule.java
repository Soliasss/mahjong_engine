package fr.univubs.inf1603.mahjong.engine.rule;
import fr.univubs.inf1603.mahjong.engine.game.MahjongBoard;
import fr.univubs.inf1603.mahjong.engine.game.Move;
import fr.univubs.inf1603.mahjong.engine.game.TileZone;
import java.util.Collection;

import java.util.EnumMap;


/**
 * BoardRule : interface to manage rules for the board
 *
 * @author Abdelilah MOULIDA
 */
public interface BoardRule {
    /**
     * Roll the dice to find the order of the players
     * @return an array of sides, each element is associated with a player (its index)
     */
    Wind[] getPlayerOrder();

    /**
     * Roll the dice to build a {@link StartingWall}
     * @return a new random starting wall
     */
    StartingWall buildWall();

    /**
     * Distribute the tiles in the right {@link TileZone}
     * @param startingWall the starting wall we are distributing from
     * @return the tile zones filled with the distributed tiles
     */
    MahjongBoard distributeTiles(StartingWall startingWall);

    /**
     * Check if a move is valid according to the rules in the given game state
     * @param board Current state of the board
     * @param lastMove the last move played, part of the game state
     * @param move the move we need to check for validity
     * @return true if the move is valid, false otherwise
     */
    boolean isMoveValid(MahjongBoard board, Move lastMove, Move move);

    /**
     * Give all the valid move for each {@link Side} according to the rules in the given game state
     * @param board State of the board to evaluate
     * @param lastMove the last move played, part of the game state
     * @return the moves for each side
     */
    EnumMap<Wind, Collection<Move>> findValidMoves(MahjongBoard board, Move lastMove);

    /**
     * Check from a given game state if the game can/should end.
     * @param board Current state of the board
     * @param lastMove the last move played, part of the game state
     * @return true if the game can/should end.
     */
    boolean isGameFinished(MahjongBoard board, Move lastMove);
}
