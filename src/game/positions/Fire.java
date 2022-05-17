package game.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

public class Fire extends Item {

    private int groundLifetime = 3;
    private final int damage = 20;

    /***2
     *
     * Constructor.
     */
    public Fire() {
        super("Fire", 'v', false);
    }

    /**
     * Constructor.
     *
     */



    public void takeDamage(Location location, int damage) {
        if (location.containsAnActor()) {
            Actor actor = location.getActor();
            //the player takes damage
            actor.hurt(damage);

            //printing
            String println = actor + "takes " + damage + " damage due to fire burning.";
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
        groundLifetime--;

        // The player standing on top takes 20 damage per round
        takeDamage(location, this.damage);
        super.tick(location);

        //spawns dirt after lifeTime ends
        if (groundLifetime == 0)
        {
            location.removeItem(this);
        }

    }



}
