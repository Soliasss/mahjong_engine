package fr.univubs.inf1603.mahjong.engine.game;

import fr.univubs.inf1603.mahjong.engine.GameTile;
import fr.univubs.inf1603.mahjong.engine.GameZone;
import fr.univubs.inf1603.mahjong.engine.ZoneException;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Cette classe définie une zone de jeu
 *
 * @author COGOLUEGNES Charles
 */
public class Zone extends GameZone implements Serializable, Cloneable{

    /**
     * La liste des zones contenu dans la zone
     */
    private ArrayList<GameZone> zoneList;
    
    /**
     * Constructeur de Zone avec tous les paramètres
     * @param name Le nom de la zone
     * @param hideable Le fait que la zone soit cachable ou non
     * @param hidden Le fait que la zone soit caché a la creation
     * @param content La liste des zones contenu dans la zone
     * @param uuid L identifiant unique de la zone
     * @throws ZoneException si la liste est null
     */
    public Zone(String name, boolean hideable, boolean hidden, ArrayList<GameZone> content, UUID uuid) throws ZoneException{
        super(uuid, name,hidden,hideable);
        if(content == null) throw new ZoneException("La liste de zones ne peut pas être null.");
        this.zoneList = new ArrayList<>(content);
    }

    /**
     * Le constructeur simplifié de Zone, l'uuid est défini automatiquement
     *
     * @param name Le nom de la zone
     * @param hideable Si la zone est cachable ou non
     */
    public Zone(String name, boolean hideable) throws ZoneException{
        this(name, hideable, false, new ArrayList<GameZone>(), UUID.randomUUID());
    }

    @Override
    public boolean setHidden() throws ZoneException {
        if (this.isHidden()) {
            throw new ZoneException("Trying to hide an already hidden Zone, risks of infinite loop");
        }
        if (this.hideable) {
            boolean oldValue = this.hidden;
            this.hidden = true;
            for (GameZone z : this.zoneList) {
                if(!z.isHidden()){
                    z.setHidden();
                }
            }
            propertyChangeSupport.firePropertyChange("hidden", oldValue, this.hidden);
        }
        return this.hideable;
    }

    /**
     * Rends une copie conforme de Zone actuelle (UUID different)
     *
     * @return la Zone
     * @throws ZoneException si la liste de zone contenu dans la zone est null
     */
    @Override
    public Zone getClone() throws ZoneException{
        return new Zone(this.getName(),
                this.isHideable(),
                this.isHidden(),
                this.cloneContent(),
                UUID.randomUUID());
    }

    /**
     * Clone le contenu de la liste des zones
     * @return Le clone de la liste des zones
     */
    private ArrayList cloneContent() throws ZoneException{
        ArrayList temp = new ArrayList();
        for (Object o : this.zoneList) {
            if (o instanceof Zone) {
                temp.add(((Zone) o).getClone());
            }
            if (o instanceof GameTile) {
                temp.add(((GameTile) o).clone());
            }

        }
        return temp;
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

    @Override
    public ArrayList<GameZone> getZones() {
        return this.zoneList;
    }

    @Override
    public ArrayList<GameTile> getTiles() throws ZoneException {
        throw new ZoneException("Impossible d'avoir une liste de tuiles sur cette zone.");
    }

    @Override
    public void setZones(ArrayList<GameZone> zones) throws ZoneException {
        ArrayList oldValue = this.zoneList;
        this.zoneList = new ArrayList(zones);
        propertyChangeSupport.firePropertyChange("content", oldValue, this.zoneList);
    }

    @Override
    public void setTiles(ArrayList<GameTile> tiles) throws ZoneException {
        throw new ZoneException("Impossible de modifier la liste de zone en liste de tuiles.");
    }

    @Override
    public boolean addZone(GameZone zone) throws ZoneException {
        boolean ret = this.zoneList.add(zone);
        propertyChangeSupport.firePropertyChange("content", this.zoneList, this.zoneList);
        return ret;
    }

    @Override
    public boolean removeZone(GameZone zone) throws ZoneException {
        boolean ret = this.zoneList.remove(zone);
        propertyChangeSupport.firePropertyChange("content", this.zoneList, this.zoneList);
        return ret;
    }

    @Override
    public boolean addTile(GameTile tile) throws ZoneException {
        throw new ZoneException("Impossible d'ajouter une tuile dans une liste de zones."); 
    }

    @Override
    public boolean removeTile(GameTile tile) throws ZoneException {
        throw new ZoneException("Impossible de retirer une tuile dans une liste de zones."); 
    }
}
