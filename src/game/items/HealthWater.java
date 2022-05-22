package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Health Water class.
 * @author Satya Jhaveri
 * @version 1.0
 */
public class HealthWater implements Drinkable {
    /**
     * The number of points to heal the actor that drinks this by
     */
    private static final int HEAL_POINTS = 50;

    /**
     * Method executed when this is 'drunk'
     * @param actor The actor drinking this
     */
    @Override
    public void drink(Actor actor) {
        // Health water heals Actor by 50HP
        actor.heal(HEAL_POINTS);
    }

    /**
     * Gets a descriptive string of the drink
     * @return A string of the Drink
     */
    @Override
    public String toString() {
        return "Health Water";
    }
}
