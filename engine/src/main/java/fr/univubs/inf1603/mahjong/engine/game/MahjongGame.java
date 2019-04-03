package fr.univubs.inf1603.mahjong.engine.game;

import fr.univubs.inf1603.mahjong.engine.rule.GameRule;
import java.util.ArrayList;

/**
 * Cette classe permet de représenter une partie de Mahjong
 * @author COGOLUEGNES Charles
 */
public class MahjongGame{
  private HashMap<Integer,Wind> listPlayers;
  private GameRule rule;
  private boolean finish;
  private Move lastMove;
  private Board board;
  
  /**
   * Le constructeur de Game
   * @param rule La règle qui va être jouée
   * @throws GameException si la règle est null
   */
  public MahjongGame(GameRule rule) throws GameException{
    if(rule == null) throw new GameException("Le règle ne peut pas être null.");
    this.rule = rule;
    this.lastMove = null;
    this.finish = false;
    this.board = null;
  }
  
  /**
   * Permet de lancer la partie
   * @return une map faisant correspondance entre le numéro du joueur et le vent
   */
  public Hash<Integer,Wind> launchGame(){
    HashMap<Integer,Wind> ret = this.rule.getPlayersOrder();
    this.board = this.rule.initBoard();
    return ret;
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
   * @throws ZoneException si certaines zones ne sont pas cachées alors qu'elle devrait l'être
   */
  public Board getBoard(int player) throws GameException{
    Board ret = this.board.clone();
    if(!(ret.getWallZone()).setHidden()) throw new GameException("La zone du mur n'a pas été cachée correctement.");
    for(int i=1; i<5; i++){
      if(i != player){
        if(!(ret.getPlayerZone(i)).setHidden()) throw new GameException("La zone du joueur "+i+" n'a pas été cachée correctement."); 
      }
    }
    return ret;
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
    ArrayList<Move> ret = this.rule.findValidMoves(this.board, this.lastMove);
    return ret;
  }
  
  /**
   * Retourne un clone du jeu en cours
   * @return un clone de Game
   */
  public Game clone(){
    return null;
  }
}
