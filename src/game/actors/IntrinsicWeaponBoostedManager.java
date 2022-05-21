package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that manages Actors that can have their Intrinsic Attack Damage Boosted.
 * @author Satya Jhaveri
 * @version 1.0
 */
public class IntrinsicWeaponBoostedManager {
    /**
     * Array of all instances of IntrinsicWeaponBooster
     */
    private static final List<IntrinsicWeaponBooster> intrinsicWeaponBoosterList = new ArrayList<>();

    /**
     * Adds an Actor that implements IntrinsicWeaponBooster to the static array
     * @param actor an Actor that implements IntrinsicWeaponBooster
     */
    public static void addInstance(IntrinsicWeaponBooster actor) {
        intrinsicWeaponBoosterList.add(actor);
    }

    /**
     * Checks if an Actor implements IntrinsicWeaponBooster
     * @param actor an Actor to check
     * @return true if the Actor implements IntrinsicWeaponBooster, false otherwise
     */
    public static boolean isInstance(Actor actor) {
        for (IntrinsicWeaponBooster i : intrinsicWeaponBoosterList) {
            if (i == actor) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets an IntrinsicWeaponBooster instance from an Actor
     * @param actor an Actor to get the IntrinsicWeaponBooster instance of
     * @return a IntrinsicWeaponBooster instance of the Actor if it exists, and null otherwise
     */
    public static IntrinsicWeaponBooster getInstance(Actor actor) {
        for (IntrinsicWeaponBooster i : intrinsicWeaponBoosterList) {
            if (i == actor) {
                return i;
            }
        }
        return null;
    }
}
