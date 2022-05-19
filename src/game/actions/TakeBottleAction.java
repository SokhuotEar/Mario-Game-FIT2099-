package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumable;

/**
 * A class inheriting Action class used when Player take the
 * Bottle from Toad.
 * @author Klarissa Jutivannadevi
 * @version 1.0
 */
public class TakeBottleAction extends Action {

    /**
     * An object from Consumable class
     */
    private Consumable consumableItem;

    /**
     * Constructor of TakeBottleAction
     * @param consumable which is the Bottle.
     */
    public TakeBottleAction(Consumable consumable) {
        this.consumableItem = consumable;
    }

    /**
     * When this action is executed, it will add the item to
     * the player's inventory
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addItemToInventory(this.consumableItem.getItem());
        return menuDescription(actor);
    }

    /**
     * Display TakeBottleAction string in the UI
     * @param actor The actor performing the action.
     * @return the string saying "player obtain bottle from Toad"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " obtains the " + this.consumableItem + " from Toad";
    }
}
