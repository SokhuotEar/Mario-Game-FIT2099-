package game.reset;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Special Action that allows Game to be reset
 *
 * @author Klarissa Jutivannadevi
 */
public class ResetAction extends Action {
    /**
     * ResetAction constructor
     */
    ResetAction() {
        System.out.println("resetactions");
    }

    /**
     * The instance will go through all the resetInstance and reset
     * the ones that should be reset.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return "Reset Game"
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run();
        return "Reset Game";
    }

    /**
     * Show the string for
     * @param actor The actor performing the action.
     * @return give description "Reset Game" in UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Reset Game";
    }

    /**
     * Decide on the hotkey to be used in the UI
     * @return "r" as the hotkey
     */
    @Override
    public String hotkey() {
        return "r";
    }
}
