package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

/**
 * Interface consumable holds all the methods of the items that can be consumed by player
 *
 * @author Satya Jhaveri, Klarissa Jutivannadevi
 * @version 1.0
 */
public interface Consumable {
    /**
     * Gets the consumable as an Item object
     * @return the consumable as an Item object
     */
    Item getItem();

    /**
     * Consumes the Consumable
     * @param actor the actor that will consume the consumable
     */
    void consume(Actor actor);

    /**
     * Default method to say whether the item should stay in the player's inventory after consumption. False by default, override to keep the item
     * @return false
     */
    default boolean stayInInventory() {
        return false;
    }

}
