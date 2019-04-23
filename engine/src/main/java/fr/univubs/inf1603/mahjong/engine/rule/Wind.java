package fr.univubs.inf1603.mahjong.engine.rule;

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
