package fr.univubs.inf1603.mahjong.engine;

import java.beans.ConstructorProperties;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.UUID;
import java.util.ArrayList;

/**
 * Cette classe définie une zone de jeu
 *
 * @author COGOLUEGNES Charles
 */
public class Zone implements Serializable, Cloneable {

    private String name;
    protected boolean hidden;
    protected final boolean hideable;
    private ArrayList<Zone> content;
    private final UUID uuid;

    @ConstructorProperties({"name", "hiddable", "hidden", "content", "uuid"})
    public Zone(String name, boolean hiddable, boolean hidden, ArrayList<Zone> content, UUID uuid) {
        this.uuid = uuid;
        this.name = name;
        this.content = content;
        this.hidden = hidden;
        this.hideable = hiddable;
    }

    /**
     * Le constructeur simplifié de Zone, l'uuid est défini automatiquement
     *
     * @param name Le nom de la zone
     * @param hiddable Si la zone est cachable ou non
     */
    public Zone(String name, boolean hiddable) {
        this(name, hiddable, false, new ArrayList<Zone>(), UUID.randomUUID());
    }

    /**
     * Retourne le contenu de la list de zone contenu dans une autre zone
     *
     * @return content, la liste de zone
     */
    public ArrayList getContent() {
        return this.content;
    }

    /**
     * Retourne l'UUID de la zone
     *
     * @return uuid
     */
    public UUID getUUID() {
        return this.uuid;
    }

    /**
     * Retourne le nom de la zone
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Permet d'ajouter une zone dans la liste
     *
     * @param zone La zone à ajouter
     * @return si la zone a correctement été ajoutée
     */
    public boolean add(Zone zone) {
        boolean ret = this.content.add(zone);
        propertyChangeSupport.firePropertyChange("content", this.content, this.content);
        return ret;
    }

    /**
     * Permet de retirer une zone dans la liste
     *
     * @param zone La zone à retirer
     * @return si la zone a correctement été retirée
     */
    public boolean remove(Zone zone) {
        boolean ret = this.content.remove(zone);
        propertyChangeSupport.firePropertyChange("content", this.content, this.content);
        return ret;
    }

    /**
     * Permet de configurer l'attribut hidden, rendant la zone cachée ainsi que
     * toutes les zones filles
     *
     * @return si la zone a bien été cachée
     * @throws fr.univubs.inf1603.mahjong.engine.ZoneException
     */
    public boolean setHidden() throws ZoneException {
        if (this.isHidden()) {
            throw new ZoneException("Trying to hide an already hidden Zone, risks of infinite loop");
        }
        if (this.hideable) {
            boolean oldValue = this.hidden;
            this.hidden = true;
            for (Zone z : this.content) {
                if(!z.isHidden()){
                    z.setHidden();
                }
            }
            propertyChangeSupport.firePropertyChange("hidden", oldValue, this.hidden);
        }
        return this.hideable;
    }

    /**
     * Retourne si la zone peut être cachée
     *
     * @return si la zone peut être cachée
     */
    public boolean isHideable() {
        return this.hideable;
    }

    /**
     * Retourne si la zone est cachée ou non
     *
     * @return si la zone est cachée ou non
     */
    public boolean isHidden() {
        return this.hidden;
    }

    /**
     * Permet de modifier le nom de la zone
     *
     * @param newName Le nouveau nom
     */
    public void setName(String newName) {
        String oldValue = this.name;
        this.name = newName;
        propertyChangeSupport.firePropertyChange("name", oldValue, this.name);

    }

    /**
     * Permet de modifier content
     *
     * @param content Le nouveau content
     */
    public void setContent(ArrayList content) {
        ArrayList oldValue = this.content;
        this.content = new ArrayList(content);
        propertyChangeSupport.firePropertyChange("content", oldValue, this.content);
    }

    /**
     * Rends une copie conforme de Zone actuelle (UUID different)
     *
     * @return la Zone
     */
    @Override
    public Zone clone() {
        return new Zone(this.getName(),
                this.isHideable(),
                this.isHidden(),
                this.cloneContent(),
                UUID.randomUUID());
    }

    private ArrayList cloneContent() {
        ArrayList temp = new ArrayList();
        for (Object o : this.content) {
            if (o instanceof Zone) {
                temp.add(((Zone) o).clone());
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
}
