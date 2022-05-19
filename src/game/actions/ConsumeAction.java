package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Bottle;
import game.items.Consumable;

import java.util.Iterator;

/**
 * Special Action that allows Actors to consume items.
 *
 * @author Satya Jhaveri, Klarissa Jutivannadevi
 * @version 1.0
 */
public class ConsumeAction extends Action {

    /**
     * Current item
     */
    private final Consumable consumableItem;

    /**
     * Constructor of consumeAction
     *
     * @param consumable the item to consume
     */
    public ConsumeAction(Consumable consumable) {
        this.consumableItem = consumable;
    }

    /**
     * Consume the item, which is done by removing from inventory if item exist or
     * taking it from the map if item is in that location.
     *
     * @param actor The actor performing the action
     * @param map The map the actor is on
     * @return a description of the action suitable for feedback in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // IF actor has consumable item in inventory, remove it unless it should stay in the players inventory:
        if (actor.getInventory().contains(this.consumableItem.getItem())) {
            if (this.consumableItem.getItem() == Bottle.getInstance()) {
                if (Bottle.getInstance().getDrink().size() != 0) {
                    return actor + " consumes the " + Bottle.getInstance().removeDrink().fountainName();
                }

            }

            else if (!this.consumableItem.stayInInventory()) {
                actor.removeItemFromInventory(this.consumableItem.getItem());
            }

        }

        else {  // Else if the item is on the ground, remove it from the ground unless it should stay in player's inventory
            if (this.consumableItem.stayInInventory()) {
                actor.addItemToInventory((this.consumableItem.getItem()));
            }
            map.locationOf(actor).removeItem(this.consumableItem.getItem());
        }

        // Add the power ups:
        this.consumableItem.consume(actor);

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
        if (consumableItem.getItem() == Bottle.getInstance() && !Bottle.getInstance().getDrink().isEmpty()) {
            return actor + " consumes the " + Bottle.getInstance() + " " + Bottle.getInstance().printContent();
        }
        else
            return actor + " consumes the " + consumableItem; }

}
