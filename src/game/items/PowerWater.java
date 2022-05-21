package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actors.InstrinsicWeaponBoostedManager;
import game.actors.IntrinsicWeaponBooster;

public class PowerWater extends Item implements Drinkable{
    /***
     * Constructor.
     */
    public PowerWater() {
        super("Power Water", 'p', false);
    }

    @Override
    public void drink(Actor actor) {
        // Increases actor's base attack by 50 hit points:
        if (InstrinsicWeaponBoostedManager.isInstance(actor)) {
            IntrinsicWeaponBooster actorWeaponBooster = InstrinsicWeaponBoostedManager.getInstance(actor);
            if (actorWeaponBooster != null) {
                actorWeaponBooster.increaseBaseAttack(15);
            }
        }
    }
}
