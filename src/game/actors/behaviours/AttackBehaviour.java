package game.actors.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.utils.Status;

/**
 * Special Behaviour for generating Attack Actions
 * @author FIT2099
 * @version 1.0
 * @see edu.monash.fit2099.engine.actions.Action
 */
public class AttackBehaviour implements Behaviour {

    /**
     * Generates an AttackAction that the Actor exhibiting this behaviour can execute
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return An AttackAction on the target
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if(!map.contains(actor))
            return null;

        Location here = map.locationOf(actor);
        // Check the exits to see if a hostile to enemy actor is nearby:
        for (Exit exit : here.getExits()) {
            if (exit.getDestination().containsAnActor()) {
                Actor otherActor = exit.getDestination().getActor();
                if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    return new AttackAction(otherActor, exit.getName());
                }
            }
        }
        return null;
    }

}
