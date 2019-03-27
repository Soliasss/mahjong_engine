package fr.univubs.inf1603.mahjong.engine;

import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author Samuel LE BERRE, COGOLUEGNES Charles
 */
public abstract class GameZone implements UniqueIdentifiable{
    private final UUID uuid;
    private String name;
    protected boolean hidden;    
    protected final boolean hideable;
    
    public GameZone(UUID uuid, String name, boolean hidden,boolean hideable){
        this.uuid = uuid;
        this.name = name;
        this.hidden = hidden;
        this.hideable = hideable;
    }
    public GameZone(UUID uuid, String name, boolean hideable){
        this.uuid = uuid;
        this.name = name;
        this.hidden = false;
        this.hideable = hideable;
    }
    public abstract ArrayList<GameZone> getZones() throws ZoneException;
    public abstract ArrayList<GameTile> getTiles() throws ZoneException;
    public abstract void setZones(ArrayList<GameZone> zones) throws ZoneException;
    public abstract void setTiles(ArrayList<GameTile> tiles) throws ZoneException;
    
    /**
     * Permet d'ajouter une zone dans la liste
     *
     * @param zone La zone à ajouter
     * @return si la zone a correctement été ajoutée
     */
    public abstract boolean addZone(GameZone zone) throws ZoneException;
    
    /**
     * Permet de retirer une zone dans la liste
     *
     * @param zone La zone à retirer
     * @return si la zone a correctement été retirée
     */
    public abstract boolean removeZone(GameZone zone) throws ZoneException;
    
    /**
     * Permet d'ajouter une tuile dans la liste
     *
     * @param tile La tuile à ajouter
     * @return si la tuile à été ajoutée correctement
     */
    public abstract boolean addTile(GameTile tile) throws ZoneException;
    
    /**
     * Permet de retirer une tuile dans la liste
     *
     * @param tile La tuile à retirer
     * @return si la tuile à été retirée correctement
     */
    public abstract boolean removeTile(GameTile tile) throws ZoneException;
    
    public boolean isHidden(){
        return this.hidden;
    }
    
    /**
     * Permet de configurer l'attribut hidden, rendant la zone cachée ainsi que
     * toutes les zones filles
     *
     * @return si la zone a bien été cachée
     * @throws fr.univubs.inf1603.mahjong.engine.ZoneException
     */
    public abstract boolean setHidden() throws ZoneException;
    
    public boolean isHideable(){
        return this.hideable;
    }
    
    /**
     *
     * @return
     * @throws ZoneException
     */
    public abstract GameZone getClone() throws ZoneException;
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String newName){
        this.name = newName;
    }

    @Override
    public UUID getUUID() {
        return this.uuid;
    }
}
