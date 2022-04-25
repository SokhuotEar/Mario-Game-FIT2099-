package game;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import edu.monash.fit2099.engine.weapons.WeaponItem;

import java.util.ArrayList;
import java.util.List;

public class Wrench extends WeaponItem implements Weapon {
    private static final List<Wrench> wrenchList = new ArrayList<>();
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
}
