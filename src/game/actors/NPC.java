package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.enemies.behaviours.Behaviour;
import game.actors.enemies.behaviours.BehaviourPriority;
import game.actors.enemies.behaviours.SpeakBehaviour;

import java.util.*;

/**
 * This is a class that all non playable actors will extend from. It encapsulates the speaking that all NPCs will do
 */
public abstract class NPC extends Actor {
    private SpeakBehaviour speakBehaviour;

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public NPC(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.speakBehaviour = null;
    }

    // Sets the lines for the NPCs speaking:
    public void setLines(List<String> lines)
    {
        this.speakBehaviour = new SpeakBehaviour(lines);
    }

    // Allows the NPC to speak:
    public void speak(Display display, Actor actor, GameMap map)
    {
        Action speakAction = speakBehaviour.getAction(actor, map);

        // If the getAction method doesn't return null, print its execution to the display:
        if (speakAction != null)
        {
            display.println("[" + actor + "]: \"" + speakAction.execute(actor, map) + '"');
        }

    }

}
