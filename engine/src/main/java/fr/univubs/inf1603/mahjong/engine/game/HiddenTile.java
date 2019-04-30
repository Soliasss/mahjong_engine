package fr.univubs.inf1603.mahjong.engine.game;

import fr.univubs.inf1603.mahjong.engine.rule.ComplexTile;
import org.apache.log4j.Logger;

/**
 * Une réprésentation d'une tuile cachée
 */
public class HiddenTile extends ComplexTile{
  /**
   * Le nom générique d'une tuile cachée
   */
    
  private static final String NAME = "XX";
  public static HiddenTile HIDDENTILE=new HiddenTile();
  
     // Logger
   private static final Logger LOGGER = Logger.getLogger(HiddenTile.class.getName());

  /**
   * Le constructeur de HiddenTile
   */
  private HiddenTile(){ }
  
  @Override
  public String toNormalizedName(){
    LOGGER.info("method : toNormalizedName()");
    return NAME;
  }
}
