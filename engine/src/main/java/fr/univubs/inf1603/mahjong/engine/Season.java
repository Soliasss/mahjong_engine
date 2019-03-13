/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine;

/**
 * @author Anton Cosnefroy
 * Class about the construction of a season
 * In count rules, we need the season associated at a wind
 */

/**
 * Enum of all seasons
 */
enum SeasonTile { SPRING, SUMMER, AUTUMN, WINTER; }

public class Season extends SupremeHonor{
    private SeasonTile seasonTile;
    /**
     * Constructor
     * A wind is associate to a season
     * @param windTile initialisation of windTile
     * @param seasonTile initialisation of seasonTile 
     */
    public Season(WindTile windTile, SeasonTile seasonTile){
        super(windTile);
        this.seasonTile = seasonTile;
    }
    
    /**
     * @return a seasonTile
     */
    public SeasonTile getSeason(){
        return this.seasonTile;
    }
    
    @Override
    /**
     * @return a string with a windTle and his seasonTile associated
     */
    public String toString(){
       return super.toString()+"wind_"+this.windTile+"season_"+this.seasonTile;
   }
}
