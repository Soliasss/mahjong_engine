package fr.univubs.inf1603.mahjong.engine;

/**
 * AbstractTile represents the concept of tile in mahjong rules.
 * @author Malléjac Clément
 */
public interface AbstractTile {
    /**
     * A tile's normalized name is a case-sensitive, two letters String (c.f Engine_designDoc.md).
     * @return Returns this AbstracTile's normalized name.
     */
    public String toNormalizedName();
}
