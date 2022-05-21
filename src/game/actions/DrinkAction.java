package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.enemies.behaviours.FollowBehaviour;
import game.items.Drinkable;
import game.positions.Fountain;

/**
 * Special Action that allows Actors to drink.
 *
 * @author Satya Jhaveri, Klarissa Jutivannadevi
 * @version 1.0
 */
public class DrinkAction extends Action {
    /**
     * The Fountain that the drink action will be drinking from if it is executed
     */
    private final Fountain fountain;

    /**
     * Constructor.
     * @param drinkFountain the fountain that the drink action will be drinking from if it is executed
     */
    public DrinkAction(Fountain drinkFountain) {
        this.fountain = drinkFountain;
    }

    /**
     * Perform the DrinkAction.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Drinkable drink = this.fountain.getDrink();
        drink.drink(actor);
        return menuDescription(actor);
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks the " + this.fountain.getDrinkName();
    }
}
