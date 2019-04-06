package fr.univubs.inf1603.mahjong.engine.game;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Cette classe représente une zone de jeu représentant un combo de tuiles bien précis
 * @author COGOLUEGNES Charles
 */
public class MeldZone extends TileZone{
    /**
     * Le constructeur de MeldZone
     * @param name Le nom de la MeldZone (un Meld)
     * @param content La TileZone
     * @param isHiddable Si la zone est cachable
     * @param uuid l identifiant unique de la zone de combo
     * @throws ZoneException si le content est null
     */
  public MeldZone(Meld name, ArrayList<GameTile> content, boolean isHiddable,UUID uuid) throws ZoneException{
    super(name.toString(), content, isHiddable,uuid);
  }

  /**
   * Permet d'ajouter une tuile dans la liste, le meld sera donc modifié
   * @param tile La tuile à ajouter
   * @param newMeld Le nouveau combo
   * @return si la tuile à été ajoutée correctement
   * @throws fr.univubs.inf1603.mahjong.engine.game.ZoneException
   */
  public boolean addTile(GameTile tile, Meld newMeld) throws ZoneException{
    boolean ret = super.addTile(tile);
    if(ret) this.setName(newMeld.toString());
    return ret;
  }

  /**
   * Permet de retirer une tuile dans la liste, le meld sera donc modifié
   * @param tile La tuile à retirer
   * @param newMeld Le nouveau combo
   * @return si la tuile à été retirée correctement
     * @throws fr.univubs.inf1603.mahjong.engine.game.ZoneException
   */
  public boolean removeTile(GameTile tile, Meld newMeld) throws ZoneException{
    boolean ret = super.removeTile(tile);
    if(ret) this.setName(newMeld.toString());
    return ret;
  }
}
