package fr.univubs.inf1603.mahjong.engine.game;

import fr.univubs.inf1603.mahjong.engine.rule.ComplexTile;

/**
 * Une réprésentation d'une tuile cachée
 */
public class HiddenTile extends ComplexTile{
  /**
   * Le nom générique d'une tuile cachée
   */
  private static final String NAME = "XX";
  public static HiddenTile HIDDENTILE=new HiddenTile();
  /**
   * Le constructeur de HiddenTile
   */
  private HiddenTile(){ }
  
  @Override
  public String toNormalizedName(){
    return NAME;
  }
}
