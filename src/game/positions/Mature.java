package game.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.FlyingKoopa;
import game.actors.enemies.Koopa;
import game.reset.ResetManager;
import game.utils.RNG;
import game.utils.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Mature Tree Class.
 * @author Satya Jhaveri
 * @version 1.0
 */
public class Mature extends Tree{
    /**
     * Constructor. Starts to create Tree from sprout
     */
    public Mature() {
        super('T');
    }

    /**
     * Gets the % chance of success when a jump is made
     *
     * @param actor the actor that is making the jump
     * @return the % chance of success when making a jump
     */
    @Override
    public int getJumpChanceSuccess(Actor actor) {
        return 70;
    }

    /**
     * Gets the fall damage that the actor will take if they fail the jump
     *
     * @param actor the actor that is making the jump
     * @return the HP the actor will lose if they fail the jump
     */
    @Override
    public int getFallDamage(Actor actor) {
        return 30;
    }

    /**
     * Method executed once every turn
     *
     * @param location The location of the Ground that the tree is on
     */
    @Override
    public void tick(Location location) {
        // Increment age:
        this.incrementAge();

        // Spawn Koopa or FLying Koopa:
        if (RNG.rng(15) && !location.containsAnActor()) {
            // Spawn Koopa 50% of the time
            if (RNG.rng(50)) {
                location.addActor(new Koopa());
            }
            else {
                location.addActor(new FlyingKoopa());
            }
        }

        // Every 5 rounds, spawn new tree:
        if (this.getAge()%5== 0) {
            // create list to contain fertile surrounding squares:
            List<Location> fertileLocations = new ArrayList<>();
            // iterate over surrounding locations to add fertile ground:
            for (Exit exit : location.getExits()) {
                Ground ground = exit.getDestination().getGround();
                if (ground.hasCapability(Status.FERTILE)) {
                    fertileLocations.add(exit.getDestination());
                }
            }

            // Pick a random square to spawn a sprout in:
            if (!fertileLocations.isEmpty()) {
                fertileLocations.get(new Random().nextInt(fertileLocations.size())).setGround(new Sprout());
            }
        }

        // 20% chance to wither and die:
        if (RNG.rng(20)) {
            location.setGround(new Dirt());
            // Remove from the reset manager:
            ResetManager.getInstance().cleanUp(this);
        }
    }
}
