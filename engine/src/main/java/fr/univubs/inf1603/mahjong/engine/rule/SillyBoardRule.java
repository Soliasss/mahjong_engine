package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.Board;
import fr.univubs.inf1603.mahjong.engine.game.Move;
import fr.univubs.inf1603.mahjong.engine.game.TileZone;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.EnumMap;

/**
 *
 * @author Abdelilah MOULIDA
 */
public class SillyBoardRule implements BoardRule{

    @Override
    public Wind[] getPlayerOrder() {
        return Wind.values();
    }

    @Override
    public StartingWall buildWall() {
        return new StartingWall(Wind.EAST, 0, new ArrayDeque<>());
    }

    @Override
    public Collection<TileZone> distributeTiles(StartingWall startingWall) {
        return null;
    }

    @Override
    public boolean isMoveValid(Collection<TileZone> tileArrangement, Move lastMove, Move move) {
        return true;
    }

    @Override
    public EnumMap<Wind, Move> findValidMoves(Board board, Move lastMove) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isGameFinished(Collection<TileZone> tileArrangement, Move lastMove) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
