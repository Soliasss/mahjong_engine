package fr.univubs.inf1603.mahjong.engine.game;

import fr.univubs.inf1603.mahjong.engine.rule.*;
import fr.univubs.inf1603.mahjong.Wind;

import java.beans.PropertyChangeSupport;
import java.time.Duration;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private Timer playingTimer;

    private ArrayList<Move> registeredMoves;
    private ArrayList<Move> possiblesMoves;
    private UUID uuid;
    private boolean ableToRegisterMoves;

    private int[] playerPoints;

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * This is the full constructor of MahjongGame, allowing to initialize all
     * of its fields
     *
     * @param rule Rules of this game
     * @param board Current state of the board
     * @param lastPlayedMove The last played move of this game
     * @param stealingTime The time players have to decide if they can steal a
     * discarded tile
     * @param playingTime This players have to decide what to discard
     * @param playerPoints The points's number of the player
     * @param uuid This game's UUID
     * @param playerWind The wind according to the player
     * @throws GameException
     */
    public MahjongGame(GameRule rule, MahjongBoard board, Move lastPlayedMove, Duration stealingTime, Duration playingTime, int[] playerPoints, UUID uuid, Wind[] playerWind) throws GameException {
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
     *  @param rule Rules of this game
     * @param stealingTime The time players have to decide if they can steal a
     * discarded tile
 * @param playingTime This players have to decide what to discard 
     */
    public MahjongGame(GameRule rule, Duration stealingTime, Duration playingTime) {
        this(UUID.randomUUID(), rule, stealingTime, playingTime);
    }

    /**
     * This is a constructor of MahjongGame
     *
     * @param uuid {@link UUID} of the game
     * @param rule Rules of this game
     * @param stealingTime The time players have to decide if they can steal a
     * discarded tile
     * @param playingTime This players have to decide what to discard
     */
    public MahjongGame(UUID uuid, GameRule rule, Duration stealingTime, Duration playingTime) {
        this.rule = rule;
        this.stealingTime = stealingTime;

        this.playingTime = playingTime;

        this.lastPlayedMove = null;
        this.board = null;
        this.uuid = uuid;
        this.playerPoints = new int[4];
        this.playerWind = this.rule.getBoardRule().getPlayerOrder();
        
    }

    @Deprecated
    public MahjongGame(GameRule rule) throws GameException {
        this.rule = rule;
        this.board = null;
        this.uuid = UUID.randomUUID();
        this.playingTime = Duration.ofSeconds(5);
    }

    @Override
    public Board getBoard(Wind wind) throws GameException {
        if (wind == null) {
            return this.board;
        }
        else if(this.board==null){
            return null;
        }
        return this.board.getViewFromWind(wind);
    }

    public Board getBoard() {
        return this.board;
    }

    @Override
    public ArrayList<Move> getPossibleMoves() {
        if(this.possiblesMoves==null){
            return new ArrayList<>();
        }
        return this.possiblesMoves;
    }

    @Override
    public ArrayList<Move> getPossibleMoves(Wind wind) {
        Map<Wind, Collection<Move>> moves = new HashMap<>(this.rule.getBoardRule().findValidMoves(this.board, this.lastPlayedMove));
        if (moves.containsKey(wind))
            return new ArrayList<>(moves.get(wind));
        else
            return new ArrayList<>();
    }

    @Override
    public ArrayList<Move> getPossibleMoves(int player) throws GameException {
        return getPossibleMoves(playerWind[player]);
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
        if(this.board==null){
            return null;
        }
        return this.board.getCurrentWind();
    }

    @Override
    public int getPlayerPoints(int player) {
        return this.playerPoints[player];
    }

    @Override
    public int getPlayerPoints(Wind wind) {
        int indexOfWind = 0;
        do {
            indexOfWind++;
        } while (this.playerWind[indexOfWind] != wind);
        return this.playerPoints[indexOfWind];
    }

    @Override
    public int[] getAllPlayerPoints() {
        return this.playerPoints;
    }

    @Override
    public Wind getPlayerWind(int player) throws GameException {
        if (player < 0 || player > 3) {
            throw new GameException("The player you are asking the wind from doesn't exist. There is only 4 player describs by 0 1 2 3 .");
        }
        Wind wind = this.playerWind[player];
        if (wind == null) {
            throw new GameException("The wind of a player cannot be null.");
        }
        return wind;
    }

    @Override
    public int getPlayerFromWind(Wind wind) throws GameException {
        int player = -1;
        for (int i = 0; i < 4; i++) {
            if (this.playerWind[i] == wind) {
                player = i;
            }
        }
        if (player == -1) {
            throw new GameException("The wind has to be in the playerWind.");
        }
        return player;
    }

    @Override
    public Wind[] getPlayerWinds() throws GameException {
        return this.playerWind;
    }

    /**
     * Permet d'effectuer le move sur le Board
     *
     * @param move Le move à effectuer
     * @throws GameException quand throw GameException dans board.applyMove
     */
    private synchronized void applyMove(Move move) throws GameException {
        try {
            System.out.println();
            this.board.applyMove(move);
            this.lastPlayedMove = move;
            this.propertyChangeSupport.firePropertyChange(LAST_PLAYED_MOVE_PROPERTY, null, this.lastPlayedMove);
        } catch (GameException ge) {
            throw new GameException(ge.getMessage());
        }
    }

    @Override
    public void launchGame() {
        if (this.playerWind == null) {
            this.playerWind = this.rule.getBoardRule().getPlayerOrder();
            //This shoudl not happen, peristance maybe missed this field
        }
        StartingWall wall = this.rule.getBoardRule().buildWall();

        if (this.board == null) {
            if (wall != null) {
                this.board = this.rule.getBoardRule().distributeTiles(wall);
            } else {
                System.err.println("Wall filled by gamerule : " + rule.getName() + " is null");
            }
        }
        this.registeredMoves = new ArrayList<>();
        getAndFirePossibleMoves();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Tasking");
//                ableToRegisterMoves = false;
                try {
                    chooseMoveToApply();
                } catch (GameException ex) {
                    System.err.println("Unable to choose move to play : " + ex);
                }
                getAndFirePossibleMoves();
                //playingTimer.schedule(this, playingTime.toMillis());
            }
        };
        playingTimer = new Timer();
        playingTimer.schedule(task, this.playingTime.toMillis(), this.playingTime.toMillis());

    }

    @Override
    public synchronized void registerMove(Move move) throws GameException {
        //      if(this.ableToRegisterMoves){
        boolean matchAlreadyPlayed = false;
        for (Move p : this.registeredMoves) {
            if (p.equals(move)) {
                matchAlreadyPlayed = true;
                break;
            }
        }
        if(!matchAlreadyPlayed){
            boolean match = false;
            for (Move p : this.possiblesMoves) {
                if (p.equals(move)) {
                    match = true;
                    break;
                }
            }
            if (match) {
                System.out.println("Registered move is : " + move);
                this.registeredMoves.add(move);
            } else {
                throw new MoveException("Move " + move + " apprears to be forged");
            }
        }else{
            throw new GameException("Trying to register an already registered move");
        }
        //    }
        /*  else{
            throw new GameException("Impossible de register le move hors du temps imparti.");
        }*/
    }

    /**
     * Permet de récupérer les moves possibles sur un Board via Rule et de les
     * notifier
     */
    private synchronized void getAndFirePossibleMoves() {
        this.possiblesMoves = new ArrayList<>();
        this.registeredMoves = new ArrayList<>();
        EnumMap<Wind, Collection<Move>> validMoves = this.rule.getBoardRule().findValidMoves(this.board, this.lastPlayedMove);
        for (Wind w : validMoves.keySet()) {
            for (Move m : validMoves.get(w)) {
                this.possiblesMoves.add(m);
            }
        }
        //System.out.println("New available moves :\n"+possiblesMoves.toString());
        if (this.rule.getBoardRule().isGameFinished(this.board, this.lastPlayedMove)) {
            this.computeScore();
            this.exitGame(0, "Fin de la partie.");
        } else {
            this.propertyChangeSupport.firePropertyChange(POSSIBLE_MOVES_PROPERTY, null, this.possiblesMoves);
        }
    }

    /**
     * Permet de choisir le move à effectuer par rapport à ceux qui ont été
     * register et appel la méthode applyMove
     *
     * @throws GameException quand applyMove throw une GameException
     */
    private synchronized void chooseMoveToApply() throws GameException {
        if (this.possiblesMoves == null) {
            System.out.println("Wow we did not get the moves for you");
        }

        if (this.possiblesMoves.isEmpty()) {
            System.out.println("Wow you can't play");
        }

        Move moveToApply = this.possiblesMoves.get(0);
        if (this.registeredMoves == null) {
            System.out.println("Wow we did not create a space to register move, it's our bad");
        }
        if (this.registeredMoves.isEmpty()) {
            System.out.println("Move choosen by the game");
            for (Move move : this.possiblesMoves) {
                if (move.getPriority() < moveToApply.getPriority()) {
                    moveToApply = move;
                }
            }
        } else {
            moveToApply = this.registeredMoves.get(0);
            for (Move move : this.registeredMoves) {
                if (move.getPriority() < moveToApply.getPriority()) {
                    moveToApply = move;
                }
            }
        }
        try {
            this.applyMove(moveToApply);
            
        } catch (GameException ge) {
            throw new GameException(ge.getMessage());
        }
    }

    /**
     * Les actions à effectuer lors d'une interruption de partie
     *
     * @param state L'état de sortie (0 = sortie sans erreur, 1 = sortie avec
     * erreur)
     * @param msg Le message à afficher
     */
    private void exitGame(int state, String msg) {
        this.playingTimer.cancel();
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
        try {
            BoardRule br = this.rule.getBoardRule();
            ScoringSystem ss = this.rule.getScoringSystem();
            Wind playerWind = this.lastPlayedMove.getWind();
            int highScore = 0;
            
            Integer idLastTile = 0;
            for(Integer inte : this.lastPlayedMove.getPath().keySet()) idLastTile = inte;
            GameTileInterface winningTileInterface=null;
            try {
                winningTileInterface = this.board.getTile(idLastTile);
            } catch (GameException ex) {
                Logger.getLogger(MahjongGame.class.getName()).log(Level.SEVERE, null, ex);
            }
            GameTile winningTile = null;
            if(winningTileInterface instanceof GameTile) winningTile = (GameTile) winningTileInterface;
            Wind wind = lastPlayedMove.getWind();
            ArrayList<Combination> hand = new ArrayList<>();
            GameTile[] tab = new GameTile[board.getTileZone("Hand"+wind.getName()).getTiles().size()];
            int j=0;
            for(GameTileInterface gti : board.getTileZone("Hand"+wind.getName()).getTiles()){
                GameTile gt = null;
                if(gti instanceof GameTile) gt = (GameTile) gti;
                if(gt != null) tab[j] = gt;
                j++;
            }
            InternationalCombinationFactory factory = new InternationalCombinationFactory();
            hand.add(factory.newCombination(tab));
            ArrayList<Combination> concealed = new ArrayList<>();
            
            ArrayList<Combination> melds = new ArrayList<>();
            for(int i=0; i<4; i++){
                tab = new GameTile[board.getTileZone("Meld"+wind.getName()+ i).getTiles().size()];
                int k = 0;
                for(GameTileInterface gti : board.getTileZone("Meld"+wind.getName()+ i).getTiles()){
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
            ArrayList<GameTile> supremeHonors = new ArrayList<>();
            for(GameTileInterface gti : supremeHonorsInterface){
                GameTile gt = null;
                if(gti instanceof GameTile) gt = (GameTile) gti;
                if(gt != null) supremeHonors.add(gt);
            }
            boolean drawnFromWall = false;
            boolean takenFromDiscard = false;
            Wind roundWind = Wind.EAST;
            PlayerSituation ps = new PlayerSituation(winningTile, null,concealed, melds, supremeHonors, drawnFromWall, takenFromDiscard, roundWind, playerWind);
            
            int score = 0;
            Collection<PlayerSet> playerSets =  ss.createSetsFromSituation(ps);
            for(PlayerSet pset : playerSets){
                Collection<IdentifiedPattern> patterns = ss.identifyPatterns(pset);
                Collection<Collection<IdentifiedPattern>> patternsCollections = ss.splitIncompatiblePatterns(patterns);
                for(Collection<IdentifiedPattern> c : patternsCollections){
                    score = ss.computeScore(c);
                    if(score > highScore) highScore = score;
                }
                
                for (int i = 0; i < this.playerWind.length; i++) {
                    if (this.playerWind[i].getSymbol() == playerWind.getSymbol()) {
                        this.playerPoints[i] = highScore;
                        break;
                    }
                }
            }
        } catch (GameException | RulesException ex) {
            Logger.getLogger(MahjongGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
