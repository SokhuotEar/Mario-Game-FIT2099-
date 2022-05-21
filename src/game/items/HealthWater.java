package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

public class HealthWater extends Item implements Drinkable {
    private static final int HEAL_POINTS = 50;

    /***
     * Constructor.
     */
    public HealthWater() {
        super("Health Water", 'h', false);
    }

    @Override
    public void drink(Actor actor) {
        // Health water heals Actor by 50HP
        actor.heal(HEAL_POINTS);
    }
}
