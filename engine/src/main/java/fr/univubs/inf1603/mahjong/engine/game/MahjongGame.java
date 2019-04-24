package fr.univubs.inf1603.mahjong.engine.game;

import fr.univubs.inf1603.mahjong.engine.rule.GameRule;
import fr.univubs.inf1603.mahjong.engine.rule.Wind;
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

    private Wind[] playerWind;
    private GameRule rule;
    private Move lastPlayedMove;
    private MahjongBoard board;

    private Duration stealingTime;
    private Duration playingTime;

    private ArrayList<Move> registeredMoves;
    private ArrayList<Move> possiblesMoves;
    private UUID uuid;

    private boolean ableToRegisterMoves;

    public MahjongGame(GameRule rule) throws GameException {
        this.rule = rule;
        this.board = null;
        this.uuid = UUID.randomUUID();
        this.ableToRegisterMoves = false;
    }

    @Override
    public void launchGame() {
        //this.playerWind = this.rule.getPlayersOrder();
        //this.board = this.rule.initBoard();
        this.getAndFirePossibleMoves();
    }

    @Override
    public void registerMove(Move move) throws GameException {
        if(this.ableToRegisterMoves){
          this.registeredMoves.add(move);
        }
    }

    @Override
    public Board getBoard(Wind wind) throws GameException {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public int getPlayerPoints(int player) {
        return 0;
    }

    @Override
    public ArrayList<Move> getPossibleMoves() {
        throw new UnsupportedOperationException("not implemented yet");
        //ArrayList ret = this.rule.getBoardRule().findValidMoves(this.board, this.lastPlayedMove);
        //return ret;
    }

    @Override
    public MahjongGame clone() {
        throw new UnsupportedOperationException("not implemented yet");
//        return new MahjongGameGame();
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
    public Move getLastPlayedMove() {
        return this.lastPlayedMove;
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
    public GameRule getRule() {
        return this.rule;
    }

    @Override
    public Wind getCurrentwind() throws GameException {
        return this.board.getCurrentWind();
    }

    @Override
    public int getPlayerPoints(Wind wind) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[] getAllPlayerPoints() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Wind getPlayerWind(int player) throws GameException {
        if(player<0 || player > 3)throw new GameException("The player you are asking the wind from doesn't exist. There is only 4 player describs by 0 1 2 3 .");
        Wind wind = this.playerWind[player];
        if(wind == null) throw new GameException("The wind of a player cannot be null.");
        return wind;
    }

    @Override
    public int getPlayerFromWind(Wind wind) throws GameException {
        int player = -1;
        for(int i=0; i<4; i++){
            if(this.playerWind[i] == wind) player = i;
        }
        if(player == -1)throw new GameException("The wind has to be in the playerWind.");
        return player;
    }

    @Override
    public Wind[] getPlayerWinds() throws GameException {
        return this.playerWind;
    }

    @Override
    public ArrayList<Move> getPossibleMoves(Wind wind) throws GameException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Move> getPossibleMoves(int player) throws GameException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Permet d'effectuer le move sur le Board
     * @param move Le move à effectuer
     */
    private void applyMove(Move move) throws GameException{
      //Appliquer le move au Board
      boolean leMoveACorrectementEteApplique = true; //temporaire
      if(leMoveACorrectementEteApplique){
        this.lastPlayedMove = move;
        this.propertyChangeSupport.firePropertyChange(LAST_PLAYED_MOVE_PROPERTY, null, this.lastPlayedMove);
        this.getAndFirePossibleMoves();
      }
      else throw new GameException("Le Move n'a pas été appliqué au Board.") // A étoffer
    }

    /**
     * Permet de récupérer les moves possibles sur un Board via Rule et de les notifier
     */
    private void getAndFirePossibleMoves(){
      //Demander à Rule la liste Possible des Moves
      if(this.possiblesMoves.isEmpty()){
        this.propertyChangeSupport.firePropertyChange(GAME_OVER_PROPERTY, null, null);
      }
      else{
        this.propertyChangeSupport.firePropertyChange(POSSIBLE_MOVES_PROPERTY, null, null);
        this.waitToRegisterMoves();
      }
    }

    /**
     * Permet de lancer un thread attendant que les moves soit register
     */
    private void waitToRegisterMoves(){
      Thread thread = new Thread(new Runnable(){
        public void run(){
          registeredMoves = new ArrayList<Move>();
          ableToRegisterMoves = true;
          Thread.sleep((int)this.playingTime.getSeconds()*1000);
          ableToRegisterMoves = false;
          chooseMoveToApply();
        }
      });
      thread.start();
    }

    /**
     * Permet de choisir le move à effectuer par rapport à ceux qui ont été register et appel la méthode applyMove
     */
    private void chooseMoveToApply(){
      //Choisir le move à effectuer
      Move moveAEffectuer = null; //temporaire
      this.applyMove(moveAEffectuer);
    }
}
