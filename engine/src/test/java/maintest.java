import fr.univubs.inf1603.mahjong.Wind;
import fr.univubs.inf1603.mahjong.engine.game.Game;
import fr.univubs.inf1603.mahjong.engine.game.GameException;
import fr.univubs.inf1603.mahjong.engine.game.GameTile;
import fr.univubs.inf1603.mahjong.engine.game.GameTileInterface;
import fr.univubs.inf1603.mahjong.engine.game.MahjongBoard;
import fr.univubs.inf1603.mahjong.engine.game.MahjongGame;
import fr.univubs.inf1603.mahjong.engine.game.Move;
import fr.univubs.inf1603.mahjong.engine.game.TileZone;
import fr.univubs.inf1603.mahjong.engine.game.TileZoneIdentifier;
import fr.univubs.inf1603.mahjong.engine.rule.GameRule;
import fr.univubs.inf1603.mahjong.engine.rule.GameRuleFactory;
import fr.univubs.inf1603.mahjong.engine.rule.RulesException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.Duration;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 */
public class maintest {

    static boolean stop =false;
    static Move lastPlayedMove;
    static ArrayList<Move> possibleMoves;
    static boolean hasRegistered = false;
    static MahjongGame game;
    public static void main(String[] args) throws GameException{
        GameRule rule = null;
        try {
            rule = new GameRuleFactory().create("INTERNATIONAL");
        } catch (RulesException ex) {
            throw new RuntimeException();
        }
        
        game = new MahjongGame(rule, Duration.ofMillis(10), Duration.ofMillis(10));
        System.out.println("Passed construction");

        PropertyChangeListener prop;
        prop = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent arg0) {
                System.out.println(arg0.getPropertyName());
                if(arg0.getPropertyName().equals(Game.GAME_OVER_PROPERTY)){
                    stop=true;
                }
                if(arg0.getPropertyName().equals(Game.LAST_PLAYED_MOVE_PROPERTY)){
                    lastPlayedMove=(Move)arg0.getNewValue();
                }
                if(arg0.getPropertyName().equals(Game.POSSIBLE_MOVES_PROPERTY)){
                    try{
                        game.registerMove(new Move(Wind.WEST, -1, new HashMap<>(), new HashMap<>()));
                    }catch(GameException e){
                        System.out.println(e);
                    }

                    
                    /*hasRegistered=false;

                    possibleMoves = (ArrayList<Move>)arg0.getNewValue();
                    try {
                        System.out.println("last playermove :"+lastPlayedMove);
                        
                        System.out.println("possible moves :"+possibleMoves);
                        System.out.println("try to register move : "+possibleMoves.get(0).toString());
                        
                        game.registerMove(possibleMoves.get(0));
                    } catch (GameException ex) {
                        Logger.getLogger(maintest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    hasRegistered=true;                    
                    */
                    
                }
            }
        };

        
        game.addPropertyChangeListener(Game.LAST_PLAYED_MOVE_PROPERTY, prop);
        game.addPropertyChangeListener(Game.GAME_OVER_PROPERTY, prop);
        game.addPropertyChangeListener(Game.POSSIBLE_MOVES_PROPERTY, prop);

        
        game.launchGame();

        MahjongBoard b = (MahjongBoard)game.getBoard();
        /*
        for(TileZone tz : b.getZones().values()){
            System.out.print(tz.getIdentifier().getNormalizedName()+":");
            for(GameTileInterface gt : tz.getTiles()){
                System.out.print(gt.toString()+" ");
            }
            
            System.out.println();
        }
*/
               
        
    }
}
