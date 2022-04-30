package game.items;

import edu.monash.fit2099.engine.items.Item;

/**
 * Interface buyable holds all the methods of the items that can be bought by player
 */
public interface Buyable {
    public int getPrice();
    public Item getItem();

}
