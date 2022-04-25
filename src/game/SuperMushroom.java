package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;

/**
 * Class dedicated for the functionality of SuperMushroom
 *
 * @author Klarissa Jutivannadevi
 */

public class SuperMushroom extends Item implements Consumable {
    private static final int maxHPIncreaseAmount = 50;
    private int jumpRate;

    /***
     * Constructor.
     *
     */
    public SuperMushroom() {
        super("Super Mushroom", '^', true);
        super.addAction(new ConsumeAction(this));
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
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

}
