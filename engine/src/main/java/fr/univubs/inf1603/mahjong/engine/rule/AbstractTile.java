package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.rule.CommonTile.Family;
import fr.univubs.inf1603.mahjong.engine.rule.CommonTile.Number;
/**
 * AbstractTile represents the concept of tile in mahjong rules.
 */
public interface AbstractTile {
    /**
     * A tile's normalized name is a case-sensitive, two letters String (c.f Engine_designDoc.md).
     * @return Returns this AbstracTile's normalized name.
     */
    String toNormalizedName();

    /**
     * This method is (mostly) used by {@link CommonTile} to find the tile following the calling tile,
     * this is useful to find a chow (straight of tile). Since each rule can have a different set
     * of following tiles, this method has to be overridden in the declaration of the tiles in
     * implementations of {@link GameRule}.
     * @return a reference to the following tile
     */
    default AbstractTile getNext(){
        return null;
    }

    /**
     * This method is (mostly) used by {@link CommonTile} to find the tile followed by the calling tile,
     * this is useful to find a chow (straight of tile). Since each rule can have a different set
     * of following tiles, this method has to be overridden in the declaration of the tiles in
     * implementations of {@link GameRule}.
     * @return a reference to the followed tile
     */
    default AbstractTile getPrevious(){
        return null;
    }
    
    default boolean isMajor(){
        return false;
    }
    
    default Family getFamily(){
        return null;
    }
    
    default Number getNumber(){
        return null;
    }
}
