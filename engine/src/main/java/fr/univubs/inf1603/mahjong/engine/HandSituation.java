package fr.univubs.inf1603.mahjong.engine;

import java.util.List;

public class HandSituation {
    private List<AbstractTile> hand;
    private List<Meld> melds;                   //FIXME: are concealed kongs considered as meld ?
    private AbstractTile winningTile;
    private List<SupremeHonor> supremeHonors;
    private WindHonor.Wind roundWind;
    private WindHonor.Wind seatWind;
    private boolean kongRemplacement;

    //TODO: finish class, using the informat.py file

}
