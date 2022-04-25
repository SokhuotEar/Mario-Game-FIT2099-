package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

public class PowerStar extends Item implements Consumable {
    private static final int hpToHealBy = 200;
    private int lifetime;
    /***
     * Constructor.
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
        //Give the invisible buff:
        actor.addCapability(Status.INVINCIBLE);

        // Heal by 200 points:
        actor.heal(hpToHealBy);
    }
}
