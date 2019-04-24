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
    
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    /**
     * This is the full constructor of MahjongGame, allowing to initialize all of its fields
     * @param rule Rules of this game
     * @param board Current state of the board
     * @param lastPlayedMove The last played move of this game
     * @param stealingTime The time players have to decide if they can steal a discarded tile
     * @param playingTime This players have to decide what to discard
     * @param uuid This game's UUID
     * @param playerWind The wind according to the player
     * @throws GameException
     */
    public MahjongGame(GameRule rule, MahjongBoard board, Move lastPlayedMove, Duration stealingTime, Duration playingTime,int[] playerPoints,UUID uuid, Wind[] playerWind) throws GameException{
        this.rule = rule;
        this.board = board;
        this.lastPlayedMove = lastPlayedMove;
        this.stealingTime = stealingTime;
        this.playingTime = playingTime;
        this.uuid = uuid;
        this.playerPoints = playerPoints;
        this.playerWind = playerWind;
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
        if(this.playerWind == null) this.playerWind = this.rule.getBoardRule().getPlayerOrder();
        if(this.board == null) this.board = this.rule.getBoardRule().distributeTiles(this.rule.getBoardRule().buildWall());
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
     * @throws GameException quand throw GameException dans board.applyMove
     */
    private void applyMove(Move move) throws GameException{
      try{
        this.board.applyMove(move);
        this.lastPlayedMove = move;
        this.propertyChangeSupport.firePropertyChange(LAST_PLAYED_MOVE_PROPERTY, null, this.lastPlayedMove);
        this.getAndFirePossibleMoves();
      }
      catch (GameException ge){
        throw new GameException(ge.getMessage());
      }
    }

    /**
     * Permet de récupérer les moves possibles sur un Board via Rule et de les notifier
     */
    private void getAndFirePossibleMoves(){
      this.possiblesMoves = new ArrayList<Move>();
      EnumMap<Wind, Collection<Move>> validMoves = this.rule.getBoardRule().findValidMoves(this.board, this.lastPlayedMove);
      for(Wind w : validMoves.keySet()){
          for(Move m : validMoves.get(w)){
              this.possiblesMoves.add(m);
          }
      }
      if(this.rule.getBoardRule().isGameFinished(this.board, this.lastPlayedMove)){
        this.exitGame(0,"Fin de la partie.");
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
          catch(GameException ge){
              exitGame(1, ge.getMessage());
          }
        }
      });
      thread.start();
    }

    /**
     * Permet de choisir le move à effectuer par rapport à ceux qui ont été register et appel la méthode applyMove
     * @throws GameException quand applyMove throw une GameException
     */
    private void chooseMoveToApply() throws GameException{
      Move moveToApply = this.possiblesMoves.get(0);
      if(this.registeredMoves.isEmpty()){
          for(Move move : this.possiblesMoves){
              if(move.getPriority() < moveToApply.getPriority()) moveToApply = move;
          }
      }
      else{
        moveToApply = this.registeredMoves.get(0);
        for(Move move : this.registeredMoves){
            if(move.getPriority() < moveToApply.getPriority()) moveToApply = move;
        }
      }
      try{
        this.applyMove(moveToApply);
      }
      catch (GameException ge){
          throw new GameException(ge.getMessage());
      }
    }
    
    /**
     * Les actions à effectuer lors d'une interruption de partie
     * @param state L'état de sortie (0 = sortie sans erreur, 1 = sortie avec erreur)
     * @param msg Le message à afficher
     */
    private void exitGame(int state, String msg){
        switch (state) {
            case 0:
                this.propertyChangeSupport.firePropertyChange(GAME_OVER_PROPERTY, null, msg);
                break;
            case 1:
                System.err.println(msg);
                break;
            default:
                System.err.println("L'état envoyé n'est pas supporté.");
        }
    }
}
