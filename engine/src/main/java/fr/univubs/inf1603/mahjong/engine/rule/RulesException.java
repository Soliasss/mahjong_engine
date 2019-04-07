package fr.univubs.inf1603.mahjong.engine.rule;

/**
 * This class is call when we want to show an exception about rules
 * @author Anton Cosnefroy
 */
public class RulesException extends Exception{
    public RulesException(String msg){
        super(msg);
        System.out.println("[Rules Exception]"+msg);
    }
    
    /**
     * Print an intern error if we have an intern error about rules
     * @param msg
     */
    public static void intern(String msg){
        System.out.println("[Rules Intern Exception]"+msg);
    }
    
    /**
     * Print an error if we have a problem during the calculation of points
     * @param msg
     */
    public static void scoring(String msg){
        System.out.println("[Rules Scoring Points Exception]"+msg);
    }
    
    /**
     * Print an error if we have a problem about some combinations
     * @param msg
     */
    public static void combinaison(String msg){
        System.out.println("[Rules Combinaison Exception]"+msg);
    } 
    
    /**
     * Print an error if we have a wrong tile
     * @param msg
     */
    public static void tile(String msg){
        System.out.println("[Rules Tile Exception]"+msg);
    }
    
    /**
     * Print an error if we have a wrong pattern
     * @param msg
     */
    public static void pattern(String msg){
        System.out.println("[Rules Pattern Exception]"+msg);
    }
}

