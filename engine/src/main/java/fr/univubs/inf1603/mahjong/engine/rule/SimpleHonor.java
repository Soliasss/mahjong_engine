package fr.univubs.inf1603.mahjong.engine.rule;
import fr.univubs.inf1603.mahjong.Wind;
import org.apache.log4j.Logger;

/**
 * This represents all simple honors (a.k.a wind tiles)
 */
public class SimpleHonor extends WindHonor{
    
    /**
     * Symbol used to represent simple honors during normalized naming
     */
    private static final char SIMPLEHONOR_SYMBOL='W';
    
    // Logger
    private static final Logger LOGGER = Logger.getLogger(SimpleHonor.class.getName());
    
    public SimpleHonor(Wind windTile){
        super(windTile);
    }
    
    @Override
    public String toString(){
        LOGGER.info("Enter to toString()");
        return super.toString()+"simpleHonor_"+wind;
    }

    @Override
    public String toNormalizedName() {
        LOGGER.info("Enter to toNormalizedName()");
        return String.format("%c%c", SIMPLEHONOR_SYMBOL, this.wind.getSymbol());
    }
}
