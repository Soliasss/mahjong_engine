package fr.univubs.inf1603.mahjong.engine;

import java.util.Collection;

public class PlayerSet {
    private GameTile winningTile;
    private Collection<Combination> hand;
    private Collection<Combination> concealed;
    private Collection<Combination> melds;
    private Collection<SupremeHonor> supremeHonors;
    private boolean drawnFromWall;
    private boolean takenFromDiscard;
    private WindHonor.Wind roundWind;
    private WindHonor.Wind playerWind;
}
