package game.actors.enemies.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * A class that generate DrinkAction when stand in the fountain
 * @author Klarissa Jutivannadevi
 * @version 1.0
 */

public class DrinkBehaviour implements Behaviour {

//    private final Drinkable drinkable;

    @Override
    public Action getAction(Actor actor, GameMap map) {

//        if(!map.contains(target) || !map.contains(actor))
//            return null;
//
//        Location here = map.locationOf(actor);
//        Location there = map.locationOf(target);
//        for (Exit exit : here.getExits()) {
//            Location destination = exit.getDestination();
//            if (destination == there) {
//                return new AttackAction(target, exit.getName());
//            }
//        }
        return null;
    }
}
