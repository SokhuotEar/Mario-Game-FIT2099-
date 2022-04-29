package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

/**
 * Interface consumable holds all the methods of the items that can be consumed by player
 */
public interface Consumable {

    public Item getItem();

    public void consume(Actor actor);

}
