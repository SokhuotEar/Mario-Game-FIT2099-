package game.actors.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.SpeakAction;
import java.util.List;
import java.util.Random;

/**
 * A special behaviour for generating speak actions
 * @author Satya
 * @version 1.0
 */
public class SpeakBehaviour implements Behaviour {
    /**
     * A list of possible strings to say
     */
    private final List<String> lines;

    /**
     * Whether the exhibitor of this behaviour spoke in the previous round
     */
    private boolean justSpoke;

    /**
     * Constructor
     * @param lines A list of possible strings to say
     */
    public SpeakBehaviour(List<String> lines)
    {
        this.justSpoke = false;
        this.lines = lines;
    }

    /**
     * Adds another monologue line
     * @param line a new line to say
     */
    public void addLine(String line)
    {
        this.lines.add(line);
    }

    /**
     * Gets an action to execute
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return an action to execute
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (this.justSpoke)
        {
            this.justSpoke = false;
            return null;
        }
        else
        {
            this.justSpoke = true;
            return new SpeakAction(lines.get(new Random().nextInt(lines.size())), actor);
        }
    }
}
