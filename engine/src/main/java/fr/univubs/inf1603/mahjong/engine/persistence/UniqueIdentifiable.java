package fr.univubs.inf1603.mahjong.engine.persistence;

import java.util.UUID;

/**
 * This interface describes the ability to get an UUID from an object.
 *
 */
public interface UniqueIdentifiable {

    /**
     * @return Returns this Object's UUID
     */
    UUID getUUID();
}
