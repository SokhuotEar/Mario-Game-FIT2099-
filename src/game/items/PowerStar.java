package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.utils.Status;
import game.actions.ConsumeAction;

/**
 * The class PowerStar is a child class of Item class which allow
 * overriding method to modify its functionality and implements both
 * Consumable and Buyable since it can be consumed and purchased.
 *
 * @author Satya Jhaveri, Klarissa Jutivannadevi
 * @version 1.0
 */
public class PowerStar extends Item implements Consumable, Buyable {
    /**
     * hpToHealBy: The hp added when PowerStar is consumed
     */
    private static final int hpToHealBy = 200;

    /**
     * PRICE: the cost to buy PowerStar
     */
    private static final int PRICE = 600;

    /**
     * lifetime: the lifetime of PowerStar once it appears
     */
    private int lifetime;

    /**
     * consumed: whether the PowerStar has been consumed
     */
    private boolean consumed;

    /**
     * Holds the current item's consume action so that it can be added or removed from the list
     */
    private final Action consumeAction;


    /***
     * Constructor of PowerStar which creates a ConsumeAction every time
     * it is created and lifetime.
     *
     */
    public PowerStar() {
        super("Power Star", '*', true);
        consumeAction = new ConsumeAction(this);
        super.addAction(consumeAction);
        lifetime = 10;
        consumed = false;
    }

    /**
     * Decrements the lifetime of the power star and destroy it if it has been too long
     * <p>
     * This method is called once per turn, if the Item is being carried.
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor           The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        lifetime--;
        if (lifetime <= 0) {
            // remove the special effects from the player:
            for (Enum<?> capability : this.capabilitiesList()) {
                actor.removeCapability(capability);
            }
            // remove the item from the player's inventory:
            actor.removeItemFromInventory(this);
        }
        else {
            if (consumed) {
                // add the effects to the player (another item might have removed the buffs, so we must re-add them):
                for (Enum<?> capability : this.capabilitiesList()) {
                    actor.addCapability(capability);
                }
            }
        }
    }

    /**
     * Decrements the lifetime of the power star and destroy it if it has been too long
     * This method is called once per turn, if the item rests upon the ground.
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        lifetime--;
        if (lifetime <= 0) {
            currentLocation.removeItem(this);
        }
    }

    /**
     * Override the method from Action class since the PowerStar will not be dropped
     * @param actor the actor that executes getDropAction
     * @return null
     */
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

    /**
     * Give the actor power up from consuming PowerStar
     * @param actor which actor consumes the PowerStar
     */
    @Override
    public void consume(Actor actor) {
        //Give the buffs to this item (since the player will hold this item and hence get the capabilities):
        super.addCapability(Status.INVINCIBLE);

        // Heal by 200 points:
        actor.heal(hpToHealBy);

        // change the consumed status of the PowerStar:
        consumed = true;

        // set the lifetime (turns left) to 10:
        lifetime = 10;

        // remove the consume action from the item's action list (it will essentially become inert, only exists to reapply buffs every turn):
        removeAction(consumeAction);
    }

    /**
     * Accessor method for PRICE attribute
     * @return price of the PowerStar
     */
    @Override
    public int getPrice() {
        return PRICE;
    }

    /**
     * Whether the consumable should stay in the Actor's inventory
     * @return true
     */
    @Override
    public boolean stayInInventory() {
        return true;  // should stay in inventory so that the effect can last for 10 turns
    }
}
