package game.items;

import edu.monash.fit2099.engine.items.Item;

/**
 * Interface buyable holds all the methods of the items that can be bought by player
 * @author Sok Ear
 * @version 1.0
 */
public interface Buyable {
    /**
     * Gets the price of the Buyable
     * @return the price of the buyable
     */
    public int getPrice();

    /**
     * Gets the buyable item as an item object
     * @return the buyable item as an instance of Item
     */
    public Item getItem();

}
