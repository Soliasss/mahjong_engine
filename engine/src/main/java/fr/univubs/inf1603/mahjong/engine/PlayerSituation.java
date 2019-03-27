package fr.univubs.inf1603.mahjong.engine;

/**
 * PlayerSituation represents the situation a player could have with all tiles; including the player's hand, tiles set down, winds from the round 
 * and from the player, conditions about a tile drawn or stolen. 
 * It could be a fictive situation.
 * @author Piere Guriel--Fardel, Anton Cosnefroy
 */

import java.util.ArrayList;
import java.util.Collection;

public class PlayerSituation {
    private GameTile winningTile;
    private Collection<GameTile> hand;
    private Collection<Combination> concealed;
    private Collection<Combination> melds;
    private Collection<SupremeHonor> supremeHonors;
    private boolean drawnFromWall;
    private boolean takenFromDiscard;
    private WindHonor.Wind roundWind;
    private WindHonor.Wind playerWind;
    
    /**
     *
     * @param winningTile It's the tile drawn from the wall or stolen, it's a winning tile if we win with her. It's possible she's fictive.
     * @param hand  The hand of the player, it's a representation of combinations in the hand
     * @param concealed If we have a concealed combination like Kong
     * @param melds Tiles set down by the player
     * @param supremeHonors If seasons or flowers was set down
     * @param drawnFromWall A boolean, if a tile was drawn from the wall
     * @param takenFromDiscard A boolean, if a tile was stolen
     * @param roundWind Wind of the round, it's influencing the scoring
     * @param playerWind Wind of a player, it's influencing the scoring
     */
    public PlayerSituation(GameTile winningTile, Collection<Combination> hand, Collection<Combination> concealed, Collection<Combination> melds, Collection<SupremeHonor> supremeHonors,
            boolean drawnFromWall, boolean takenFromDiscard, WindHonor.Wind roundWind, WindHonor.Wind playerWind){
        this.winningTile = winningTile;
        this.hand = new ArrayList<GameTile>();
        this.concealed = new ArrayList<Combination>();
        this.melds = new ArrayList<Combination>();
        this.supremeHonors = new ArrayList<SupremeHonor>();
        this.drawnFromWall = drawnFromWall;
        this.takenFromDiscard = takenFromDiscard;
        this.roundWind = roundWind;
        this.playerWind = playerWind;
    }
}
