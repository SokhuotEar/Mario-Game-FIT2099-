package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
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
    public void resetInstance() {
        //setDisplayChar(new Dirt().getDisplayChar());

    }

}
