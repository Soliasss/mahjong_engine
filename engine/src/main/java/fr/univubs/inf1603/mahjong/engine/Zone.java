package fr.univubs.inf1603.mahjong.engine;

import java.util.UUID;
import java.util.ArrayList;

/**
 * Cette classe définie une zone de jeu
 * @author COGOLUEGNES Charles
 */
public class Zone{
  private UUID uuid;
  private String name;
  private ArrayList<Zone> content;
  protected boolean isHidden;
  protected boolean isHiddable;

  /**
   * Le constructeur de Zone, l'uuid est défini automatiquement
   * @param name Le nom de la zone
   * @param hiddable Si la zone est cachable ou non
   */
  public Zone(String name, boolean hiddable){
    this.uuid = UUID.randomUUID();
    this.name = name;
    this.content = new ArrayList<Zone>();
    this.isHidden = false;
  this.isHiddable = hiddable;
  }

  /**
   * Retourne le contenu de la list de zone contenu dans une autre zone
   * @return content, la liste de zone
   */
  public ArrayList getContent(){
    if(!isHidden) return this.content;
    else{
      ArrayList<Integer> ret = new ArrayList<Integer>();
      for(int i=0; i<this.content.size(); i++) ret.add(new Integer(i));
      ret.trimToSize();
      return ret;
    }
  }

  /**
   * Retourne l'UUID de la zone
   * @return uuid
   */
  public UUID getUUID(){
    return this.uuid;
  }

  /**
   * Retourne le nom de la zone
   * @return name
   */
  public String getName(){
    return this.name;
  }

  /**
   * Permet d'ajouter une zone dans la liste
   * @param zone La zone à ajouter
   * @return si la zone a correctement été ajoutée
   */
  public boolean add(Zone zone){
    return this.content.add(zone);
  }

  /**
   * Permet de retirer une zone dans la liste
   * @param zone La zone à retirer
   * @return si la zone a correctement été retirée
   */
  public boolean remove(Zone zone){
    return this.content.remove(zone);
  }

  /**
   * Permet de configurer l'attribut isHidden, rendant la zone cachée
   * ainsi que toutes les zones filles
   * @return si la zone a bien été cachée
   */
  public boolean setHidden(){
    boolean ret = false;
    if(this.isHiddable){
      ret = true;
      this.isHidden = true;
      for(Zone z : this.content) z.setHidden();
    }
    return ret;
  }
  
  /**
   * Retourne si la zone peut être cachée
   * @return si la zone peut être cachée
   */
  public boolean isHiddable(){
    return this.isHiddable;
  }
  
  /**
   * Retourne si la zone est cachée ou non
   * @return si la zone est cachée ou non
   */
  public boolean isHidden(){
    return this.isHidden;
  }

  /**
   * Permet de modifier le nom de la zone
   * @param newName Le nouveau nom
   */
  public void setName(String newName){
    this.name = newName;
  }
  
  /**
   * Permet de modifier l'UUID
   * @param uuid Le nouveau uuid
   */
  public void setUUID(UUID uuid){
    this.uuid = uuid;
  }
  
  /**
   * Permet de modifier content
   * @param content Le nouveau content
   */
  public void setContent(ArrayList content){
  this.content = new ArrayList(content);
  }
  
  /**
   * Rends une copie conforme de Zone actuelle
   * @return la Zone
   */
  public Zone clone(){
    Zone ret = new Zone(this.name, this.isHiddable);
    ret.setUUID(this.uuid);
    ret.setContent(this.content);
    ret.setHidden();
    return ret;
  }
}
