package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.enemies.Enemy;

/**
 * Special Action for an Actor committing suicide.
 * @author Satya Jhaveri
 * @version 1.0
 * @see edu.monash.fit2099.engine.actions.Action
 */
public class SuicideAction extends Action {

    /**
     * Performs the Suicide Action.
     *
     * @param actor The actor committing suicide.
     * @param map   The map the actor is on.
     * @return a description of the suicide that is displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        Enemy.removeInstance(actor);
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
        return actor + " commits suicide.";
    }
}
