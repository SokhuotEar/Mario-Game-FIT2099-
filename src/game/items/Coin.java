package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.positions.Dirt;
import game.reset.Resettable;

/**
 * Coin class.
 * @author Sok Ear
 * @version 1.0
 */
public class Coin extends Item implements Resettable {
    /**
     * The value of the coin
     */
    private final int value;

    /**
     * Constructor.
     * @param value the value of the coin
     */
    public Coin(int value) {
        super("coin", '$',false); // to be changed
        this.value = value;
        this.registerInstance();
        super.addAction(new PickUpCoinAction(this));
    }


    /**
     * gets the value of the coin
     * @return the value of the coin
     */
    public int getValue() {
        return value;
    }

    /**
     * Resets the coin
     */
    @Override
    public void resetInstance(GameMap map) {
        // iterate over the map to find coin, then remove coin
        boolean found = false;
        for (int x : map.getXRange()) {
            for (int y : map.getYRange()) {
                if (map.at(x,y).getItems().contains(this)) {
                    found = true;
                    map.at(x,y).removeItem(this);
                    break;
                }
            }
            if (found) {
                break;
            }
        }
    }
}
