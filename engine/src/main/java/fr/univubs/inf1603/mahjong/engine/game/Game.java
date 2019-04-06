package fr.univubs.inf1603.mahjong.engine.game;

import fr.univubs.inf1603.mahjong.engine.persistence.Persistable;
import java.time.Duration;
import java.util.ArrayList;

/**
 * 
 * @author purpl
 */
public interface Game extends Persistable {

    /**
     * @return The time a stealing phase can last up to.
     */
    public Duration getStealingTime();

    /**
     * @return The time a non stealing phase can last up to.
     */    
    public Duration getPlayingTime();
    
    public Move getLastMove();
    /**
     * Permet de lancer la partie
     *
     * @return une map faisant correspondance entre le numéro du joueur et le
     * vent
     */
    public Side[] launchGame();

    /**
     * Ajoute un Move qui sera (ou non), effectué en fonction du context actuel (timing, validité, ...)
     * Un Move peut aussi etre refusé (différent de non jouer)
     * e.g Si un move décrit un changement correspondant à un état de jeu precedent, il sera refusé.
     * @param move Move à considérer
     * @throws GameException Quand un move est refusé, detaille la raison du refus
     */
    public void registerMove(Move move) throws GameException;

    /**
     * Retourne si la partie est finie ou non
     *
     * @return si la partie est finie ou non
     */
    public boolean isFinish();

    /**
     * Retourne un Board en fonction du numero du joueur, si joueur est différent de 0,1,2 ou 3 alors retourne le
     * Board en entier avec les zones non cachées
     *
     * @param player le joueur
     * @return le board du joueur
     * @throws ZoneException si certaines zones ne sont pas cachées alors
     * qu'elle devrait l'être
     */
    public Board getBoardView(int player) throws GameException;

    /**
     * Retourne le nombre de points d'un joueur à un instant t
     *
     * @param player Le joueur
     * @return le nombre de points du joueur
     */
    public int getPlayerPoints(int player);

    /**
     * Retourne la liste possible de Move à effectuer pour tout les joueurs en
     * fonction de l'état du Board
     *
     * @return la liste possible de Move
     */
    public ArrayList<Move> getPossibleMoves();

    
    
    /**
     * Retourne un clone du jeu en cours
     *
     * @return un clone de Game
     */
    public Game clone();
}
