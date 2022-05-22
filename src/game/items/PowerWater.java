package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.IntrinsicWeaponBoostedManager;
import game.actors.IntrinsicWeaponBooster;

/**
 * Health Water class.
 * @author Satya Jhaveri
 * @version 1.0
 */
public class PowerWater implements Drinkable{

    /**
     * Method executed when this is 'drunk'
     * @param actor The actor drinking this
     */
    @Override
    public void drink(Actor actor) {
        // Increases actor's base attack by 50 hit points:
        if (IntrinsicWeaponBoostedManager.isInstance(actor)) {
            IntrinsicWeaponBooster actorWeaponBooster = IntrinsicWeaponBoostedManager.getInstance(actor);
            if (actorWeaponBooster != null) {
                actorWeaponBooster.increaseBaseAttack(15);
            }
        }
    }

    /**
     * Gets a descriptive string of the drink
     * @return A string of the Drink
     */
    @Override
    public String toString() {
        return "Power Water";
    }
}
