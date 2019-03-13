package fr.univubs.inf1603.mahjong.engine;

import java.util.ArrayList;
import java.util.UUID;

/**
 * This class is a representation of a zone where the money will be contains
 * @author COGOLUEGNES Charles
 */
public class MoneyZone implements Zone{
  private UUID id;
  private int money;

  /**
   * The constructor of MoneyZone, the id is generate in the constructor, the money is at the start equals at 0
   */
  public MoneyZone(){
    this.id = UUID.randomUUID();
    this.money = 0;
  }

  /**
   * Not suppose to be used in MoneyZone
   * @return null
   */
  public ArrayList<AbstractTile> getTilesCollection(){
    return null;
  }
  
  /**
   * Not suppose to be used in MoneyZone
   * @return null
   */
  public ArrayList<AbstractTile> getZonesCollection(){
    return null;
  }

  /**
   * Returns the amount of money contains in the zone
   * @return money
   */
  public int getMoney(){
    return this.money;
  }

  /**
   * Allows to add money in the zone
   * @param money
   */
  public void addMoney(int money){
    this.money += money;
  }

  /**
   * Allows to remove money in the zone
   * @param money
   * @throws MoneyZoneException if we want to remove more money that there is in the zone
   */
  public void removeMoney(int money) throws MoneyZoneException{
    if(this.money - money < 0) throw new MoneyZoneException("You cannot remove more money than there is in the zone.");
    this.money -= money;
  }
}
