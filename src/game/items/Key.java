package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.utils.Status;

/**
 * Key class.
 * @author Satya Jhaveri
 * @version 1.0
 */
public class Key extends Item {
    /**
     * The instance of this item, since there is only one key to use to free Peach
     */
    private static Key instance = null;
    /***
     * Constructor.
     */
    private Key() {
        super("Key", 'k', true);
        this.addCapability(Status.CAN_FREE_PEACH);
    }

    public static Key getInstance() {
        if (Key.instance == null) {
            instance = new Key();
        }
        return instance;
    }
}
