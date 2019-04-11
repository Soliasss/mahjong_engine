package fr.univubs.inf1603.mahjong.engine.game;

import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

/**
 * Cette classe permet de représenter le déplacement d'une ou plusieurs tuiles
 * d'un joueur
 *
 * @author COGOLUEGNES Charles
 */
public class Move implements Serializable, Cloneable {

    private final Side side;
    private final int priority;
    private final HashMap<Integer, MahjongTileZone> path;
    private final UUID uuid;

    /**
     *
     * @param side Le nombre qui représente le numéro du joueur qui effectue
     * le Move
     * @param priority La priorité du Move par rapport à d'autre Move (0 est la
     * plus grosse priorité, n est la moins grosse priorité)
     * @param path Une Map qui contient l'information de déplacement de la ou
 des tuile(s). Le numéro reprente l'ID de la tuile, la MahjongTileZone est la
 zone de jeu dans laquelle la tuile va être ajoutée
     * @param uuid
     * @throws fr.univubs.inf1603.mahjong.engine.game.MoveException
     */
    @ConstructorProperties({"player", "priority", "path", "uuid"})
    public Move(Side side, int priority, HashMap<Integer, MahjongTileZone> path, UUID uuid) throws MoveException {
        this.side = side;

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

    public Move(Side side, int priority, HashMap<Integer, MahjongTileZone> path) throws MoveException {
        this(side, priority, path, UUID.randomUUID());
    }

    /**
     * Retourne le coté du joueur associé au Move
     *
     * @return side
     */
    Side getSide() {
        return this.side;
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
    public HashMap<Integer, MahjongTileZone> getPath() {
        return this.path;
    }
}
