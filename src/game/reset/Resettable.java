package game.reset;

import edu.monash.fit2099.engine.positions.GameMap;
import game.reset.ResetManager;

/**
 * Objects that must be reset will implement this interface
 * @author FIT2099, modified by Klarissa Jutivannadevi
 * @version 1.0
 */
public interface Resettable {
    /**
     * Allows any classes that use this interface to reset abilities, attributes, and/or items.
     */
    void resetInstance(GameMap map);

    /**
     * a default interface method that register current instance to the Singleton manager.
     * It allows corresponding class uses to be affected by global reset
     */
    default void registerInstance(){
        ResetManager.getInstance().appendResetInstance(this);
    }
}
