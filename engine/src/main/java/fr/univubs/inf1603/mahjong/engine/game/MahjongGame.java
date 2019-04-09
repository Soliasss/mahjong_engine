package fr.univubs.inf1603.mahjong.engine.game;

import fr.univubs.inf1603.mahjong.engine.rule.GameRule;
import java.beans.PropertyChangeSupport;
import java.time.Duration;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Cette classe permet de représenter une partie de Mahjong
 *
 * @author COGOLUEGNES Charles
 */
public class MahjongGame implements Game {

    private Side[] listPlayers;
    private GameRule rule;
    private boolean finish;
    private Move lastMove;
    private Board board;
    
    private Duration stealingTime;
    private Duration playingTime;
    
    private ArrayList<Move> registeredMoves;
    private UUID uuid;
    
    public MahjongGame(GameRule rule) throws GameException {
        if (rule == null) {
            throw new GameException("Le règle ne peut pas être null.");
        }
        this.rule = rule;
        this.lastMove = null;
        this.finish = false;
        this.board = null;
        this.uuid = UUID.randomUUID();
    }

    @Override
    public Side[] launchGame() {
        this.listPlayers = this.rule.getPlayersOrder();
        this.board = this.rule.initBoard();
        return listPlayers;
    }

    @Override
    public void registerMove(Move move) {
        
    }

    @Override
    public boolean isFinish() {
        return this.finish;
    }

    @Override
    public Board getBoardView(int player) throws GameException {
        Board ret = this.board.clone();
        
        return ret;
    }

    @Override
    public int getPlayerPoints(int player) {
        return 0;
    }

    @Override
    public ArrayList<Move> getPossibleMoves() {
        ArrayList<Move> ret = this.rule.findValidMoves(this.board, this.lastMove);
        return ret;
    }

    @Override
    public Game clone() {
        return null;
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

    @Override
    public Duration getStealingTime() {
        return this.stealingTime;
    }

    @Override
    public Duration getPlayingTime() {
        return this.playingTime;
    }

    @Override
    public Move getLastMove() {
        return this.lastMove;
    }
}
