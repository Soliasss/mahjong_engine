package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.GameTile;

/**
 * 
 */
public class InternationalCombinationFactory implements AbstractCombinationFactory {

    @Override
    public Combination newCombination(GameTile... tiles) throws RulesException{
        for (int i = 0; i < tiles.length; i++)
            for (int j = 0; j < tiles.length; j++)
                if (i != j && tiles[i].getGameID() == tiles[j].getGameID())
                    throw new RulesException("Same gameID found on the tiles");

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
