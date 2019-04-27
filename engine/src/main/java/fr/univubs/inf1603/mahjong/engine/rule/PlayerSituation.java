package fr.univubs.inf1603.mahjong.engine.rule;
/**
 * PlayerSituation represents the situation a player could have with all tiles; including the player's hand, tiles set down, winds from the round 
 * and from the player, conditions about a tile drawn or stolen. 
 * It could be a fictive situation.
 */

import fr.univubs.inf1603.mahjong.engine.game.GameTile;

import java.util.Collection;
import java.util.HashSet;

public class PlayerSituation {
    private GameTile winningTile;
    private Collection<GameTile> hand = new HashSet<>();
    private Collection<Combination> concealed = new HashSet<>();
    private Collection<Combination> melds = new HashSet<>();
    private Collection<GameTile> supremeHonors = new HashSet<>();
    private boolean drawnFromWall;
    private boolean takenFromDiscard;
    private Wind roundWind;
    private Wind playerWind;
    
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
    public PlayerSituation(GameTile winningTile, Collection<GameTile> hand, Collection<Combination> concealed, Collection<Combination> melds, Collection<GameTile> supremeHonors,
                           boolean drawnFromWall, boolean takenFromDiscard, Wind roundWind, Wind playerWind){
        this.winningTile = winningTile;
        this.hand.addAll(hand);
        this.concealed.addAll(concealed);
        this.melds.addAll(melds);
        this.supremeHonors.addAll(supremeHonors);
        this.drawnFromWall = drawnFromWall;
        this.takenFromDiscard = takenFromDiscard;
        this.roundWind = roundWind;
        this.playerWind = playerWind;
    }
    
    /**
     *
     * @return winningTile The last tile drawn or stolen which allow us to win 
     */
    public GameTile getWinningTile(){
        return this.winningTile;
    }
    
    /**
     *
     * @return hand It's a list of GameTiles in the player's hand
     */
    public Collection<GameTile> getHand(){
        return this.hand;
    }
    
    /**
     *
     * @return concealed It's a list of combination if some tiles are hidden
     */
    public Collection<Combination> getConcealed(){
        return this.concealed;
    }
    
    /**
     *
     * @return melds It's a list of combination about tiles set down
     */
    public Collection<Combination> getMelds(){
        return this.melds;
    }
    
    /**
     *
     * @return It's a list of supremeHonors set down
     */
    public Collection<GameTile> getSupremeHonors(){
        return this.supremeHonors;
    }
    
    /**
     *
     * @return true or false if the last tile was drawn from the wall
     */
    public boolean isDrawnForWall(){
        return this.drawnFromWall;
    }
    
    /**
     *
     * @return true or false if the last tile was stolen
     */
    public boolean isTakenFromDiscard(){
        return this.takenFromDiscard;
    }
    
    /**
     *
     * @return the wind during this round if his a upward or downward wind
     */
    public Wind getRoundWind(){
        return this.roundWind;
    }
    
    /**
     *
     * @return the wind of the player, it could be east, south, weast or north
     */
    public Wind getPlayerWind(){
        return this.playerWind;
    }
}
