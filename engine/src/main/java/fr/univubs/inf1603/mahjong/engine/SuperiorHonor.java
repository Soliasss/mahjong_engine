/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine;

/**
 * @author Anton Cosnefroy
 * Class about the construction of a dragon
 */

/**
 * Enum of all dragons
 */
enum DragonTile { RED, GREEN, WHITE; }

public class SuperiorHonor extends HonorTile {
    private DragonTile dragonsTile;
    
    /**
     * Constructor
     * @param dragonsTile initialisation of a dragonsTile
     */
    public SuperiorHonor(DragonTile dragonsTile){
        this.dragonsTile = dragonsTile;
    }
    
    /**
     * @return a dragonsTile
     */
    public DragonTile getSuperiorHonor(){
       return this.dragonsTile;
   }
   
    @Override
    /**
     * @return a string with a superiorHonor associate with a dragonTile
     */
   public String toString(){
       return super.toString()+"superiorHonor_"+this.dragonsTile;
   }
}
