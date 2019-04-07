package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.GameTile;

/**
 *
 * @author purpl
 */
public class CombinationFactory {

    /**
     * @param tiles Tiles with which we want to make a combination
     * @return The correct Combination made with the given tiles
     * @throws fr.univubs.inf1603.mahjong.engine.rule.RulesException
     */
    public Combination getCombination(GameTile[] tiles) throws RulesException{
        try{
            return new Pung(tiles);
        }catch(RulesException e){
            //This combination is not a Pung
        }try{
            return new Kong(tiles);
        }catch(RulesException e){
            //This combination is not a Kong
        }try{
            return new Chow(tiles);
        }catch(RulesException e){
            //This combination is not a Chow
        }try{
            return new Pair(tiles);
        }catch(RulesException e){
            //This combination is not a Pair
        }
        throw new RulesException("This is not a valid combination");
    }
}
