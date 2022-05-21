package game.actors.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.FireAttackAction;
import game.utils.Status;

/**
 * A special behaviour for generating FireAttackActions
 * @author Satya Jhaveri
 * @version 1.0
 */
public class FireAttackBehaviour implements Behaviour{

    /**
     * @param actor the Actor acting
     * @param map   the GameMap containing the Actor
     * @return a FireAttackAction that actor can perform, or null if actor can't do this.
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
                    return new FireAttackAction(otherActor, exit.getName());
                }
            }
        }
        return null;
    }
}
