package fr.univubs.inf1603.mahjong.engine;

import java.util.ArrayList;

/**
 *
 * @author Samuel LE BERRE
 */
public abstract class GameZone implements GameElement{
    private String name;
    protected boolean hidden;    
    protected final boolean hideable;
    
    public GameZone(String name, boolean hidden,boolean hideable){
        this.name = name;
        this.hidden = hidden;
        this.hideable = hideable;
    }
    public GameZone(String name, boolean hideable){
        this.name = name;
        this.hidden = false;
        this.hideable = hideable;
    }
    public abstract ArrayList<? extends GameElement> getContent();
    public abstract void setContent(ArrayList<? extends GameElement> content);
    public abstract boolean add(GameElement GameElt);
    public abstract boolean remove(GameElement GameElt);
    
    public boolean isHidden(){
        return this.hidden;
    }
    
    public abstract boolean setHidden() throws ZoneException;
    
    public boolean isHideable(){
        return this.hideable;
    }
    
    @Override
    public abstract GameZone clone();
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String newName){
        this.name = newName;
    }
}
