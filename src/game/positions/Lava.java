package game.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Player;
import game.actors.enemies.Enemy;
import game.utils.Status;

/**
 * Lava Class.
 * @author Sok Ear, Satya Jhaveri
 * @version 1.0
 */
public class Lava extends Ground {
    /**
     * The damage that is inflicted to actors that stand on Lava
     */
    private final static int damage = 15;

    /**
     * Constructor.
     */
    public Lava() {
        super('L');
    }

    /**
     * Checks if an actor can enter the Lava ground
     * @param actor the Actor to check
     * @return true if the actor can enter, false otherwise
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        // an actor can not enter if it is an instance of Enemy
        return !actor.hasCapability(Status.CANT_ENTER_LAVA);
    }

    /**
     * Lava can burn Actors
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        // The player standing on top takes 15 damage per round
        if (location.containsAnActor()) {
            Actor player = location.getActor();
            //the player takes damage
            player.hurt(Lava.damage);

            String println = player + "takes " + Lava.damage + " damage from stepping on Lava.";
            new Display().println(println);
        }
        super.tick(location);
    }
}

