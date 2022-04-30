package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

/**
 * Interface consumable holds all the methods of the items that can be consumed by player
 *
 * @author Satya Jhaveri, Klarissa Jutivannadevi
 */
public interface Consumable {
    Item getItem();
    void consume(Actor actor);

    default boolean stayInInventory() {
        return false;
    }

}
