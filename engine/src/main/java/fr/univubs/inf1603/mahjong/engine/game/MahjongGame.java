package fr.univubs.inf1603.mahjong.engine.game;

import fr.univubs.inf1603.mahjong.engine.rule.GameRule;
import fr.univubs.inf1603.mahjong.engine.rule.Wind;
import java.beans.PropertyChangeSupport;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.UUID;

/**
 * Cette classe permet de représenter une partie de Mahjong
 *
 * @author COGOLUEGNES Charles
 */
public class MahjongGame implements Game {

    private Wind[] playerWind;
    private GameRule rule;
    private MahjongBoard board;
    private Move lastPlayedMove;

    private Duration stealingTime;
    private Duration playingTime;

    private ArrayList<Move> registeredMoves;
    private ArrayList<Move> possiblesMoves;
    private UUID uuid;
    private boolean ableToRegisterMoves;
    
    private int[] playerPoints;
    
    /**
     * This is the full constructor of MahjongGame, allowing to initialize all of its fields
     * @param rule Rules of this game
     * @param board Current state of the board
     * @param lastPlayedMove The last played move of this game
     * @param stealingTime The time players have to decide if they can steal a discarded tile
     * @param playingTime This players have to decide what to discard
     * @param uuid This game's UUID
     * @throws GameException
     */
    public MahjongGame(GameRule rule, MahjongBoard board, Move lastPlayedMove, Duration stealingTime, Duration playingTime,int[] playerPoints,UUID uuid) throws GameException{
        this.rule = rule;
        this.board = board;
        this.lastPlayedMove = lastPlayedMove;
        this.stealingTime = stealingTime;
        this.playingTime = playingTime;
        this.uuid = uuid;
        this.playerPoints = playerPoints;
    }
    
    /**
     * This is a constructor of MahjongGame
     * @param rule Rules of this game
     * @param stealingTime The time players have to decide if they can steal a discarded tile
     * @param playingTime This players have to decide what to discard
     */
    public MahjongGame(GameRule rule, Duration stealingTime, Duration playingTime){
        this.rule = rule;
        this.stealingTime = stealingTime;
        this.playingTime = playingTime;    

        this.board = new MahjongBoard(Wind.WEST);
        this.lastPlayedMove = null;
        this.uuid = UUID.randomUUID();
        this.playerPoints = new int[4];        
    }
    
    @Deprecated
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
    public synchronized void registerMove(Move move) throws GameException {
        if(this.ableToRegisterMoves){
          this.registeredMoves.add(move);
        }
        else{
            throw new GameException("Impossible de register le move hors du temps imparti.");
        }
    }

    @Override
    public Board getBoard(Wind wind) throws GameException {
        if(wind == null){
            return this.board;
        }
        return this.board.getViewFromWind(wind);
    }
    
    public Board getBoard(){
        return this.board;
    }
    
    


    @Override
    public ArrayList<Move> getPossibleMoves() {
        EnumMap<Wind, Collection<Move>> res = this.rule.getBoardRule().findValidMoves(this.board, this.lastPlayedMove);
        ArrayList<Move> ret = new ArrayList<>();
        for(Collection c : res.values()){
            ret.addAll(c);
        }
        return ret;
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
    public int getPlayerPoints(int player) {
        return this.playerPoints[player];
    }

    
    @Override
    public int getPlayerPoints(Wind wind) {
        int indexOfWind=0;
        do{
          indexOfWind++;  
        }while(this.playerWind[indexOfWind] != wind);
        return this.playerPoints[indexOfWind];
    }

    @Override
    public int[] getAllPlayerPoints() {
        return this.playerPoints;
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
        return new ArrayList<>(this.rule.getBoardRule().findValidMoves(this.board, this.lastPlayedMove).get(wind));
    }

    @Override
    public ArrayList<Move> getPossibleMoves(int player) throws GameException {
        return getPossibleMoves(playerWind[player]);
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
      else throw new GameException("Le Move n'a pas été appliqué au Board."); // A étoffer
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
    private synchronized void waitToRegisterMoves(){
      Thread thread = new Thread(new Runnable(){
        public void run(){
          registeredMoves = new ArrayList<Move>();
          ableToRegisterMoves = true;
          try{
              Thread.sleep((int)playingTime.getSeconds()*1000);
              ableToRegisterMoves = false;
              chooseMoveToApply();
          }
          catch (InterruptedException ie){
              ableToRegisterMoves = false;
              ie.printStackTrace();
          }
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
      try{
        this.applyMove(moveAEffectuer);
      }
      catch (GameException ge){
          ge.printStackTrace();
      }
    }
}
