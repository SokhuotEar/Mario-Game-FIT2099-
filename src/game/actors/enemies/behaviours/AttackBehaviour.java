package game.actors.enemies.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;

public class AttackBehaviour implements Behaviour {

    private final Actor target;

    public AttackBehaviour(Actor subject) {
        this.target = subject;
    }

    // TODO: develop and use it to attack the player automatically.
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
