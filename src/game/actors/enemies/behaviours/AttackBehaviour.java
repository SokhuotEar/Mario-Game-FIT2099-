package game.actors.enemies.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;

/**
 * Special Behaviour for generating Attack Actions
 * @author FIT2099
 * @version 1.0
 * @see edu.monash.fit2099.engine.actions.Action
 */
public class AttackBehaviour implements Behaviour {

    /**
     * The target to be attacked
     */
    private final Actor target;

    /**
     * Constructor.
     * @param subject The target to be attacked
     */
    public AttackBehaviour(Actor subject) {
        this.target = subject;
    }


    /**
     * Generates an AttackAction that the Actor exhibiting this behaviour can execute
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return An AttackAction on the target
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if(!map.contains(target) || !map.contains(actor))
            return null;

        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination == there) {
                return new AttackAction(target, exit.getName());
            }
        }
        return null;
    }

}
