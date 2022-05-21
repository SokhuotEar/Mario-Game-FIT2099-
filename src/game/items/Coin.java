package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PickUpCoinAction;
import game.reset.ResetManager;
import game.reset.Resettable;

/**
 * Coin class.
 * @author Sok Ear
 * @version 1.0
 */
public class Coin extends Item implements Resettable{
    /**
     * The value of the coin
     */
    private final int value;

    /**
     * Constructor.
     * @param value the value of the coin
     */
    public Coin(int value) {
        super("coin", '$',false);
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
                    // remove the Coin from the Reset Manager:
                    ResetManager.getInstance().cleanUp(this);
                    break;
                }
            }
            if (found) {
                break;
            }
        }
    }
}
