package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.GameException;
import fr.univubs.inf1603.mahjong.engine.game.GameTile;
import fr.univubs.inf1603.mahjong.engine.game.GameTileInterface;
import fr.univubs.inf1603.mahjong.engine.game.MahjongBoard;
import fr.univubs.inf1603.mahjong.engine.game.Move;
import fr.univubs.inf1603.mahjong.engine.game.MoveException;
import fr.univubs.inf1603.mahjong.engine.game.TileZoneIdentifier;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 */
public class InternationalBoardRule implements BoardRule{
    
    private int firstDieRoll = 0;
    private int previousLastMoveIsKong = 0;
    
    public InternationalBoardRule(){
        this.firstDieRoll = 0;
    }
    
    @Override
    public Wind[] getPlayerOrder() {
        Wind[] internationalOrder = {Wind.EAST,Wind.NORTH,Wind.WEST,Wind.SOUTH};
        Wind[] playerOrder = new Wind[4];
        Random randomGenerator = new Random();
	int a = randomGenerator.nextInt(3);
        int b = 0;
        while(a<4){
            playerOrder[a] = internationalOrder[b];
            a++;
            b++;
        }
        a=0;
        while(b<4){
            playerOrder[a] = internationalOrder[b];
            a++;
            b++;
        }
        return playerOrder;
    }

    /**
     * Retourne le vent ou se trouve la breche
     * @return Le vent ou se trouve la breche
     */
    public Wind chooseWindWall(){
        Wind ret = null;
        Random randomGenerator = new Random();
	int random = randomGenerator.nextInt(11)+2;
        this.firstDieRoll = random;
        switch(random){
            case 5:
            case 9:
                ret = Wind.EAST;
                break;
            case 2:
            case 6:
            case 10:
                ret = Wind.SOUTH;
                break;
            case 3:
            case 7:
            case 11:
                ret = Wind.WEST;
                break;
            case 4:
            case 8:
            case 12:
                ret = Wind.NORTH;
        }
        return ret;
    }
    
    /**
     * Retourne l'index ou se trouve la breche
     * @return L'index ou se trouve la breche
     */
    public int chooseStartingHeap(){
        Random randomGenerator = new Random();
	int random = randomGenerator.nextInt(11)+2;
        int startingHeap = random + this.firstDieRoll;
        return startingHeap;
    }
    
    @Override
    public StartingWall buildWall() {
        ArrayDeque<GameTile> listTile = new ArrayDeque<GameTile>();
        
        ArrayList<AbstractTile> allTiles = new ArrayList<AbstractTile> ();
        
        for (InternationalTiles a : InternationalTiles.values()) {
            if (a.getTile() instanceof CommonTile) {
                for(int i = 0; i<4; i++){
                    allTiles.add(a.getTile());
                }
            }else{
                allTiles.add(a.getTile());
            }
        }
        int i=0;
        while(!allTiles.isEmpty() && i<144){
            Random randomGenerator = new Random();
            int random = randomGenerator.nextInt(allTiles.size());
            listTile.add(new GameTile(i,allTiles.get(random)));
            allTiles.remove(random);
            i++;
        }
        
        return new StartingWall(this.chooseWindWall(),this.chooseStartingHeap(),listTile);
    }

    @Override
    public MahjongBoard distributeTiles(StartingWall startingWall) {
        MahjongBoard board = new MahjongBoard(startingWall.getStartingSide());
        try {
            board.getTileZone(TileZoneIdentifier.Wall).getTiles().addAll(startingWall.getCut());
        } catch (GameException ex) {
            Logger.getLogger(InternationalBoardRule.class.getName()).log(Level.SEVERE, null, ex);
        } 
        for(int i = 0;i<4;i++){//This is the draw moves they draw 13 tiles for each player in one swipe
            HashMap<Integer,TileZoneIdentifier> path = new HashMap<>();
            for(int j = 0;j<13;j++){
                try {
                    Integer idGameTile = board.getTileZone(TileZoneIdentifier.Wall).getTiles().get(0).getGameID();
                    path.put(idGameTile, TileZoneIdentifier.getIdentifierFromNormalizedName("Hand"+Wind.values()[i].getName()));
                    Move drawMove=null;
                    try {
                        drawMove = new Move(Wind.values()[i], 0, path,new HashMap<Integer, Boolean>());
                    } catch (MoveException ex) {
                        Logger.getLogger(SillyBoardRule.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    board.applyMove(drawMove);
                } catch (GameException ex) {
                    Logger.getLogger(InternationalBoardRule.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //Pioche la 14 tuile du joueur placer a l EST
        try {
            HashMap<Integer,TileZoneIdentifier> path = new HashMap<>();
            Integer idGameTile;
            idGameTile = board.getTileZone(TileZoneIdentifier.Wall).getTiles().get(0).getGameID();
            path.put(idGameTile, TileZoneIdentifier.HandEast);
            board.applyMove(new Move(Wind.EAST, 0, path,new HashMap<Integer, Boolean>()));
        } catch (GameException ex) {
            Logger.getLogger(InternationalBoardRule.class.getName()).log(Level.SEVERE, null, ex);
        }
        return board;
    }

    @Override
    public boolean isMoveValid(MahjongBoard board, Move lastMove, Move move) {
        return findValidMoves(board, lastMove).get(move.getWind()).contains(move);
    }

    /**
     * Recherche la premiere zone de meld disponible d'un joueur
     * @param board Le board du jeu
     * @param wind Le vent du joueur dont on veut vérifier les zones de meld disponible
     * @return Un TileZoneIdentifier representant la zone disponible
     * @throws GameException 
     */
    public TileZoneIdentifier getMeldAvailable(MahjongBoard board, Wind wind) throws GameException{
        TileZoneIdentifier tzi = null;
        if(board.getTileZone("Meld"+wind.getName()+"0").getTiles().isEmpty()){
            tzi = board.getTileZone("Meld"+wind.getName()+"0").getIdentifier();
        }else if(board.getTileZone("Meld"+wind.getName()+"1").getTiles().isEmpty()){
            tzi = board.getTileZone("Meld"+wind.getName()+"1").getIdentifier();
        }else if(board.getTileZone("Meld"+wind.getName()+"2").getTiles().isEmpty()){
            tzi = board.getTileZone("Meld"+wind.getName()+"2").getIdentifier();
        }else if(board.getTileZone("Meld"+wind.getName()+"3").getTiles().isEmpty()){
            tzi = board.getTileZone("Meld"+wind.getName()+"3").getIdentifier();
        }else {
            throw new GameException("It should already be a Majhong");
        }
        return tzi;
    }
    /**
     * Renvoie tous les mouvements de defausse possible
     * @param board Le board de la partie
     * @param wind Le vent du joueur dont on veut trouver les defausse possibles
     * @return Une liste vide si pas de tuile dans la main ou contenant les mouvements de defausse possible
     * @throws GameException 
     */

    public ArrayList<Move> possibleMoveDiscard(MahjongBoard board, Wind wind) throws GameException{
        ArrayList<Move> moves = new ArrayList<Move>();
        TileZoneIdentifier tziDiscard = board.getTileZone("Discard" + wind.getName()).getIdentifier();
        for(GameTileInterface gti : board.getTileZone("Hand" + wind.getName()).getTiles()){
            HashMap<Integer, TileZoneIdentifier> path = new HashMap<>();
            path.put(gti.getGameID(), tziDiscard);
            moves.add( new Move(wind, 0, path,new HashMap<Integer, Boolean>()));
        }
        return moves;
    }
    /**
     * Verifie si un joueur peut faire une combinaison de 3 tuiles
     * @param board Le board de la partie
     * @param wind Le vent du joueur dont on veut verifier les moves possibles
     * @param tiles La main du joueur
     * @return Une liste vide si pas de movements ou contenant les Move possibles
     * @throws GameException 
     */
    public ArrayList<Move> possibleMove3Tiles(MahjongBoard board, Wind wind, ArrayList<GameTile> tiles) throws GameException{        
        int size = tiles.size();
        Combination combi;
        CombinationFactory factory = new CombinationFactory();
        ArrayList<Move> move = null;
        TileZoneIdentifier tzi = getMeldAvailable(board,wind);
        for(int i=0; i<size; i++){
            for(int j=i+1; j<size; j++){
                combi = null;
                for(int k=j+1; k<size; k++){
                    if(i!=j && j!=k){
                        try{
                            combi = factory.newCombination(tiles.get(i),tiles.get(j),tiles.get(k));
                            if(combi.isPung() || combi.isChow()){
                                HashMap<Integer, TileZoneIdentifier> path = new HashMap<>();
                                path.put(tiles.get(i).getGameID(), tzi);
                                path.put(tiles.get(j).getGameID(), tzi);
                                path.put(tiles.get(k).getGameID(), tzi);
                                move.add(new Move(wind, 0, path,new HashMap<Integer, Boolean>()));
                            }
                        } catch (RulesException ex) {
                            Logger.getLogger(InternationalBoardRule.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        }
        return move;
    }
    
    /**
     * Verifie si un joueur peut faire une combinaison de 4 tuiles
     * @param board Le board de la partie
     * @param wind Le vent du joueur dont on veut verifier les moves possibles
     * @param tiles La main du joueur
     * @return Une liste vide si pas de movements ou contenant les Move possibles
     * @throws GameException 
     */
    public ArrayList<Move> possibleMove4Tiles(MahjongBoard board, Wind wind, ArrayList<GameTile> tiles) throws GameException{        
        int size = tiles.size();
        Combination combi;
        CombinationFactory factory = new CombinationFactory();
        ArrayList<Move> move = null;
        TileZoneIdentifier tzi = getMeldAvailable(board,wind);
        for(int i=0; i<size; i++){
            for(int j=i+1; j<size; j++){
                for(int k=j+1; k<size; k++){
                    combi = null;
                    for(int l=k+1; l<size; l++){
                        if(i!=j && j!=k){
                            try{
                                combi = factory.newCombination(tiles.get(i), tiles.get(j), tiles.get(k), tiles.get(l));
                                if(combi.isKong()){
                                    HashMap<Integer, TileZoneIdentifier> path = new HashMap<>();
                                    path.put(tiles.get(i).getGameID(), tzi);
                                    path.put(tiles.get(j).getGameID(), tzi);
                                    path.put(tiles.get(k).getGameID(), tzi);
                                    path.put(tiles.get(l).getGameID(), tzi);
                                    move.add(new Move(wind, 0, path,new HashMap<Integer, Boolean>()));
                                }
                            } catch (RulesException ex) {
                                Logger.getLogger(InternationalBoardRule.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
        }
        return move;
    }
    
    /**
     * Recherche les vols de la derniere tuile qui a été deffausser
     * @param board Le board du game
     * @param wind Le vent du joueur dont on veut verifier les vols possibles
     * @param gameTile La tuile defausser
     * @param tiles La main du joueur dont on veut verifier les vols possibles
     * @return Une liste de Move vide si pas de mouvement ou contenant les moves de vols possibles
     * @throws GameException 
     */
    public ArrayList<Move> possibleSteal(MahjongBoard board, Wind wind, GameTile gameTile, ArrayList<GameTile> tiles) throws GameException{
        ArrayList<Move> moveSteal = new ArrayList<Move>();
        int size = tiles.size();
        ArrayList<GameTile> gtArray = null;
        for(int i=0; i<size; i++){
            for(int j=i+1; j<size; j++){
                gtArray = new ArrayList<GameTile>();
                gtArray.add(tiles.get(i));
                gtArray.add(tiles.get(j));
                gtArray.add(gameTile);
                moveSteal.addAll(this.possibleMove3Tiles(board, wind, tiles));
                for(int k=j+1; k<size; k++){
                    gtArray = new ArrayList<GameTile>();
                    gtArray.add(tiles.get(i));
                    gtArray.add(tiles.get(j));
                    gtArray.add(tiles.get(k));
                    gtArray.add(gameTile);
                    moveSteal.addAll(this.possibleMove4Tiles(board, wind, tiles));
                }
            }
        }
        return moveSteal;
    }
    
    public boolean nbOfTileInHandAcceptable(MahjongBoard board, Wind wind) throws GameException{
        boolean ret = false;
        int nbTile = board.getTileZone("Meld"+wind.getName()+"0").getTiles().size();
        nbTile += board.getTileZone("Meld"+wind.getName()+"1").getTiles().size();
        nbTile += board.getTileZone("Meld"+wind.getName()+"2").getTiles().size();
        nbTile += board.getTileZone("Meld"+wind.getName()+"3").getTiles().size();        
        if(nbTile == 13) ret = true;
        return ret;
    }
            
    @Override
    public EnumMap<Wind, Collection<Move>> findValidMoves(MahjongBoard board, Move lastMove) {
        EnumMap<Wind, Collection<Move>> moves = new EnumMap<>(Wind.class);
        Wind nextWindToPlay = null;
        char lastMoveTZIFirstLetter = lastMove.getPath().get(0).getNormalizedName().charAt(0);
        try {
            
            if(lastMove.getPath().keySet().size() == 4 ) previousLastMoveIsKong = 2;
            
            //Si le dernier movement est une pioche ou un kong.
            //Si l'on vient de mettre un honneur.
            //Marche aussi pour le premier Movement grace a la pioche automatique de la tuile pour l'Est
            if( (lastMoveTZIFirstLetter == 'H' && previousLastMoveIsKong!=1) || lastMove.getPath().keySet().size() == 4  
                    || lastMoveTZIFirstLetter == 'S' ){
                nextWindToPlay = lastMove.getWind();
            }else{
                int index = (lastMove.getWind().ordinal()+1)% Wind.values().length;
                nextWindToPlay = Wind.values()[index];
            }
            moves.put(nextWindToPlay, new ArrayList<>());
            
            //Ajoute les elements defaussable, le fait de piocher, de voler et de faire des melds
            if(lastMove != null){
                
                //Si le dernier Mouvement n'est le fait d'avoir piocher
                if(lastMoveTZIFirstLetter != 'H' || previousLastMoveIsKong==1){
                    
                    //Gere la pioche
                    HashMap<Integer, TileZoneIdentifier> path = new HashMap<>();
                    TileZoneIdentifier tzi = board.getTileZone("Hand" + nextWindToPlay.getName()).getIdentifier();
                    path.put(board.getTileZone(TileZoneIdentifier.Wall).getTiles().get(0).getGameID(), tzi);                    
                    moves.get(nextWindToPlay).add(new Move(nextWindToPlay, 0, path,new HashMap<Integer, Boolean>()));
                    
                    if(previousLastMoveIsKong>0) previousLastMoveIsKong--;//Important a la suite d'un KONG

                    //Gere le vol de tuile pour faire des combinaisons            
                    if(lastMoveTZIFirstLetter == 'D'){
                        ArrayList<GameTileInterface> gtiArray = board.getTileZone("Discard" + lastMove.getWind().getName()).getTiles();
                        ArrayList<GameTile> gtArray = new ArrayList<GameTile>();
                        for(GameTileInterface gti : gtiArray){
                            GameTile gt = null;
                            if(gti instanceof GameTile) gt = (GameTile) gti;
                            if(gt != null) gtArray.add(gt);
                        }
                        for(Integer id : lastMove.getPath().keySet()){
                            GameTileInterface gti = board.getTile(id);
                            GameTile gt = null;
                            if(gti instanceof GameTile) gt = (GameTile) gti;
                            if(gt!=null){
                                for(Wind windSteal : Wind.values()){
                                    if(!windSteal.getName().equals(lastMove.getWind().getName())){
                                        ArrayList<Move> moveSteal = this.possibleSteal(board, windSteal,gt,gtArray);
                                        if(!moveSteal.isEmpty())moves.put(windSteal,moveSteal);
                                    }
                                }
                            } 
                        }
                    }
                        
                //Gere la defausse et les melds possibles dans la main apres une pioche
                } else {
                    //Discard
                    moves.get(nextWindToPlay).addAll(this.possibleMoveDiscard(board, nextWindToPlay));
                    HashMap<Integer, TileZoneIdentifier> path = new HashMap<>();
                    path.put(board.getTileZone(TileZoneIdentifier.Wall).getTiles().get(0).getGameID(), board.getTileZone("Discard"+nextWindToPlay.getName()).getIdentifier());
                    moves.get(nextWindToPlay).add(new Move(nextWindToPlay, 0, path,new HashMap<Integer, Boolean>()));
                    //PUNG et CHOW
                    ArrayList<GameTileInterface> gtiArray = board.getTileZone("Hand"+nextWindToPlay.getName()).getTiles();
                    ArrayList<GameTile> gtArray = new ArrayList<GameTile>();
                    for(GameTileInterface gti : gtiArray){
                        GameTile gt = null;
                        if(gti instanceof GameTile) gt = (GameTile) gti;
                        if(gt != null) gtArray.add(gt);
                    }
                    moves.get(nextWindToPlay).addAll(this.possibleMove3Tiles(board, nextWindToPlay, gtArray));
                    //KONG
                    moves.get(nextWindToPlay).addAll(this.possibleMove4Tiles(board, nextWindToPlay, gtArray)); 
                }
            }   
        } catch (GameException ex) {
            Logger.getLogger(InternationalBoardRule.class.getName()).log(Level.SEVERE, null, ex);
        }      
        return moves;
    }

    @Override
    public boolean isGameFinished(MahjongBoard board, Move lastMove){
        boolean finished = false;
        try {
            finished = board.getTileZone(TileZoneIdentifier.Wall).getTiles().isEmpty();
        } catch (GameException ex) {
            Logger.getLogger(InternationalBoardRule.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!finished){
            boolean noMeldFree = false;
            try {
                TileZoneIdentifier tzi1 = this.getMeldAvailable(board, Wind.WEST);
                TileZoneIdentifier tzi2 = this.getMeldAvailable(board, Wind.EAST);
                TileZoneIdentifier tzi3 = this.getMeldAvailable(board, Wind.NORTH);
                TileZoneIdentifier tzi4 = this.getMeldAvailable(board, Wind.SOUTH);
            } catch (GameException ex) {
                noMeldFree = true;
            }
            if(lastMove.getPath().keySet().size() == 2 && noMeldFree) finished = true;
        }
        return finished;
    }
    
}