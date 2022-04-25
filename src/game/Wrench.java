package game;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.WeaponItem;

import java.util.ArrayList;
import java.util.List;

public class Wrench extends WeaponItem {
    private static List<Wrench> wrenchList = new ArrayList<Wrench>();
    /**
     * Constructor.

     */
    public Wrench() {
        super("wrench", 'W', 50, "hits with wrench", 80);
        wrenchList.add(this);
    }

    public static boolean isInstance(Item item) {
        return wrenchList.contains(item);
    }
}
