package fr.univubs.inf1603.mahjong.engine;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @author Samuel LE BERRE, COGOLUEGNES Charles
 */
public abstract class GameZone implements UniqueIdentifiable{
    private final UUID uuid;
    private String name;
    protected boolean hidden;    
    protected final boolean hideable;
    
    /**
     * Le constructeur complet de GameZone
     * @param uuid L'uuid de la GameZone
     * @param name Le nom de la GameZone
     * @param hidden Si la GameZone est cachée
     * @param hideable Si la GameZone est cachable
     */
    public GameZone(UUID uuid, String name, boolean hidden,boolean hideable){
        this.uuid = uuid;
        this.name = name;
        this.hidden = hidden;
        this.hideable = hideable;
    }
    
    /**
     * Le constructeur partiel de GameZone
     * @param uuid L'uuid de la GameZone
     * @param name Le nom de la GameZone
     * @param hideable Si la GameZone est cachable
     */
    public GameZone(UUID uuid, String name, boolean hideable){
        this.uuid = uuid;
        this.name = name;
        this.hidden = false;
        this.hideable = hideable;
    }
    
    /**
     * Permet de retourner la liste de GameZone
     * @return la liste de GameZone
     * @throws ZoneException Si la GameZone ne peut pas retourner une liste de zones
     */
    public abstract ArrayList<GameZone> getZones() throws ZoneException;
    
    /**
     * Permet de retourner la liste de GameTile
     * @return la liste de GameTile
     * @throws ZoneException Si la GameZone ne peut pas retourner une liste de tuiles
     */
    public abstract ArrayList<GameTile> getTiles() throws ZoneException;
    
    /**
     * Permet de modifier la liste de GameZone
     * @param zones La liste de zones
     * @throws ZoneException Si la GameZone ne peut pas modifer sa liste par une liste de zones
     */
    public abstract void setZones(ArrayList<GameZone> zones) throws ZoneException;
    
    /**
     * Permet de modifier la liste de GameTile
     * @param tiles La liste de tuiles
     * @throws ZoneException Si la GameZone ne peut pas modifer sa liste par une liste de tuiles
     */
    public abstract void setTiles(ArrayList<GameTile> tiles) throws ZoneException;
    
    /**
     * Permet d'ajouter une zone dans la liste
     * @param zone La zone à ajouter
     * @return si la zone a correctement été ajoutée
     */
    public abstract boolean addZone(GameZone zone) throws ZoneException;
    
    /**
     * Permet de retirer une zone dans la liste
     * @param zone La zone à retirer
     * @return si la zone a correctement été retirée
     */
    public abstract boolean removeZone(GameZone zone) throws ZoneException;
    
    /**
     * Permet d'ajouter une tuile dans la liste
     * @param tile La tuile à ajouter
     * @return si la tuile à été ajoutée correctement
     */
    public abstract boolean addTile(GameTile tile) throws ZoneException;
    
    /**
     * Permet de retirer une tuile dans la liste
     * @param tile La tuile à retirer
     * @return si la tuile à été retirée correctement
     */
    public abstract boolean removeTile(GameTile tile) throws ZoneException;
    
    /**
     * Retourne si la zone est cachée
     * @return si la zone est cachée
     */
    public boolean isHidden(){
        return this.hidden;
    }
    
    /**
     * Permet de configurer l'attribut hidden, rendant la zone cachée ainsi que toutes les zones filles
     * @return si la zone a bien été cachée
     * @throws fr.univubs.inf1603.mahjong.engine.ZoneException
     */
    public abstract boolean setHidden() throws ZoneException;
    
    /**
     * Retourne si la zone est cachable
     * @return si la zone est cachable
     */
    public boolean isHideable(){
        return this.hideable;
    }
    
    /**
     * Retourne un clone de la GameZone
     * @return un clone de la GameZone
     * @throws ZoneException si la liste de la GameZone est null lors du clonage
     */
    public abstract GameZone getClone() throws ZoneException;
    
    /**
     * Retourne le nom de la GameZone
     * @return le nom de la GameZone
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * Permet de modifier le nom de la GameZone
     * @param newName Le nouveau nom de la GameZone
     */
    public void setName(String newName){
        this.name = newName;
    }

    @Override
    public UUID getUUID() {
        return this.uuid;
    }
}
