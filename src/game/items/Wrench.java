package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;

import java.util.ArrayList;
import java.util.List;

public class Wrench extends WeaponItem implements Weapon, Buyable {
    private static final List<Wrench> wrenchList = new ArrayList<>();
    private static final int PRICE = 200;
    /**
     * Constructor.
     */
    public Wrench() {
        super("wrench", 'w', 50, "uses wrench to hit", 80);
        wrenchList.add(this);
    }

    public static boolean isInstance(Item item) {
        return wrenchList.contains(item);
    }

    @Override
    public int getPrice() {
        return PRICE;
    }

    @Override
    public Item getItem() {
        return this;
    }
}
