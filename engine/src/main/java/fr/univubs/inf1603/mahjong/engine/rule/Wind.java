package fr.univubs.inf1603.mahjong.engine.rule;

/**
  * Enumeration of all winds
  */
public enum Wind {
    EAST('e',"East"), SOUTH('s',"South"), WEST('w',"West"), NORTH('n',"North");
    private final char symbol;
    private final String name;

    private Wind(char symbol, String name){
        this.symbol = symbol;
        this.name = name;
    }

    public char getSymbol(){
        return this.symbol;
    }

    public String getName(){
        return this.name;
    }
}
