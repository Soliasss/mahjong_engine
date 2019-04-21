package fr.univubs.inf1603.mahjong.engine.rule;

/**
 * This class is call when we want to show an exception about rules
 * @author Anton Cosnefroy
 */
public class RulesException extends Exception{
    RulesException(String msg){
        super(msg);
    }

    RulesException(String s, Exception e) {
        super(s, e);
    }
}

