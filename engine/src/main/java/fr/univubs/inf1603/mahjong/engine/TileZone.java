package fr.univubs.inf1603.mahjong.engine;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Cette classe est une représentation d'une zone contenant des tuiles
 *
 * @author COGOLUEGNES Charles
 */
public class TileZone extends GameZone implements UniqueIdentifiable {

    protected ArrayList<GameTile> content;
    private final UUID uuid;

    /**
     * Le constructeur de TileZone prenant le nom et la liste de tuiles en
     * paramètres
     *
     * @param name Le nom de la TileZone
     * @param content la liste des tuiles
     * @param isHiddable Si la zone est cachable
     * @param uuid
     * @throws ZoneException Si la liste des tuiles est null
     */
    public TileZone(String name, ArrayList<GameTile> content, boolean isHiddable,UUID uuid) throws ZoneException {
        super(name, isHiddable);
        if (content == null) {
            throw new ZoneException("La liste de tuiles ne peut pas être null.");
        }
        this.content = new ArrayList(content);
        this.uuid = uuid;
    }

    /**
     * Le constructeur de TileZone prenant que le nom en paramètre
     *
     * @param name Le nom de la TileZone
     * @param isHiddable Si la zone est cachable
     * @param uuid
     */
    public TileZone(String name, boolean isHiddable,UUID uuid) {
        super(name, isHiddable);
        this.content = new ArrayList<GameTile>();
        this.uuid = uuid;
    }

    public TileZone(String name, boolean isHiddable) {
        super(name, isHiddable);
        this.content = new ArrayList<GameTile>();
        this.uuid = UUID.randomUUID();
    }
    

    /**
     * Permet d'ajouter une tuile dans la liste
     *
     * @param tile La tuile à ajouter
     * @return si la tuile à été ajoutée correctement
     */
    public boolean add(GameTile tile) {
        boolean ret = this.content.add(tile);
        propertyChangeSupport.firePropertyChange("content", this.content, this.content);
        return ret;
    }

    @Override
    public ArrayList getContent() {
        return this.content;
    }

    @Override
    public void setContent(ArrayList<? extends GameElement> content) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(GameElement GameElt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(GameElement GameElt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GameZone clone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Permet de retirer une tuile dans la liste
     *
     * @param tile La tuile à retirer
     * @return si la tuile à été retirée correctement
     */
    public boolean remove(GameTile tile) {
        boolean ret = this.content.remove(tile);
        propertyChangeSupport.firePropertyChange("content", this.content, this.content);
        return ret;
    }

    @Override
    public boolean setHidden() throws ZoneException {
        if (this.isHidden()) {
            throw new ZoneException("Trying to hide an already hidden Zone, risks of infinite loop");
        }
        if (this.hideable) {
            this.hidden = true;
            for (GameTile gt : this.content) {
                if (gt.getTile() != HiddenTile.HIDDENTILE) {
                    gt.setTile(HiddenTile.HIDDENTILE);
                }
            }
        }
        return this.hideable;
    }

    private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    @Override
    public UUID getUUID() {
        return this.uuid;
    }
}
