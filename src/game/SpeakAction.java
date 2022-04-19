package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class SpeakAction extends Action {
    private String line;

    /**
     * Overloaded constructor for SpeakAction
     *
     * @param line the line of monologue that will be spoken when this action is executed.
     */
    public SpeakAction(String line) {
        this.line = line;
    }

    /**
     * Perform the SpeakAction, returns a string to speak.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a string that the actor will speak
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return line;
    }


    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " speaks to Toad";
    }
}
