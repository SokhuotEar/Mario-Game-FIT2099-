package game.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

public class Fire extends Ground {

    private int lifetime = 10;
    private final int damage = 20;

    /**
     * Constructor.
     *
     */
    public Fire() {
        super('v');
    }



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
        lifetime--;

        // The player standing on top takes 20 damage per round
        takeDamage(location, this.damage);
        super.tick(location);

        //spawns dirt after lifeTime ends
        if (lifetime == 0)
        {
            location.setGround(new Dirt());
        }
    }

}
