package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

/**
 * Health Water class.
 * @author Satya Jhaveri
 * @version 1.0
 */
public class HealthWater extends Item implements Drinkable {
    /**
     * The number of points to heal the actor that drinks this by
     */
    private static final int HEAL_POINTS = 50;

    /**
     * Constructor.
     */
    public HealthWater() {
        super("Health Water", 'h', false);
    }

    /**
     * Method executed when this is 'drunk'
     * @param actor The actor drinking this
     */
    @Override
    public void drink(Actor actor) {
        // Health water heals Actor by 50HP
        actor.heal(HEAL_POINTS);
    }
}
