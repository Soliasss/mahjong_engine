package fr.univubs.inf1603.mahjong.engine;

/**
 * @author COGOLUEGNES Charles, LE BERRE Samuel
 * Une réprésentation d'une tuile cachée
 */
public class HiddenTile extends ComplexTile{
  /**
   * Le nom générique d'une tuile cachée
   */
  private static final String NAME = "XX";
  public static HiddenTile HIDDENTILE;
  /**
   * Le constructeur de HiddenTile
   */
  private HiddenTile(){ }
  
  @Override
  public String toNormalizedName(){
    return NAME;
  }
}
