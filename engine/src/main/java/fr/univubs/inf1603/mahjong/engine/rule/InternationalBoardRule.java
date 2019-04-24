/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.MahjongBoard;
import fr.univubs.inf1603.mahjong.engine.game.Move;
import java.util.Collection;
import java.util.EnumMap;

/**
 *
 * @author maxime
 */
public class InternationalBoardRule implements BoardRule{

    @Override
    public Wind[] getPlayerOrder() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StartingWall buildWall() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MahjongBoard distributeTiles(StartingWall startingWall) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isMoveValid(MahjongBoard board, Move lastMove, Move move) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EnumMap<Wind, Collection<Move>> findValidMoves(MahjongBoard board, Move lastMove) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isGameFinished(MahjongBoard board, Move lastMove) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 }
