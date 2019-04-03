package fr.univubs.inf1603.mahjong.engine.rule.tiles;

import fr.univubs.inf1603.mahjong.engine.rule.tiles.SupremeHonor;
import fr.univubs.inf1603.mahjong.engine.rule.tiles.WindHonor;

/**
 * @author Anton Cosnefroy
 * Flower tiles are bonus tiles used in some rules, they are associated to winds.
 */
public class FlowerTile extends SupremeHonor{
    /**
     * Symbol used to represent seasons during normalized naming
     */
    private static final char FLOWER_SYMBOL='F';

    /**
     * Possible flowers of flower tiles
     */
    public enum Flower{
        PLUM(1,WindHonor.Wind.EAST), ORCHID(2,WindHonor.Wind.SOUTH), CHRYSANTHEMUM(3,WindHonor.Wind.WEST), BAMBOO(4,WindHonor.Wind.NORTH);
        private final int value;
        private final WindHonor.Wind wind;

        private Flower(int value,WindHonor.Wind wind){
            this.value = value;
            this.wind = wind;
        }
        
        public WindHonor.Wind getWind(){
            return this.wind;
        }
        
        public int getValue(){ 
            return this.value;
        }
    }

    private final Flower flower;
    
    /**
     * 
     * @param flower Flower of the Tile
     */
    public FlowerTile(Flower flower){
        super(flower.getWind());
        this.flower = flower;
    }
    
    /**
     * @return Returns this FlowerTile's flower. 
     */
    public Flower getFlower(){
        return this.flower;
    }
    
    @Override
    public String toNormalizedName() {
        return String.format("%c%d", FLOWER_SYMBOL,this.flower.getValue());
    }
    
    @Override
    public String toString(){
       return super.toString()+"wind_"+this.wind+"flower_"+this.flower;
    }
}
