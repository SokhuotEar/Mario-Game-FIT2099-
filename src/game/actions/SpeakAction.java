package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Special Action for Monologue.
 * @author Satya Jhaveri
 * @version 1.0
 * @see edu.monash.fit2099.engine.actions.Action
 */
public class SpeakAction extends Action {
    /**
     * The string that will be spoken
     */
    private final String line;

    /**
     * The Actor that is saying the line
     */
    private final Actor speaker;

    /**
     * Constructor for SpeakAction
     *
     * @param line the line of monologue that will be spoken when this action is executed.
     * @param speaker the actor that is speaking
     */
    public SpeakAction(String line, Actor speaker) {
        this.line = line;
        this.speaker = speaker;
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
        return "[" + this.speaker + "]: \"" + this.line + "\"";
    }


    /**
     * Returns a descriptive string for use in the menu
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " speaks to " + this.speaker;
    }
}
