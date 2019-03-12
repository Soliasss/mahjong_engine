package fr.univubs.inf1603.mahjong.engine;

import java.util.ArrayList;

/**
 *
 */
public interface Zone{
  /**
   *
   * @return
   */
  public ArrayList<Tile> getCollection();

  /**
   *
   * @return
   */
  public int getMoney();
}
