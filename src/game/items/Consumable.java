package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

/**
 * Interface consumable holds all the methods of the items that can be consumed by player
 */
public interface Consumable {

    /**
     * Accessor for the Item object.
     * @return the item
     */
    public Item getItem();

    /**
     * Used for updating the actor functionality after consuming certain object.
     * @param actor the actor that consumes
     */
    public void consume(Actor actor);

}
