package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;

/**
 * Class dedicated for the functionality of SuperMushroom
 *
 * @author Klarissa Jutivannadevi
 */

public class SuperMushroom extends Item implements Consumable, Buyable {
    private static final int maxHPIncreaseAmount = 50;
    private static final int PRICE = 400;
    private int jumpRate;
    static int Price;

    /***
     * Constructor.
     *
     */
    public SuperMushroom() {
        super("Super Mushroom", '^', false);  // set portable to false so that item can't be dropped unless portability is toggled
        super.addAction(new ConsumeAction(this));
    }

    /**
     * Create and return an action to pick this Item up. SuperMushroom can always be picked up
     * If this Item is not portable, returns null.
     *
     * @param actor
     * @return a new PickUpItemAction if this Item is portable, null otherwise.
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new PickUpItemAction(this);
    }

    /**
     * Returns the item as an instance of an item object. Overridden method from ConsumableInterface
     * @return the item object
     */
    @Override
    public Item getItem() {
        return this;
    }


    @Override
    public void consume(Actor actor) {
        // Increase max HP by 50:
        actor.increaseMaxHp(maxHPIncreaseAmount);

        // Give TALL capability (can jump over anything), will change displaychar to uppercase, see player.getDisplayChar:
        actor.addCapability(Status.TALL);
    }

    @Override
    public int getPrice() {
        return PRICE;
    }
}
