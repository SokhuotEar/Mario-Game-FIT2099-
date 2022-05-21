package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Bottle;
import game.items.Drinkable;
import game.positions.Fountain;

/**
 * Special Action that allows Actors with a Bottle to fill up the bottle from a Fountain
 *
 * @author Satya Jhaveri
 * @version 1.0
 */
public class RefillAction extends Action {
    /**
     * The fountain that the Bottle will be filled from if this action is executed
     */
    private final Fountain fountain;

    /**
     * Constructor.
     * @param drinkFountain The fountain that the Bottle will be filled from if this action is executed
     */
    public RefillAction(Fountain drinkFountain) {
        this.fountain = drinkFountain;
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.getInventory().contains(Bottle.getInstance())) {
            Bottle bottle = Bottle.getInstance();
            bottle.addDrink(this.fountain.getDrink());
            return menuDescription(actor);
        }
        else {
            return actor + " did not refill from the fountain, as they did not have the Bottle";
        }
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " refills bottle from the " + this.fountain + "(" + this.fountain.getCapacity() + "/" + this.fountain.getMaxCapacity() + ")";
    }
}
