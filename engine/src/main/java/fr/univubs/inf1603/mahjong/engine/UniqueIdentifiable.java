/**
 * This interface describes the ability to get an UUID from an object.
 * 
 */
package fr.univubs.inf1603.mahjong.engine;

import java.util.UUID;

/**
 *
 * @author purpl
 */
public interface UniqueIdentifiable {

    /**
     *
     * @return Returns this Object's UUID
     */
    public UUID getUUID();
}
