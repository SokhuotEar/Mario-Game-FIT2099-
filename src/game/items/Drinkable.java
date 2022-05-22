package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Interface drinkable that drinkable items will implement
 *
 * @author Satya Jhaveri, Klarissa Jutivannadevi
 * @version 1.0
 */
public interface Drinkable {
    /**
     * Method executed when a Drinkable object is 'drunk'
     * @param actor The actor drinking this object
     */
    void drink(Actor actor);

    /**
     * Gets a descriptive string of the drink
     * @return A string of the Drink
     */
    String toString();
}
