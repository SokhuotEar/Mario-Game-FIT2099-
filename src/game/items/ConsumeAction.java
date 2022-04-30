package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Special Action that allows Actors to consume items.
 *
 * @author Satya Jhaveri, Klarissa Jutivannadevi
 */
public class ConsumeAction extends Action {

    /**
     * Current item
     */
    private final Consumable consumableItem;

    /**
     * Constructor.
     *
     * @param consumableItem the item to be consumed
     */
    public ConsumeAction(Consumable consumableItem) {
        this.consumableItem = consumableItem;
    }

    /**
     * Consume the item. It will remove from inventory if the actor consumes the magical item in the inventory
     * and will take it from the ground if it is there.
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return a description of the action suitable for feedback in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // IF actor has consumable item in inventory, remove it:
        if (actor.getInventory().contains(consumableItem.getItem())) {
            actor.removeItemFromInventory(consumableItem.getItem());
        }
        else {  // Else if the item is on the ground, remove it from the ground
            map.locationOf(actor).removeItem(consumableItem.getItem());
        }

        // Add the power ups:
        consumableItem.consume(actor);

        return menuDescription(actor);
    }

    /**
     * A string describing the action suitable for displaying in the UI menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player consumes the potato"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes the " + consumableItem;
    }
}
