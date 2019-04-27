
import fr.univubs.inf1603.mahjong.engine.game.Game;
import fr.univubs.inf1603.mahjong.engine.game.GameException;
import fr.univubs.inf1603.mahjong.engine.game.GameTile;
import fr.univubs.inf1603.mahjong.engine.game.GameTileInterface;
import fr.univubs.inf1603.mahjong.engine.game.MahjongBoard;
import fr.univubs.inf1603.mahjong.engine.game.MahjongGame;
import fr.univubs.inf1603.mahjong.engine.game.Move;
import fr.univubs.inf1603.mahjong.engine.game.TileZone;
import fr.univubs.inf1603.mahjong.engine.rule.GameRule;
import fr.univubs.inf1603.mahjong.engine.rule.GameRuleFactory;
import fr.univubs.inf1603.mahjong.engine.rule.RulesException;
import fr.univubs.inf1603.mahjong.engine.rule.Wind;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.Duration;
import java.util.Map;
import java.util.UUID;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 */
public class maintest {
    public static void main(String[] args) throws GameException{
        GameRule rule = null;
        try {
            rule = new GameRuleFactory().create("INTERNATIONAL");
        } catch (RulesException ex) {
            throw new RuntimeException();
        }
        
        MahjongGame game;
        game = new MahjongGame(rule, Duration.ofSeconds(5), Duration.ofSeconds(5));
        System.out.println("Passed construction");
        
        game.launchGame();

        MahjongBoard b = (MahjongBoard)game.getBoard();
        
        for(TileZone tz : b.getZones().values()){
            System.out.print(tz.getIdentifier().getNormalizedName()+":");
            for(GameTileInterface gt : tz.getTiles()){
                System.out.print(gt.toString()+" ");
            }
            
            System.out.println();
        }
        PropertyChangeListener prop = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent arg0) {
                System.out.println(arg0.getPropertyName());
            }
        };
                
        game.addPropertyChangeListener(Game.LAST_PLAYED_MOVE_PROPERTY, prop);
        game.addPropertyChangeListener(Game.GAME_OVER_PROPERTY, prop);
        game.addPropertyChangeListener(Game.POSSIBLE_MOVES_PROPERTY, prop);

        for(Move a : game.getPossibleMoves()){
            System.out.println(a);
        }
        
    }
}
