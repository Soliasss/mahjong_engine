package fr.univubs.inf1603.mahjong.engine.rule;

/**
 * This represents all superior honors (a.k.a dragon tiles)
 */

public class SuperiorHonor extends HonorTile {
    /**
     * Symbol used to represent superior honors during normalized naming
     */
    private static final char SUPERIORHONOR_SYMBOL='D';
    
    /**
     * Enumeration of all dragons
     */
    public enum Dragon { 
        RED('r'), GREEN('g'), WHITE('w');
        private final char symbol;

        private Dragon(char symbol){
            this.symbol = symbol;
        }
        
        public char getSymbol(){
            return this.symbol;
        }  
    
    }
    
    private final Dragon dragon;
    /**
     * @param dragon Initialization of a dragonsTile
     */
    public SuperiorHonor(Dragon dragon){
        this.dragon = dragon;
    }
    
    /**
     * @return Returns this tile's Dragon
     */
    public Dragon getDragon(){
       return this.dragon;
   }
   
    @Override
    public String toString(){
        return super.toString()+"superiorHonor_"+this.dragon;
    }

    @Override
    public String toNormalizedName() {
        return String.format("%c%c",SUPERIORHONOR_SYMBOL,this.dragon.getSymbol());
    }
}
