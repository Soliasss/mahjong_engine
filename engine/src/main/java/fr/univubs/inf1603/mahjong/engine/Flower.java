/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine;

/**
 * @author Anton Cosnefroy
 * Class about the construction of a flower
 * In count rules, we need the flower associated at a wind
 */

/**
 * Enum of all flowers
 */
enum FlowerTile { PLUM, ORCHID, CHRYSANTHEMUM, BAMBOO; }
 
public class Flower extends SupremeHonor{
    private FlowerTile flowerTile;
    
    /**
     * Constructor
     * A wind is associate to a flower
     * @param windTile initialisation of a windTile
     * @param flowerTile initialisation of a flowerTile
     */
    public Flower(WindTile windTile, FlowerTile flowerTile){
        super(windTile);
        this.flowerTile = flowerTile;
    }
    
    /**
     * @return a flowerTile
     */
    public FlowerTile getFlowers(){
        return this.flowerTile;
    }
    
    @Override
    /**
     * @return a string with a windTle and his flowerTile associated
     */
    public String toString(){
       return super.toString()+"wind_"+this.windTile+"flower_"+this.flowerTile;
   }
}
