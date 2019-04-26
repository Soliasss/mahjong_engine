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
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Samuel LE BERRE
 */
public class InternationalBoardRule implements BoardRule{
    
    private int firstDieRoll = 0;
    
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
            }else {
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
                    path.put(i*j, TileZoneIdentifier.getIdentifierFromNormalizedName("hand"+Wind.values()[i].name()));
                    Move drawMove=null;
                    try {
                        drawMove = new Move(Wind.values()[i], 0, path);
                    } catch (MoveException ex) {
                        Logger.getLogger(SillyBoardRule.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    board.applyMove(drawMove);
                } catch (GameException ex) {
                    Logger.getLogger(InternationalBoardRule.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return board;
    }

    @Override
    public boolean isMoveValid(MahjongBoard board, Move lastMove, Move move) {
        return findValidMoves(board, lastMove).get(move.getWind()).contains(move);
    }

    public TileZoneIdentifier getMeldAvailable(MahjongBoard board, Wind wind) throws GameException{
        TileZoneIdentifier tzi = null;
        if(board.getTileZone("Meld"+wind.getName()+"0").getTiles().isEmpty()){
            tzi = board.getTileZone("Meld"+wind.getName()+"0").getIdentifier();
        }else if(board.getTileZone("Meld"+wind.getName()+"1").getTiles().isEmpty()){
            tzi = board.getTileZone("Meld"+wind.getName()+"0").getIdentifier();
        }else if(board.getTileZone("Meld"+wind.getName()+"2").getTiles().isEmpty()){
            tzi = board.getTileZone("Meld"+wind.getName()+"0").getIdentifier();
        }else if(board.getTileZone("Meld"+wind.getName()+"3").getTiles().isEmpty()){
            tzi = board.getTileZone("Meld"+wind.getName()+"0").getIdentifier();
        }else {
            throw new GameException("It should already be a Majhong");
        }
        return tzi;
    }
    
    public ArrayList<Move> possibleMoveDiscard(MahjongBoard board, Wind wind) throws GameException{
        ArrayList<Move> moves = new ArrayList<Move>();
        TileZoneIdentifier tziDiscard = board.getTileZone("discard" + wind.name()).getIdentifier();
        for(GameTileInterface gti : board.getTileZone("hand" + wind.name()).getTiles()){
            HashMap<Integer, TileZoneIdentifier> path = new HashMap<>();
            path.put(gti.getGameID(), tziDiscard);
            moves.add( new Move(wind, 0, path));
        }
        return moves;
    }
    
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
                                move.add(new Move(wind, 0, path));
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
                                    move.add(new Move(wind, 0, path));
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
    
    public ArrayList<Move> possibleSteal(MahjongBoard board, Wind wind, GameTile gameTile, ArrayList<GameTile> tiles) throws GameException{
        ArrayList<Move> moveSteal = new ArrayList<Move>();
        int size = tiles.size();
        ArrayList<GameTile> gtArray = null;
        for(Wind windOthers : Wind.values()){
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
        }
        return moveSteal;
    }
            
    @Override
    public EnumMap<Wind, Collection<Move>> findValidMoves(MahjongBoard board, Move lastMove) {
        EnumMap<Wind, Collection<Move>> moves = new EnumMap<>(Wind.class);
        int index = (lastMove.getWind().ordinal()+1)% Wind.values().length;
        Wind nextWindToPlay = Wind.values()[index];
        moves.put(nextWindToPlay, new ArrayList<>());
        try {
            ArrayList<GameTileInterface> gtiArray = board.getTileZone("discard" + nextWindToPlay.name()).getTiles();
            ArrayList<GameTile> gtArray = new ArrayList<GameTile>();
            for(GameTileInterface gti : gtiArray){
                GameTile gt = null;
                if(gti instanceof GameTile) gt = (GameTile) gti;
                if(gt != null) gtArray.add(gt);
            }
            moves.get(nextWindToPlay).addAll(this.possibleMoveDiscard(board, nextWindToPlay));
            if(lastMove.getPath().get(0).getNormalizedName().charAt(0) == 'd'){
                
                //moves.get(nextWindToPlay).addAll(this.possibleSteal(board, nextWindToPlay,lastMove.getPath().keySet()) ,gtArray));
            }   
        } catch (GameException ex) {
            Logger.getLogger(InternationalBoardRule.class.getName()).log(Level.SEVERE, null, ex);
        }       
        return moves;
    }

    @Override
    public boolean isGameFinished(MahjongBoard board, Move lastMove) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}