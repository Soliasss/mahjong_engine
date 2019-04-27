package fr.univubs.inf1603.mahjong.engine.rule;

/**
 * Represents common tiles (i.e bamboo,characters,dots) of a mahjong game.
 */

public class CommonTile extends ComplexTile{
    
    /**
     * Possible tile families of simple tiles
     */
    public enum Family { 
        BAMBOO('b'), CHARACTER('c'), DOT('d');
        private  final char symbol;
        private Family(char symbol){
            this.symbol = symbol;
        }
        
        public char getSymbol(){ 
            return this.symbol;
        }
    }

    /**
     * Possible numbers of simple tiles
     */
    public enum Number { 
        ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9);
        private final int value;
        private Number(int value){
            this.value = value;
        }
        public int getValue(){ 
            return this.value;
        }
        public char getSymbol(){ 
            return Character.forDigit(this.value,10);
        }
    }
    
    private final Family family;
    private final Number number;
    
    /**
     * Constructs a new CommonTile with its specified Family and Number
     * @param family Family of the tile
     * @param number Number of the tile
     */
    public CommonTile(Family family, Number number){
        this.family = family;
        this.number = number;
    }
    
    /**
     * @return Returns this CommonTile's Family.
     */
    @Override
    public Family getFamily(){
        return this.family;
    }
    
    /**
     * @return Returns this CommonTile's Number.
     */
    @Override
    public Number getNumber(){
        return this.number;
    }
    
    /**
     * Looks if the CommonTile is a major tile (i.e its number is 1 or 9).
     * @return Returns true if the tile is a major tile, false otherwise.
     */
    @Override
    public boolean isMajor(){
        return (this.number == Number.ONE) || (this.number == Number.NINE);     
    }
    
    @Override
    public String toNormalizedName() {
        return String.format("%c%c",this.getFamily().getSymbol(),this.getNumber().getSymbol()); 
    }
    
    @Override
    public String toString(){
        return super.toString()+"family_"+this.family+"number_"+this.number;
    }
}
