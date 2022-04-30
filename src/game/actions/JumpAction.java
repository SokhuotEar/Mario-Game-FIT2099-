package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.RNG;

public class JumpAction extends Action {

    private final Location location;
    private final String direction;
    private final int jumpSuccess;
    private final int fallDamage;

    public JumpAction(Location moveToLocation, String direction, int jumpChanceSuccess, int fallDamage) {
        this.location = moveToLocation;
        this.direction = direction;
        this.jumpSuccess = jumpChanceSuccess;
        this.fallDamage = fallDamage;
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (RNG.rng(jumpSuccess)) {
            // move the actor to the location:
            map.moveActor(actor, location);
            return menuDescription(actor);
        }
        else {
            // do not move the actor and apply fall damage:
            actor.hurt(fallDamage);
            return actor + " failed jump and lost " + fallDamage + "HP";
        }
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " jumps " + direction + " (" + location.x() +", " + location.y() +")";
    }
}
