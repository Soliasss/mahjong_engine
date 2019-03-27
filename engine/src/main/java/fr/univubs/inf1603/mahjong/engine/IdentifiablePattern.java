package fr.univubs.inf1603.mahjong.engine;

import java.util.Collection;

/**
 * Interface
 */
public interface IdentifiablePattern {
    int getValue();

    Collection<IdentifiedPattern> identify(PlayerSet set);
}
