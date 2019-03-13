/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine;

/**
 * @author Anton Cosnefroy
 * Class about the construction of common tiles, those are families and the number associate at a family
 */

/**
 * Enum of principals families
 */
enum FamilyTile { BAMBOO, CHARACTER, CIRCLE; }
/**
 * Enum of numbers
 */
enum NumberTile { ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, HEIGHT, NINE; }

public class CommonTile extends ComplexTile{
    private FamilyTile familyTile;
    private NumberTile numberTile;
    
    /**
     * Constructor
     * A common tile is create by a family and a number
     * @param familyTile the family of the tile
     * @param numberTile the number of the tile
     */
    public CommonTile(FamilyTile familyTile, NumberTile numberTile){
        this.familyTile = familyTile;
        this.numberTile = numberTile;
    }
    
    /**
     * @return a familiyTile
     */
    public FamilyTile getFamily(){
        return this.familyTile;
    }
    
    /**
     * @return a numberTile
     */
    public NumberTile getNumber(){
        return this.numberTile;
    }
    
    /**
     * looks if the tile is the number 1 or 9. It's important when we count points
     * @return a boolean true or false
     */
    public boolean isMajor(){
        if ( (this.numberTile == NumberTile.ONE) || (this.numberTile == NumberTile.NINE) ) return true;
        return false;        
    }
    
    @Override
    /**
     * @return a string with the familyTile and the numberTile associated
     */
    public String toString(){
        return super.toString()+"family_"+this.familyTile+"number_"+this.numberTile;
    }
}
