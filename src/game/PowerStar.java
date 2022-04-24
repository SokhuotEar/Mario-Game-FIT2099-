package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;

public class PowerStar extends Item implements Consumable {
    private static final int hpToHealBy = 200;
    /***
     * Constructor.
     *
     */
    public PowerStar() {
        super("Power Star", '*', true);
        super.addAction(new ConsumeAction(this));
    }

    public void checkLifetime() {

    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    @Override
    public ConsumeAction getConsumeAction(Actor actor) {
        return new ConsumeAction(this);
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
        actor.addCapability(Status.INVISIBLE);

        // Heal by 200 points:
        actor.heal(hpToHealBy);
    }
}
