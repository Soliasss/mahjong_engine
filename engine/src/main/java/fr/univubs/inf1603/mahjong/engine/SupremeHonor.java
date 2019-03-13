/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.mahjong.engine;

/**
 * @author Anton Cosnefroy
 * Abstract class about a supremeHonor
 */
public abstract class SupremeHonor extends WindHonor{

    /**
     * Constructor
     * @param windTile initialisation of a windTile
     */
    public SupremeHonor(WindTile windTile) {
        super(windTile);
    }
}
