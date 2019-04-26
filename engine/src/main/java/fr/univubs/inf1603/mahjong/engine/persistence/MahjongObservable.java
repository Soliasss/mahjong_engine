package fr.univubs.inf1603.mahjong.engine.persistence;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * This interface provides methods to listen for properties of an object
 */
public interface MahjongObservable {
    
    PropertyChangeSupport getPropertyChangeSupport();
    
    default void addPropertyChangeListener(PropertyChangeListener listener) {
        getPropertyChangeSupport().addPropertyChangeListener(listener);
    }

    default void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        getPropertyChangeSupport().addPropertyChangeListener(propertyName, listener);
    }

    default void removePropertyChangeListener(PropertyChangeListener listener) {
        getPropertyChangeSupport().removePropertyChangeListener(listener);
    }

    default void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        getPropertyChangeSupport().removePropertyChangeListener(propertyName, listener);
    }
}
