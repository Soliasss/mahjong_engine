package fr.univubs.inf1603.mahjong.engine.game;

import java.util.ArrayList;

/**
 *
 * @author Samuel LE BERRE
 */
public class PlayerZone {
    
    private TileZone hand;
    private TileZone discard;
    private ArrayList<MeldZone> meldzone;
    
    public PlayerZone(TileZone hand,TileZone discard,ArrayList<MeldZone> meldzone){
        this.hand = hand;
        this.discard = discard;
        this.meldzone = meldzone;
    }

    public TileZone getHand() {
        return this.hand;
    }

    public void setHand(TileZone hand) {
        this.hand = hand;
    }

    public TileZone getDiscard() {
        return this.discard;
    }

    public void setDiscard(TileZone discard) {
        this.discard = discard;
    }

    public ArrayList<MeldZone> getMeldzone() {
        return this.meldzone;
    }

    public void setMeldzone(ArrayList<MeldZone> meldzone) {
        this.meldzone = meldzone;
    }
    
    
}
