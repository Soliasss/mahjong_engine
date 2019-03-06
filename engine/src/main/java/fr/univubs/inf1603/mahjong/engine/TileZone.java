package fr.univubs.inf1603.mahjong.engine;

import java.util.ArrayList;
import java.util.Collection;

/**
 * TileZone
 * @author Malléjac Clément
 */
public class TileZone implements Zone{
    
    
    protected ArrayList<GameTile> tiles;
    
    public TileZone(){
        tiles = new ArrayList<>();
    }
    
    @Override
    public Collection getCollection() {
        return tiles;
    }
    
}
