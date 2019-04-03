package fr.univubs.inf1603.mahjong.engine.rule.tiles;

import fr.univubs.inf1603.mahjong.engine.rule.tiles.SupremeHonor;
import fr.univubs.inf1603.mahjong.engine.rule.tiles.WindHonor;

/**
 * @author Anton Cosnefroy
 * Season tiles are bonus tiles used in some rules, they are associated to winds.
 */

public class SeasonTile extends SupremeHonor{
    
    /**
     * Symbol used to represent seasons during normalized naming
     */
    private static final char SEASON_SYMBOL='S';
    /**
    * Enumeration of all seasons
    */
    public enum Season { 
        SPRING(1,WindHonor.Wind.EAST), SUMMER(2,WindHonor.Wind.SOUTH), AUTUMN(3,WindHonor.Wind.WEST), WINTER(4,WindHonor.Wind.NORTH);
        private final int value;
        private final WindHonor.Wind wind;

        private Season(int value,WindHonor.Wind wind){
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



    private final Season season;
    /**
     * Constructor
     * A wind is associate to a season
     * @param windTile initialization of windTile
     * @param season initialization of seasonTile 
     */
    public SeasonTile(Wind windTile, Season season){
        super(windTile);
        this.season = season;
    }
    
    /**
     * @return Returns this tile's season
     */
    public Season getSeason(){
        return this.season;
    }
    
    @Override
    public String toNormalizedName() {
        return String.format("%c%d",SEASON_SYMBOL,this.season.getValue());
    }
    
    @Override
    /**
     * @return a string with a windTle and its seasonTile associated
     */
    public String toString(){
       return super.toString()+"wind_"+this.wind+"season_"+this.season;
   }
}
