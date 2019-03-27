package fr.univubs.inf1603.mahjong.engine;

import java.util.Collection;

/**
 * Interface representing a pattern. A pattern is a group of {@link GameTile} or {@link Combination}
 * forming what can be called a "fan"
 */
public interface IdentifiablePattern {
    int getValue();

    Collection<IdentifiedPattern> identify(PlayerSet set);
}
