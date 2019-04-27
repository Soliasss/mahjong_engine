
import fr.univubs.inf1603.mahjong.engine.game.GameException;
import fr.univubs.inf1603.mahjong.engine.game.MahjongBoard;
import fr.univubs.inf1603.mahjong.engine.game.MahjongGame;
import fr.univubs.inf1603.mahjong.engine.game.Move;
import fr.univubs.inf1603.mahjong.engine.rule.GameRule;
import fr.univubs.inf1603.mahjong.engine.rule.GameRuleFactory;
import fr.univubs.inf1603.mahjong.engine.rule.RulesException;
import fr.univubs.inf1603.mahjong.engine.rule.Wind;
import java.time.Duration;
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
            rule = new GameRuleFactory().create("SILLY_RULE");
        } catch (RulesException ex) {
            System.exit(0);
        }
        
        
        MahjongBoard board = new MahjongBoard(Wind.WEST);
        
        
        MahjongGame game;
        game = new MahjongGame(rule, board, (Move)null, Duration.ofSeconds(5), Duration.ofSeconds(5), new int[4], UUID.randomUUID(), Wind.values());
        System.out.println("Passed construction");
        
        game.launchGame();
        
    }
}
