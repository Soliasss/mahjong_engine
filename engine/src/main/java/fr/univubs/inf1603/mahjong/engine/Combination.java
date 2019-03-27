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
    boolean isPair();
    /**
     * @return true if the combination is a chow (a straight of 3 common tiles of the same family)
     */
    boolean isChow();
    /**
     * @return true if the combination is a pung (three identical tiles)
     */
    boolean isPung();
    /**
     * @return true if the combination is a kong (four identical tiles)
     */
    boolean isKong();
}
