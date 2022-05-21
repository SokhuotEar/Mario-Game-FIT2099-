package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Bottle;
import game.items.Consumable;

/**
 * Special Action for an Actor getting a Bottle from Toad
 * @author Klarissa Jutivannadevi
 * @version 1.0
 */
public class TakeBottleAction extends Action {
    private final Actor giver;
    /**
     * Constructor.
     * @param giver the actor that is giving the bottle to the other actor that executes this action
     */
    public TakeBottleAction(Actor giver) {
        this.giver = giver;
    }

    /**
     * When this action is executed, it will add the item to
     * the player's inventory
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string describing what happened
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addItemToInventory(Bottle.takeBottle());
        return menuDescription(actor);
    }

    /**
     * Display TakeBottleAction string in the UI
     * @param actor The actor performing the action.
     * @return a descriptive String
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " obtains the Bottle from " + this.giver;
    }
}
