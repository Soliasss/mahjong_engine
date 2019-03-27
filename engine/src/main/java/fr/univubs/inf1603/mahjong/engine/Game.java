package fr.univubs.inf1603.mahjong.engine;

import java.util.ArrayList;

/**
 * Cette classe permet de représenter une partie de Mahjong
 * @author COGOLUEGNES Charles
 */
public class Game{
  private HashMap<Integer,Wind> listPlayers;
  private GameRule rule;
  private boolean finish;
  private Move lastMove;
  
  /**
   * Le constructeur de Game
   * @param rule La règle qui va être jouée
   * @throws GameException si la règle est null
   */
  public Game(GameRule rule) throws GameException{
    if(rule == null) throw new GameException("Le règle ne peut pas être null.");
    this.rule = rule;
  }
  
  /**
   * Permet de lancer la partie
   * @return une map faisant correspondance entre le numéro du joueur et le vent
   */
  public Hash<Integer,Wind> launchGame(){
    return null;
  }
  
  /**
   * Permet d'effectuer un Move sur le Board
   * @param move Le Move
   * @return si le Move a été effectué
   */
  public boolean makeMove(Move move){
    this.lastMove = move;
    return false;
  }
  
  /**
   * Retourne si la partie est finie ou non
   * @return si la partie est finie ou non
   */
  public boolean isFinish(){
    return this.finish; 
  }
  
  /**
   * Retourne un Board en fonction du joueur, si joueur=0 alors retourne le Board en entier avec les zones non cachées
   * @param player le joueur
   * @return le board du joueur
   */
  public Board getBoard(int player){
    return null;
  }
  
  /**
   * Retourne le nombre de points d'un joueur à un instant t
   * @param player Le joueur
   * @return le nombre de points du joueur
   */
  public int getPlayerPoints(int player){
    return 0;
  }
  
  /**
   * Retourne la liste possible de Move à effectuer pour tout les joueurs en fonction de l'état du Board
   * @return la liste possible de Move
   */
  public ArrayList<Move> getPossibleMoves(){
    return null;
  }
  
  /**
   * Retourne un clone du jeu en cours
   * @return un clone de Game
   */
  public Game clone(){
    return null;
  }
}
