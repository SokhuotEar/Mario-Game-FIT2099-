package game.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Player;
import game.actors.enemies.Enemy;
import game.utils.Status;

public class Lava extends Ground {

    private final static int damage = 15;

    /**
     * Constructor.
     */
    public Lava() {
        super('L');
    }


    @Override
    public boolean canActorEnter(Actor actor) {
        // an actor can not enter if it is an instance of Enemy
        return !actor.hasCapability(Status.CANT_ENTER_LAVA);
    }

    public void takeDamage(Location location, int damage) {
        if (location.containsAnActor()) {
            Actor player = location.getActor();
            //the player takes damage
            player.hurt(damage);

            String println = player + "takes " + damage + " damage from stepping on Lava.";
            new Display().println(println);
        }
    }

    /**
     * Lava can burn the player
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        // The player standing on top takes 15 damage per round
        takeDamage(location, Lava.damage);
        super.tick(location);
    }
}

