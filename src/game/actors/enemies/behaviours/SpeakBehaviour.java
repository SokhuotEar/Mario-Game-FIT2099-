package game.actors.enemies.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.SpeakAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpeakBehaviour implements Behaviour {
    private List<String> lines;
    private boolean justSpoke;

    public SpeakBehaviour(List<String> lines)
    {
        this.justSpoke = false;
        this.lines = lines;
    }

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
            return new SpeakAction(lines.get(new Random().nextInt(lines.size())));
        }

    }
}
