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
     * @throws Exception If the provided tiles do not form a valid combination
     */
    public Combination getCombination(GameTile[] tiles) throws Exception{//TODO : throws RuleException
        try{
            return new Pung(tiles);
        }catch(Exception e){
            //This combination is not a Pung
        }try{
            return new Kong(tiles);
        }catch(Exception e){
            //This combination is not a Kong
        }try{
            return new Chow(tiles);
        }catch(Exception e){
            //This combination is not a Chow
        }try{
            return new Pair(tiles);
        }catch(Exception e){
            //This combination is not a Pair
        }
        throw new Exception("This is not a valid combination");//TODO : make this a rules exception
    }
}
