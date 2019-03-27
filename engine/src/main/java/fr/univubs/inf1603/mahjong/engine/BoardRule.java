package fr.univubs.inf1603.mahjong.engine;
//import fr.univubs.inf1603.mahjong.sapi.*;

import java.util.List;
import java.util.Map;

/**
 * BoardRule : interface to manage rules for the board
 *
 * @author Abdelilah MOULIDA
 */
public interface BoardRule
{
    /**
     * getting player in order
     * @param players
     * @return
     */
    List<Integer> getPlayerOrder(List<Integer> players); //FIXME: use Player instead of Integer ?

    /**
     * starting the wall for the game
     * @return
     */
    StartingWall buildWall();

    /**
     * for the distribution of tiles
     * @param startingWall
     * @return
     */
    Map<GameTile, TileZone> distributeTiles(StartingWall startingWall);

    /**
     * check if the move done is respect rules or no
     * @param tileArrangement
     * @param move
     * @return
     */
    boolean isMoveValid(Map<GameTile, TileZone> tileArrangement, Move move);

    /**
     * show possible moves that respect rules
     * @param tileArrangement
     * @return
     */
    List<Move> findValidMoves(Map<GameTile, TileZone> tileArrangement);

    /**
     * checking if the game is finish and return a boolean
     * @param tileArrangement
     * @return
     */
    boolean isGameFinished(Map<GameTile, TileZone> tileArrangement);
}
