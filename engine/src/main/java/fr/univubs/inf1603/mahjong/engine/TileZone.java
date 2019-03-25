package fr.univubs.inf1603.mahjong.engine;

import java.util.ArrayList;
/**
 * Cette classe est une représentation d'une zone contenant des tuiles
 * @author COGOLUEGNES Charles
 */
public class TileZone extends Zone{
  private ArrayList<GameTile> content;

  /**
   * Le constructeur de TileZone prenant que le nom en paramètre
   * @param name Le nom de la TileZone
   * @param isHiddable Si la zone est cachable
   */
  public TileZone(String name, boolean isHiddable){
    super(name, isHiddable);
    this.content = new ArrayList<GameTile>();
  }

  /**
   * Le constructeur de TileZone prenant le nom et la liste de tuiles en paramètres
   * @param name Le nom de la TileZone
   * @param content la liste des tuiles
   * @param isHiddable Si la zone est cachable
   * @throws ZoneException Si la liste des tuiles est null
   */
  public TileZone(String name, ArrayList<GameTile> content, boolean isHiddable) throws ZoneException{
    super(name, isHiddable);
    if(content == null) throw new ZoneException("La liste de tuiles ne peut pas être null.");
    this.content = new ArrayList(content);
  }
  
  /**
   * Le constructeur de copie de TileZone
   * @param content La TileZone
   */
  public TileZone(TileZone content){
	  super(content.getName(), content.isHiddable());
	  this.content = new ArrayList(content.getContent());
  }

  /**
   * Permet d'ajouter une tuile dans la liste
   * @param tile La tuile à ajouter
   * @return si la tuile à été ajoutée correctement
   */
  public boolean add(GameTile tile){
    return this.content.add(tile);
  }

  /**
   * Permet de retirer une tuile dans la liste
   * @param tile La tuile à retirer
   * @return si la tuile à été retirée correctement
   */
  public boolean remove(GameTile tile){
    return this.content.remove(tile);
  }
    
  @Override
  public boolean setHidden(){
    boolean ret = false;
    if(this.isHiddable){
      this.isHidden = true;
      for(GameTile gt : this.content) gt.setTile(new CheapTile("XX"));
      ret = true;
    }
    return ret;
  }
}
