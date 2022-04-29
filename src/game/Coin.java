package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/** I made this only cuz I need the class to work on my part. Feel free to change the content. **/

public class Coin extends Item implements Resettable {
    static int value = 20;
    int age;
    /***
     * Constructor.
     */
    public Coin(int value) {
        super("coin", '$',true); // to be changed
        this.value = value;
        this.registerInstance();
    }

    public int getValue() {
        return value;
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {

        // TODO: can you fix instanceof here? Thanks
        if (actor instanceof Player) {

            //add it to player's wallet
            ((Player) actor).getWallet().addBalance(this.value);

            // System.out.println(((Player) actor).getWallet().getBalance()); //test only
        }
        return new PickUpItemAction(this);
    }

    @Override
    public void resetInstance() {
        setDisplayChar(new Dirt().getDisplayChar());

    }

}
