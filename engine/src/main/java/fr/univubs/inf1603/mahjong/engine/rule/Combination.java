package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.GameTile;

/**
 * Interface representing combination of tiles (pair, chow, pung, and kong)
 */
public interface Combination {
    /**
     * @return the tiles of the combination
     */
    GameTile[] getTiles();
    /**
     * @return true if the combination is a pair (two identical tiles)
     */
    default boolean isPair(){
        return false;
    }
    /**
     * @return true if the combination is a chow (a straight of 3 common tiles of the same family)
     */
    default boolean isChow(){
        return false;
    }
    /**
     * @return true if the combination is a pung (three identical tiles)
     */
    default boolean isPung(){
        return false;
    }
    /**
     * @return true if the combination is a kong (four identical tiles)
     */
    default boolean isKong(){
        return false;
    }
    
    /**
     * @param tiles the tiles of the combination
     * @return true or false if a pair, chow, pung or kong is valid
     */
    boolean isValid(GameTile[] tiles);
}
