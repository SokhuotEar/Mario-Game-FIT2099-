package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.utils.RNG;

/**
 * Special Action for jumping to higher ground.
 * @author Satya Jhaveri
 * @version 1.0
 * @see edu.monash.fit2099.engine.actions.Action
 */
public class JumpAction extends Action {

    /**
     * The location to jump to (if the jump is successful)
     */
    private final Location location;

    /**
     * The direction the jump is being made in
     */
    private final String direction;

    /**
     * The probability of the jump succeeding
     */
    private final int jumpSuccess;

    /**
     * The fall damage the actor will take if the jump is not successful
     */
    private final int fallDamage;

    /**
     * Constructor.
     * @param moveToLocation The location to jump to (if the jump is successful)
     * @param direction The direction the jump is being made in
     * @param jumpChanceSuccess The probability of the jump succeeding
     * @param fallDamage The fall damage the actor will take if the jump is not successful
     */
    public JumpAction(Location moveToLocation, String direction, int jumpChanceSuccess, int fallDamage) {
        this.location = moveToLocation;
        this.direction = direction;
        this.jumpSuccess = jumpChanceSuccess;
        this.fallDamage = fallDamage;
    }

    /**
     * Perform the JumpAction.
     *
     * @param actor The actor performing the jump.
     * @param map   The map the actor is on.
     * @return a string describing the outcome of the jump.
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
