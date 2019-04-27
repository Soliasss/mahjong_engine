package fr.univubs.inf1603.mahjong.engine.rule;

/**
 * This represents all simple honors (a.k.a wind tiles)
 */
public class SimpleHonor extends WindHonor{
    
    /**
     * Symbol used to represent simple honors during normalized naming
     */
    private static final char SIMPLEHONOR_SYMBOL='W';
    
    public SimpleHonor(Wind windTile){
        super(windTile);
    }
    
    @Override
    public String toString(){
        return super.toString()+"simpleHonor_"+wind;
    }

    @Override
    public String toNormalizedName() {
        return String.format("%c%c", SIMPLEHONOR_SYMBOL, this.wind.getSymbol());
    }
}
