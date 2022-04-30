package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;

import java.util.ArrayList;
import java.util.List;

/**
 * The class Wrench is a child class of WeaponItem and implements Weapon,
 * which shows that it has a behavior of a weapon, and also Buyable, since
 * it can be purchase.
 */
public class Wrench extends WeaponItem implements Weapon, Buyable {
    private static final List<Wrench> wrenchList = new ArrayList<>();
    private static final int PRICE = 200;

    /**
     * Constructor of Wrench. Every time this is created, a new wrench object
     * is added to the list of wrenchList.
     */
    public Wrench() {
        super("wrench", 'w', 50, "uses wrench to hit", 80);
        wrenchList.add(this);
    }

    /**
     * Check whether wrenchList contains wrench
     * @param item the item to be checked
     * @return boolean to tell whether it exist or not
     */
    public static boolean isInstance(Item item) {
        return wrenchList.contains(item);
    }

    /**
     * Accessor function for the price of the Wrench
     * @return price of Wrench
     */
    @Override
    public int getPrice() {
        return PRICE;
    }

    /**
     * Accessor function to get the Wrench item
     * @return Wrench item
     */
    @Override
    public Item getItem() {
        return this;
    }
}
