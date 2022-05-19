package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import game.utils.Status;
import game.actions.ConsumeAction;

/**
 * The class SuperMushroom is a child class of Item class which allow
 * overriding method to modify its functionality and implements both
 * Consumable and Buyable since it can be consumed and purchased.
 *
 * @author Satya Jhaveri, Klarissa Jutivannadevi
 * @version 1.0
 */

public class SuperMushroom extends Item implements Consumable, Buyable {
    /**
     * The HP of SuperMushroom to be added to actor who consumed
     */
    private static final int maxHPIncreaseAmount = 50;

    /**
     * The price to purchase SuperMushroom
     */
    private static final int PRICE = 400;

    /***
     * Constructor creates ConsumeAction.
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
     * @param actor the actor that will pick up the item
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

    /**
     * Allow power up to actor based on the item consumed
     * @param actor the actor that consumes item
     */
    @Override
    public void consume(Actor actor) {
        // Increase max HP by 50:
        actor.increaseMaxHp(maxHPIncreaseAmount);

        // Give TALL capability (can jump over anything):
        actor.addCapability(Status.TALL);
    }

    /**
     * Accessor function of the PRICE attribute of SuperMushroom
     * @return price of SuperMushroom
     */
    @Override
    public int getPrice() {
        return PRICE;
    }
}
