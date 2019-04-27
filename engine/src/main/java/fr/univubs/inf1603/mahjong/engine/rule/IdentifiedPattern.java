package fr.univubs.inf1603.mahjong.engine.rule;

import fr.univubs.inf1603.mahjong.engine.game.GameTile;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

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
    private Collection<GameTile> tiles = new HashSet<>();

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdentifiedPattern that = (IdentifiedPattern) o;
        return pattern.equals(that.pattern) &&
                Objects.equals(tiles, that.tiles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern, tiles);
    }
}
