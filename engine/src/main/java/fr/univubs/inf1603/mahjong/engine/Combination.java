package fr.univubs.inf1603.mahjong.engine;

/**
 * Interface representing combination of tiles (pair, chow, pung, and kung)
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
}
