package game.positions;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.utils.Status;
import game.actions.JumpAction;
import game.items.Coin;

/**
 * Abstract higher ground class
 * @author Satya Jhaveri
 * @version 1.0
 */
public abstract class HighGround extends Ground {

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public HighGround(char displayChar) {
        super(displayChar);
    }

    /**
     * Actors can only enter if they have Status. INVINCIBLE
     *
     * @param actor the Actor to check
     * @return true if the actor is invincible, false otherwise
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.INVINCIBLE) | actor.hasCapability(Status.FLYING);
    }

    /**
     * Ground can also experience the joy of time.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        // if the ground contains an actor that is invincible, replace ground with dirt and drop a $5 coin:
        if (location.containsAnActor()) {
            if (location.getActor().hasCapability(Status.INVINCIBLE)) {
                location.setGround(new Dirt());
                location.addItem(new Coin(5));
            }
        }
        super.tick(location);
    }

    /**
     * Gets the % chance of success when a jump is made
     * @param actor the actor that is making the jump
     * @return the % chance of success when making a jump
     */
    abstract public int getJumpChanceSuccess(Actor actor);

    /**
     * Returns Action list with Jump action in it (if the actor is not invincible).
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, empty collection of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor, location, direction);

        // only add jump option if the player is not invincible and the ground is not the current ground
        if (!actor.hasCapability(Status.INVINCIBLE) && !location.containsAnActor()) {
            int jumpChanceSuccess;
            if (actor.hasCapability(Status.TALL)) {
                jumpChanceSuccess = 100;
            }
            else {
                jumpChanceSuccess = getJumpChanceSuccess(actor);
            }
            actions.add(new JumpAction(location, direction, jumpChanceSuccess, getFallDamage(actor)));
        }

        return actions;
    }

    /**
     * Gets the fall damage that the actor will take if they fail the jump
     * @param actor the actor that is making the jump
     * @return the HP the actor will lose if they fail the jump
     */
    abstract public int getFallDamage(Actor actor);
}
