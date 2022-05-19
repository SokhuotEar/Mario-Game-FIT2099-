package game.items;

import edu.monash.fit2099.engine.items.Item;

public class Key extends Item {
    private static Key instance = null;
    /***
     * Constructor.
     */
    private Key() {
        super("Key", 'k', true);
    }

    public static Key getInstance() {
        if (Key.instance == null) {
            instance = new Key();
        }
        return instance;
    }
}
