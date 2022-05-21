package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Bottle;
import game.items.Drinkable;

/**
 * Special Action that allows Actors with a Bottle to Drink from the bottle.
 *
 * @author Satya Jhaveri, Klarissa Jutivannadevi
 * @version 1.0
 */
public class DrinkFromBottleAction extends Action {
    /**
     * The bottle that the drink from bottle action will be drinking from if it is executed
     */
    private final Bottle bottle;

    /**
     * Constructor
     * @param bottle The bottle that the drink from bottle action will be drinking from if it is executed
     */
    public DrinkFromBottleAction(Bottle bottle) {
        this.bottle = bottle;
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
        // Remove a drink from the drink bottle:
        Drinkable drink = this.bottle.popDrink();
        // Run the Drinkable's drink method on the Actor
        drink.drink(actor);
        // Return descriptive string of exactly what was just drunk
        return actor + " drinks " + drink + " from the Bottle";
    }

    /**
     * Returns a descriptive string for use in the menu
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks from the " + this.bottle.printContent();
    }
}
