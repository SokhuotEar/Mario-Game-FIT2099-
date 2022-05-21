package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Bottle;
import game.items.Drinkable;

public class DrinkFromBottleAction extends Action {
    private Bottle bottle;

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
        Drinkable drink = this.bottle.popDrink();
        drink.drink(actor);
        return actor + " drinks " + drink + " from the Bottle";
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks from the " + this.bottle.printContent();
    }
}
