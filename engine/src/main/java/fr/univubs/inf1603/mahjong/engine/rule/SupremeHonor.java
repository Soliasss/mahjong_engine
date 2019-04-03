package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.rule.WindHonor;

/**
 * @author Anton Cosnefroy
 * Abstract class about a supremeHonor
 */
public abstract class SupremeHonor extends WindHonor{

    /**
     * @param windTile initialization of a windTile
     */
    public SupremeHonor(Wind windTile) {
        super(windTile);
    }
}
