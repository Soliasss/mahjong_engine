package fr.univubs.inf1603.mahjong.engine;

/**
 * @author Anton Cosnefroy
 * WindHonor represents tiles which are associated to a wind.
 */
abstract class WindHonor extends HonorTile{
    
    /**
      * Enumeration of all winds
      */
    public enum Wind { 
        EAST('e'), SOUTH('s'), WEST('w'), NORTH('n');
        private final char symbol;

        private Wind(char symbol){
            this.symbol = symbol;
        }
        
        public char getSymbol(){
            return this.symbol;
        }    
    }
    
    
    protected Wind wind;
    
    /**
     * Constructor
     * @param wind initialization of a windTile 
     */
    public WindHonor(Wind wind){
        this.wind = wind;
    }
    
    /**
     * @return Returns this tile's associated wind
     */
    public Wind getWind(){
        return this.wind;
    }
    
    @Override
    public String toString(){
       return super.toString()+"windHonor_"+wind;
    }
}
