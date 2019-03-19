package fr.univubs.inf1603.mahjong.engine;

import java.util.HashMap;

/**
 * Interface used to identify various patterns of tiles given the hand situation
 */
public interface PatternIdentifier {

    /**
     * Main method of an identifier, takes the hand situation and return the associated pattern(s)
     * with their position in the hand
     * @param situation hand situation to analyse
     * @return a HashMap with {@link AbstractPattern AbstractPattern(s)} as key(s) and {@link AbstractTile AbstractTiles} as content
     */
    HashMap<AbstractPattern, AbstractTile> identify(HandSituation situation);
}
