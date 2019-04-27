package fr.univubs.inf1603.mahjong.engine.game;
import fr.univubs.inf1603.mahjong.engine.rule.*;
import java.beans.PropertyChangeSupport;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

/**
 * Cette classe permet de représenter une partie de Mahjong
 *
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
     * @param playerPoints The points's number of the player
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

        this.ableToRegisterMoves=false;
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


        this.lastPlayedMove = null;
        this.board = null;
        this.uuid = UUID.randomUUID();
        this.playerPoints = new int[4];
        this.playerWind = null;

        this.ableToRegisterMoves=false;
    }

    @Deprecated
    public MahjongGame(GameRule rule) throws GameException {
        this.rule = rule;
        this.board = null;
        this.uuid = UUID.randomUUID();
        this.playingTime = Duration.ofSeconds(5);
        this.ableToRegisterMoves = false;
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

    @Override
    public void launchGame() {
        if(this.playerWind == null){
            this.playerWind = this.rule.getBoardRule().getPlayerOrder();
        }
        StartingWall wall = this.rule.getBoardRule().buildWall();

        if(this.board == null){
            if(wall !=null){
                this.board = this.rule.getBoardRule().distributeTiles(wall);
            }else{
                System.err.println("Wall filled by gamerule : "+rule.getName()+" is null");
            }
        }

        getAndFirePossibleMoves();
    }

    @Override
    public void registerMove(Move move) throws GameException {
        if(this.ableToRegisterMoves){
          this.registeredMoves.add(move);
        }
        else{
            throw new GameException("Impossible de register le move hors du temps imparti.");
        }
    }



    /**
     * Permet de récupérer les moves possibles sur un Board via Rule et de les notifier
     */
    private void getAndFirePossibleMoves(){
      this.possiblesMoves = new ArrayList<>();
      EnumMap<Wind, Collection<Move>> validMoves = this.rule.getBoardRule().findValidMoves(this.board, this.lastPlayedMove);
      for(Wind w : validMoves.keySet()){
          for(Move m : validMoves.get(w)){
              this.possiblesMoves.add(m);
          }
      }

      if(this.rule.getBoardRule().isGameFinished(this.board, this.lastPlayedMove)){
        this.computeScore();
        this.exitGame(0,"Fin de la partie.");
      }
      else{
        this.propertyChangeSupport.firePropertyChange(POSSIBLE_MOVES_PROPERTY, null, null);
      }

      TimerTask recalculateMoves = new TimerTask() {
          @Override
          public void run() {

          }
      };

      Timer timer = new Timer();
      timer.schedule(recalculateMoves, playingTime.toSeconds());

    }

    @Override
    public ArrayList<Move> getPossibleMoves() {
        return this.possiblesMoves;
    }

    @Override
    public ArrayList<Move> getPossibleMoves(Wind wind) throws GameException {
        ArrayList<Move> mlist = new ArrayList<>();
        for(Move m : this.possiblesMoves){
            if(m.getWind()==wind){
                mlist.add(m);
            }
        }
        return mlist;
    }

    @Override
    public ArrayList<Move> getPossibleMoves(int player) throws GameException {
        return getPossibleMoves(playerWind[player]);
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

    /**
     * Permet de calculer le score du joueur qui a fait Mahjong
     */
    private void computeScore(){
      BoardRule br = this.rule.getBoardRule();
      ScoringSystem ss = this.rule.getScoringSystem();
      Wind playerWind = this.lastPlayedMove.getWind();
      int highScore = 0;

      Integer idLastTile = 0;
      for(Integer inte : this.lastPlayedMove.getPath().keySet()) idLastTile = inte;
      GameTileInterface winningTileInterface = this.board.getTile(idLastTile);
      GameTile winningTile = null;
      if(winningTileInterface instanceof GameTile) winningTile = (GameTile) winningTileInterface;

      ArrayList<Combination> hand = new ArrayList<Combination>();
      GameTile[] tab = new GameTile[board.getTileZone("Hand"+wind.getName()).getTiles().size()];
      int j=0;
      for(GameTileInterface gti : board.getTileZone("Hand"+wind.getName()).getTiles()){
          GameTile gt = null;
          if(gti instanceof GameTile) gt = (GameTile) gti;
          if(gt != null) tab[j] = gt;
          j++;
      }
      try{
          hand.add(factory.newCombination(tab));
      }catch(RulesException ex){

      }
      ArrayList<Combination> concealed = new ArrayList<Combination>();

      ArrayList<Combination> melds = new ArrayList<Combination>();
      for(int i=0; i<4; i++){
          tab = new GameTile[board.getTileZone("Meld"+wind.getName()+String.valueOf(i)).getTiles().size()];
          int k = 0;
          for(GameTileInterface gti : board.getTileZone("Meld"+wind.getName()+String.valueOf(i)).getTiles()){
              GameTile gt = null;
              if(gti instanceof GameTile) gt = (GameTile) gti;
              if(gt != null) tab[k] = gt;
              k++;
          }
          try{
              melds.add(factory.newCombination(tab));
          }catch(RulesException ex){

          }
      }
      ArrayList<GameTileInterface> supremeHonorsInterface = board.getTileZone("Supreme"+wind.getName()).getTiles();
      ArrayList<GameTile> supremeHonors = new ArrayList<GameTile>();
      for(GameTileInterface gti : supremeHonorsInterface){
          GameTile gt = null;
          if(gti instanceof GameTile) gt = (GameTile) gti;
          if(gt != null) supremeHonors.add(gt);
      }
      boolean drawnFromWall = false;
      boolean takenFromDiscard = false;

      PlayerSituation ps = new PlayerSituation(winningTile, hand, concealed, melds, supremeHonors, drawnFromWall, takenFromDiscard, roundWind, playerWind);

      int score = 0;
      Collection<PlayerSet> playerSets =  ss.createSetsFromSituation(ps);
      for(PlayerSet pset : playerSets){
        Collection<IdentifiedPattern> patterns = ss.identifyPatterns(pset);
        Collection<Collection<IdentifiedPattern>> patternsCollections = ss.splitIncompatiblePatterns(patterns);
        for(Collection<IdentifiedPattern> c : patternsCollections){
          score = ss.computeScore(c);
          if(score > highScore) highScore = score;
        }
      }

      for(int i=0; i<this.playerWind.length; i++){
        if(this.playerWind[i].getSymbol() == playerWind.getSymbol()){
          this.playerPoints[i] = highScore;
          break;
        }
      }
    }
}
