package fr.univubs.inf1603.mahjong.engine;

import java.util.UUID;

/**
 * This interface describes the ability to get an UUID from an object.
 *
 * @author purpl
 */
public interface UniqueIdentifiable {

    /**
     * @return Returns this Object's UUID
     */
    UUID getUUID();
}
