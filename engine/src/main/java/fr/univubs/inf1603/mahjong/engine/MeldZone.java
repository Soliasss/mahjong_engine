package fr.univubs.inf1603.mahjong.engine;

import java.util.ArrayList;

/**
 * Cette classe représente une zone de jeu représentant un combo de tuiles bien précis
 * @author COGOLUEGNES Charles
 */
public class MeldZone extends TileZone{
  private Meld name;

  /**
   * Le constructeur de MeldZone
   * @param name Le nom de la MeldZone (un Meld)
   * @param content La TileZone
   * @param isHiddable Si la zone est cachable
   */
  public MeldZone(Meld name, ArrayList<GameTile> content, boolean isHiddable){
    super(name.toString(), content, isHiddable);
    this.name = name;
  }

  /**
   * Permet d'ajouter une tuile dans la liste, le meld sera donc modifié
   * @param tile La tuile à ajouter
   * @param newMeld Le nouveau combo
   * @return si la tuile à été ajoutée correctement
   */
  public boolean add(GameTile tile, Meld newMeld){
    boolean ret = this.content.add(tile);
    if(ret) this.setName(newMeld.toString());
    return ret;
  }

  /**
   * Permet dde retirer une tuile dans la liste, le meld sera donc modifié
   * @param tile La tuile à retirer
   * @param newMeld Le nouveau combo
   * @return si la tuile à été retirée correctement
   */
  public boolean remove(GameTile tile, Meld newMeld){
    boolean ret = this.content.remove(tile);
    if(ret) this.setName(newMeld.toString());
    return ret;
  }
}
