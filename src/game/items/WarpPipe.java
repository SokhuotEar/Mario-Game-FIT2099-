package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TeleportAction;
import game.actors.enemies.PiranhaPlant;
import game.positions.HighGround;

import java.util.ArrayList;
import java.util.List;

public class WarpPipe extends HighGround {
    private int age;
    private boolean spawnPlant;
    private Location destination;
    private Location location;
    private static List<WarpPipe> pipeList = new ArrayList<>();

    /***
     * Constructor
     * This is used to create a Warp pipe
     */
    public WarpPipe() {
        super('c');
        this.spawnPlant = true;
        this.age = 0;
        this.destination = null;
        this.location = null;
        pipeList.add(this);
    }

    public static boolean isInstance(Ground ground) {
        return pipeList.contains(ground);
    }

    public static WarpPipe getInstance(Ground ground) {
        if (isInstance(ground)) {
            for (WarpPipe pipe : pipeList) {
                if (pipe == ground) {
                    return pipe;
                }
            }
        }
        return null;
    }


    // Links two pipes together
    public static void linkPipes(WarpPipe pipe1, Location position1, WarpPipe pipe2, Location position2) {
        pipe1.destination = position2;
        pipe1.location = position1;
        pipe2.destination = position1;
        pipe2.location = position2;
    }

    @Override
    public void tick(Location currentLocation) {
        this.age++;
        super.tick(currentLocation);
        if (age >= 2 && this.spawnPlant && !currentLocation.containsAnActor())
        {
            currentLocation.addActor(new PiranhaPlant());
            this.spawnPlant = false;
        }
    }

    /**
     * Gets the % chance of success when a jump is made
     *
     * @param actor the actor that is making the jump
     * @return the % chance of success when making a jump
     */
    @Override
    public int getJumpChanceSuccess(Actor actor) {
        return 100;
    }

    /**
     * Gets the fall damage that the actor will take if they fail the jump
     *
     * @param actor the actor that is making the jump
     * @return the HP the actor will lose if they fail the jump
     */
    @Override
    public int getFallDamage(Actor actor) {
        return 0;
    }

    /**
     * Returns Action list with teleport action
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, empty collection of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor, location, direction);

        // Add the teleport action if the destination has been initialised and the piranha plant has been spawned (and hence killed)
        if (this.destination != null && location.getActor()==actor) {  // TODO: add !spawnPlant in this if statement
            actions.add(new TeleportAction(this.destination));
        }
        return actions;
    }
}
