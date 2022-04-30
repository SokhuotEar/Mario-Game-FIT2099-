package game.positions;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpAction;
import game.items.Coin;

import javax.swing.*;
import java.io.LineNumberInputStream;
import java.rmi.StubNotFoundException;
import java.util.ArrayList;
import java.util.List;

public abstract class HighGround extends Ground {
    private static final List<HighGround> highGroundList = new ArrayList<>();
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public HighGround(char displayChar) {
        super(displayChar);
        highGroundList.add(this);

    }

    /**
     * Override this to implement impassable terrain, or terrain that is only passable if conditions are met.
     *
     * @param actor the Actor to check
     * @return true
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.INVINCIBLE);
    }

    public static boolean isInstance(Ground ground) {
        return highGroundList.contains(ground);
    }

    public static HighGround getInstance(Ground ground) {
        if (isInstance(ground)) {
            return highGroundList.get(highGroundList.indexOf(ground));
        }
        else {
            return null;
        }
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
                highGroundList.remove(this);
            }
        }
        super.tick(location);
    }

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

    abstract public int getFallDamage(Actor actor);
}
