package fr.univubs.inf1603.mahjong.engine;

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
public class Zone extends GameZone implements Serializable, Cloneable, GameElement,UniqueIdentifiable {

    private ArrayList<GameZone> content;
    private final UUID uuid;    
    public Zone(String name, boolean hideable, boolean hidden, ArrayList<GameZone> content, UUID uuid) {
        super(name,hidden,hideable);
        this.uuid = uuid;
        this.content = content;
    }

    /**
     * Le constructeur simplifié de Zone, l'uuid est défini automatiquement
     *
     * @param name Le nom de la zone
     * @param hideable Si la zone est cachable ou non
     */
    public Zone(String name, boolean hideable) {
        this(name, hideable, false, new ArrayList<GameZone>(), UUID.randomUUID());
    }

    /**
     * Retourne l'UUID de la zone
     *
     * @return uuid
     */
    @Override
    public UUID getUUID() {
        return this.uuid;
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
    @Override
    public boolean setHidden() throws ZoneException {
        if (this.isHidden()) {
            throw new ZoneException("Trying to hide an already hidden Zone, risks of infinite loop");
        }
        if (this.hideable) {
            boolean oldValue = this.hidden;
            this.hidden = true;
            for (GameZone z : this.content) {
                if(!z.isHidden()){
                    z.setHidden();
                }
            }
            propertyChangeSupport.firePropertyChange("hidden", oldValue, this.hidden);
        }
        return this.hideable;
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

    @Override
    public ArrayList<GameElement> getContent() {
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
}
