/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine;

/**
 * @author Anton Cosnefroy
 * Class about a simpleHonor
 */
public class SimpleHonor extends WindHonor{
    
    public SimpleHonor(WindTile windTile){
        super(windTile);
    }
    
    @Override
    /**
     * @return a string with a simpleHonnor associate with a windTile
     */
    public String toString(){
        return super.toString()+"simpleHonor_"+windTile;
    }
}
