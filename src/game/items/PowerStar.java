package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * The class PowerStar is a child class of Item class which allow
 * overriding method to modify its functionality and implements both
 * Consumable and Buyable since it can be consumed and purchased.
 *
 * @author Satya Jhaveri, Klarissa Jutivannadevi
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

    /***
     * Constructor of PowerStar which creates a ConsumeAction every time
     * it is created and lifetime.
     *
     */
    public PowerStar() {
        super("Power Star", '*', true);
        super.addAction(new ConsumeAction(this));
        lifetime = 10;
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
            actor.removeItemFromInventory(this);
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
        //Give the invisible buff:
        actor.addCapability(Status.INVINCIBLE);

        // Heal by 200 points:
        actor.heal(hpToHealBy);
    }

    /**
     * Accessor method for PRICE attribute
     * @return price of the PowerStar
     */
    @Override
    public int getPrice() {
        return PRICE;
    }

}
