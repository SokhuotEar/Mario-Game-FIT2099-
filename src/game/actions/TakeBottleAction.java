package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Bottle;
import game.items.Consumable;

/**
 * A class inheriting Action class used when Player takes the Bottle from Toad.
 * @author Klarissa Jutivannadevi
 * @version 1.0
 */
public class TakeBottleAction extends Action {

    /**
     * Constructor.
     */
    public TakeBottleAction() {
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
        actor.addItemToInventory(Bottle.takeBottle());
        return menuDescription(actor);
    }

    /**
     * Display TakeBottleAction string in the UI
     * @param actor The actor performing the action.
     * @return the string saying "player obtain bottle from Toad"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " obtains the Bottle from Toad";
    }
}
