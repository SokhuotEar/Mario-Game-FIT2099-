package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.utils.Status;
import game.actions.ConsumeAction;

/**
 * The class FireFlower is a child class of Item class which allow
 *  * overriding method to modify its functionality and implements
 *  * the Consumable interface since it can be consumed.
 * @author Sok Huot Ear, Satya Jhaveri
 * @version 1.0
 */
public class FireFlower extends Item implements Consumable {

    /**
     * The lifetime remaining of the FireFlower
     */
    private int lifetime = 20;
    /**
     * Whether the FireFlower has been consumed
     */
    private boolean consumed = false;

    /**
     * Holds the current item's consume action so that it can be added or removed from the list of allowable actions
     */
    private final Action consumeAction;


    /**
     * Constructor.
     */
    public FireFlower() {
        super ("FireFlower",'f',true);
        this.consumeAction = new ConsumeAction(this);
        super.addAction(consumeAction);
    }

    /**
     * Gets the Item form of a Consumable
     * @return This item
     */
    @Override
    public Item getItem() {
        return this;
    }

    /**
     * Method called once every round
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        // decrement its lifetime only when it is consumed
        if (!this.consumed){
            return;
        }
        lifetime--;

        if (lifetime == 0)
        {
            // remove the expired consumed FireFlower from the Actors inventory
            actor.removeItemFromInventory(this);
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
     * Consumes the FireFlower
     * @param actor the actor that will consume the FireFlower
     */
    @Override
    public void consume(Actor actor) {
        //set consumed to true
        consumed = true;

        // add the FireFlower to the player's inventory if they are not already holding it
        if (!actor.getInventory().contains(this)) {
            actor.addItemToInventory(this);
        }

        // the player can now perform fire attack, since it is holding the fireflower, giving fireflower the status also gives it to the player
        this.addCapability(Status.FIREATTACK);

        // stay in player's inventory, but the player can no longer consume it
        this.removeAction(consumeAction);
    }

}
