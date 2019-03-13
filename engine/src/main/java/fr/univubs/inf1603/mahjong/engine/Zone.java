package fr.univubs.inf1603.mahjong.engine;

import java.util.ArrayList;

/**
 * This interface defines what's a Zone in the game
 * @author COGOLUEGNES Charles
 */
public interface Zone{
  /**
   * Returns the collection of tiles contains in the zone
   * @return the collection of tiles
   */
  public Collection<AbstractTile> getTilesCollection();
  
  /**
   * Returns the collection of zone contains in the zone
   * @return the collection of zone
   */
  public Collection<Zone> getZonesCollection();

  /**
   * Returns the amount of money contains in the zone
   * @return the money
   */
  public int getMoney();
}
