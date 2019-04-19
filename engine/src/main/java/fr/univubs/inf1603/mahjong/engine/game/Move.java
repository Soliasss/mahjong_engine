package fr.univubs.inf1603.mahjong.engine.game;

import fr.univubs.inf1603.mahjong.engine.persistence.Persistable;
import fr.univubs.inf1603.mahjong.engine.rule.Wind;
import java.beans.ConstructorProperties;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

/**
 * Cette classe permet de représenter le déplacement d'une ou plusieurs tuiles
 * d'un joueur
 *
 * @author COGOLUEGNES Charles
 */
public class Move implements Persistable,Serializable, Cloneable {

    private final Wind wind;
    private final int priority;
    private final HashMap<Integer, TileZoneIdentifier> path;
    private final UUID uuid;

    /**
     *
     * @param wind
     * @param priority La priorité du Move par rapport à d'autre Move (0 est la
     * plus grosse priorité, n est la moins grosse priorité)
     * @param path Une Map qui contient l'information de déplacement de la ou
 des tuile(s). Le numéro reprente l'ID de la tuile, la MahjongTileZone est la
 zone de jeu dans laquelle la tuile va être ajoutée
     * @param uuid
     * @throws fr.univubs.inf1603.mahjong.engine.game.MoveException
     */
    public Move(Wind wind, int priority, HashMap<Integer, TileZoneIdentifier> path, UUID uuid) throws MoveException {
        this.wind = wind;

        if (priority < 0) {
            throw new MoveException("The priority has to be positive or O");
        }
        this.priority = priority;

        if (path.isEmpty()) {
            throw new MoveException("The path can not be empty.");
        }
        this.path = path;

        this.uuid = uuid;
    }

    public Move(Wind wind, int priority, HashMap<Integer, TileZoneIdentifier> path) throws MoveException {
        this(wind, priority, path, UUID.randomUUID());
    }

    /**
     * Retourne le coté du joueur associé au Move
     *
     * @return side
     */
    public Wind getWind() {
        return this.wind;
    }

    /**
     * Retourne la priorité du Move
     *
     * @return priority
     */
    public int getPriority() {
        return this.priority;
    }

    /**
     * Retourne le map symbolisant le déplacement
     *
     * @return path
     */
    public HashMap<Integer, TileZoneIdentifier> getPath() {
        return this.path;
    }

    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    
    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    @Override
    public PropertyChangeSupport getPropertyChangeSupport() {
        return this.propertyChangeSupport;
    }
}
