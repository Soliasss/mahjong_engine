package fr.univubs.inf1603.mahjong.engine;

/**
 * @author Anton Cosnefroy
 * ComplexTile and its subclasses propose advanced typing of tiles.
 */
public abstract class ComplexTile implements AbstractTile{
    /**
     * This method is (mostly) used by {@link CommonTile} to find the tile follow the calling tile,
     * this is useful to find a chow (straight of tile). Since each rule can have a different set
     * of following tiles, this method has to be overridden in the declaration of the tiles in
     * implementations of {@link GameRule}.
     * @return a reference to the following tile
     */
    public ComplexTile getNext(){
        return null;
    }
}
