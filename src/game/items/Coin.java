package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.reset.Resettable;

/** I made this only cuz I need the class to work on my part. Feel free to change the content. **/

public class Coin extends Item implements Resettable {
    private int value;
    int age;
    /***
     * Constructor.
     */
    public Coin(int value) {
        super("coin", '$',false); // to be changed
        this.value = value;
        this.registerInstance();
        super.addAction(new PickUpCoinAction(this));
    }

    public int getValue() {
        return value;
    }

    @Override
    public void resetInstance() {
        //setDisplayChar(new Dirt().getDisplayChar());

    }

}
