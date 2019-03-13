/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine;

/**
 * @author Anton Cosnefroy
 * Class about the construction of a wind
 */

/**
 * Enum of all winds
 */
enum WindTile { EAST, SOUTH, WEST, NORTH; }


public class WindHonor extends HonorTile{
    protected WindTile windTile;
    
    /**
     * Constructor
     * @param windTile inintialisation of a windTile 
     */
    public WindHonor(WindTile windTile){
        this.windTile = windTile;
    }
    
    /**
     * @return a windTile
     */
    public WindTile getWind(){
        return this.windTile;
    }
    
    @Override
    /**
     * @return a string with a windTile
     */
    public String toString(){
       return super.toString()+"windHonor_"+windTile;
   }
}
