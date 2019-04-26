package fr.univubs.inf1603.mahjong.engine.rule;

/**
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
        SPRING(1, Wind.EAST), SUMMER(2, Wind.SOUTH), AUTUMN(3, Wind.WEST), WINTER(4, Wind.NORTH);
        private final int value;
        private final Wind wind;

        private Season(int value, Wind wind){
            this.value = value;
            this.wind = wind;
        }
        
        public Wind getWind(){
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
     * @param season initialization of seasonTile
     */
    public SeasonTile(Season season){
        super(season.getWind());
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
