package fr.univubs.inf1603.mahjong.engine.game;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Cette classe est une représentation d'une zone contenant des tuiles
 *
 * @author COGOLUEGNES Charles
 */
public class MahjongTileZone extends GameZone implements Serializable, Cloneable{

    /**
     * La liste des tuiles contenus dans la zone.
     */
    protected ArrayList<GameTile> tileList;

    /**
     * Le constructeur de TileZone prenant le nom et la liste de tuiles en
     * paramètres
     *
     * @param name Le nom de la TileZone
     * @param content la liste des tuiles
     * @param isHiddable Si la zone est cachable
     * @param uuid l identifiant unique de la zone de tuile
     * @throws ZoneException Si la liste des tuiles est null
     */
    public MahjongTileZone(String name, ArrayList<GameTile> content, boolean isHiddable,UUID uuid) throws ZoneException {
        super(uuid, name, isHiddable);
        if (content == null) {
            throw new ZoneException("La liste de tuiles ne peut pas être null.");
        }
        this.tileList = new ArrayList<>(content);
    }

    /**
     * Le constructeur de TileZone prenant que le nom en paramètre
     *
     * @param name Le nom de la TileZone
     * @param isHiddable Si la zone est cachable
     * @param uuid l identifiant unique de la zone de tuile
     */
    public MahjongTileZone(String name, boolean isHiddable,UUID uuid) {
        super(uuid, name, isHiddable);
        this.tileList = new ArrayList<>();
    }

    /**
     * Le constructeur de TileZone prenant que le nom et le fait que la zone soit cachable en paramètre
     *
     * @param name Le nom de la TileZone
     * @param isHiddable Si la zone est cachable
     */
    public MahjongTileZone(String name, boolean isHiddable) {
        super(UUID.randomUUID(), name, isHiddable);
        this.tileList = new ArrayList<GameTile>();
    }

    @Override
    public GameZone getClone() throws ZoneException{
        MahjongTileZone ret = new MahjongTileZone(this.getName(), this.tileList, this.hideable, UUID.randomUUID());
        return ret;
    }

    @Override
    public boolean setHidden() throws ZoneException {
        if (this.isHidden()) {
            throw new ZoneException("Trying to hide an already hidden Zone, risks of infinite loop");
        }
        if (this.hideable) {
            this.hidden = true;
            for (GameTile gt : this.tileList) {
                if (gt.getTile() != HiddenTile.HIDDENTILE) {
                    gt.setTile(HiddenTile.HIDDENTILE);
                }
            }
        }
        return this.hideable;
    }

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
    }

    /**
     * Permet de retourner la liste de GameTile
     * @return la liste de GameTile
     */
    public ArrayList<GameTile> getTiles() {
        return this.tileList;
    }

    /**
     * Permet de modifier la liste de GameTile
     * @param tiles La liste de tuiles
     */
    public void setTiles(ArrayList<GameTile> tiles) {
        ArrayList oldValue = this.tileList;
        this.tileList = new ArrayList(tiles);
        propertyChangeSupport.firePropertyChange("content", oldValue, this.tileList);
    }

    /**
     * Permet d'ajouter une tuile dans la liste
     * @param tile La tuile à ajouter
     * @return si la tuile à été ajoutée correctement
     */
    public boolean addTile(GameTile tile) { 
        boolean ret = this.tileList.add(tile);
        propertyChangeSupport.firePropertyChange("content", this.tileList, this.tileList);
        return ret;
    }

    /**
     * Permet de retirer une tuile dans la liste
     * @param tile La tuile à retirer
     * @return si la tuile à été retirée correctement
     */
    public boolean removeTile(GameTile tile) {
        boolean ret = this.tileList.remove(tile);
        propertyChangeSupport.firePropertyChange("content", this.tileList, this.tileList);
        return ret;
    }
}
