package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.GameTile;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Representation of a pattern in a ({@link PlayerSet})
 */
public class IdentifiedPattern {
    /**
     * Pattern identified
     */
    private IdentifiablePattern pattern;
    /**
     * Tiles composing the pattern
     */
    private Collection<GameTile> tiles = new ArrayList<>();

    public IdentifiedPattern(IdentifiablePattern pattern, Collection<GameTile> tiles){
        this.pattern = pattern;
        this.tiles.addAll(tiles);
    }

    public IdentifiedPattern(IdentifiablePattern pattern){
        this.pattern = pattern;
    }

    public IdentifiablePattern getPattern() {
        return this.pattern;
    }

    public Collection<GameTile> getTiles() {
        return tiles;
    }
}
