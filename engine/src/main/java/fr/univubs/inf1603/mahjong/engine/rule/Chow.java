package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.GameTile;

import java.util.Arrays;
import org.apache.log4j.Logger;

/**
 *
 */
public class Chow implements Combination {

    private GameTile[] tiles;
    
        // Logger
    private static final Logger LOGGER = Logger.getLogger(Chow.class.getName());
    
    /**
     *
     * @param tiles
     * @throws RulesException
     */
    public Chow(GameTile[] tiles) throws RulesException{
        if(isValid(tiles)){
           this.tiles = tiles; 
        } else {
            throw new RulesException("Invalid chow");
        }
    }
    @Override
    public GameTile[] getTiles() {
        LOGGER.info("Enter to getTile() method");
        return this.tiles;
    }

    @Override
    public boolean isChow() {
        LOGGER.info("Enter to isChow() method");
        return true;
    }
    
    @Override
    public final boolean isValid(GameTile[] tiles){
        LOGGER.info("Enter to isValid(GameTile[] tiles) method");
        //Works on the basis that the tiles are either ordered or not a Chow
        return tiles.length==3 &&
                tiles[0].getTile().getNext() == tiles[1].getTile() &&
                tiles[1].getTile().getNext() == tiles[2].getTile();
    }

    @Override
    public boolean equals(Object o) {
        LOGGER.info("Enter to equals(Object o) method");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chow chow = (Chow) o;
        return Arrays.equals(getTiles(), chow.getTiles());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getTiles());
    }
}
