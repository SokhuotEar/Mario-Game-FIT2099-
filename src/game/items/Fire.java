package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Fire item class
 * @author Sok Ear
 * @version 1.0
 */
public class Fire extends Item {
    /**
     * The number of turns that fire will exist for on the ground
     */
    private int groundLifetime;

    /**
     * The damage that will be inflicted on Actors by the Fire
     */
    private static final int damage = 20;

    /**
     * Constructor.
     */
    public Fire() {
        super("Fire", 'v', false);
        this.groundLifetime = 3;
    }

    /**
     * Method called once every turn
     * @param location The location that the Fire is in
     */
    @Override
    public void tick(Location location) {
        groundLifetime--;

        // The Actor standing on top takes 20 damage per round
        if (location.containsAnActor()) {
            Actor actor = location.getActor();
            // the Actor takes damage
            actor.hurt(damage);
            String result = actor + "takes " + damage + " damage due to fire burning.";

            // If actor not concious, drop all items and remove from map:
            if (!actor.isConscious()) {
                // Drop items:
                for (Item i : actor.getInventory()) {
                    if (i.getDropAction(actor) != null) {
                        i.getDropAction(actor).execute(actor, location.map());
                    }
                }
                // Remove actor from map:
                location.map().removeActor(actor);
                result = actor + "died from stepping on fire.";
            }

            new Display().println(result);
        }

        super.tick(location);

        //spawns dirt after lifeTime ends
        if (groundLifetime == 0)
        {
            location.removeItem(this);
        }

    }



}
