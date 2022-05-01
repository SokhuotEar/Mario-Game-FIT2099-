package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
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
     * Whether the coin will be reset this turn
     */
    private boolean reset;

    /**
     * Constructor.
     * @param value the value of the coin
     */
    public Coin(int value) {
        super("coin", '$',false); // to be changed
        this.value = value;
        this.registerInstance();
        super.addAction(new PickUpCoinAction(this));
        reset = false;
    }

    /**
     * This method is called once per turn, if the coin is on the ground.
     *
     * @param currentLocation The location of the ground
     */
    @Override
    public void tick(Location currentLocation) {
        if (reset) {
            currentLocation.removeItem(this);
        }
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
    public void resetInstance() {
        reset = true;
    }

}
