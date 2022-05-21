package game.actors;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.ArrayList;
import java.util.List;

public class InstrinsicWeaponBoostedManager {
    private static List<IntrinsicWeaponBooster> intrinsicWeaponBoosterList = new ArrayList<>();

    public static void addInstance(IntrinsicWeaponBooster actor) {
        intrinsicWeaponBoosterList.add(actor);
    }

    public static boolean isInstance(Actor actor) {
        for (IntrinsicWeaponBooster i : intrinsicWeaponBoosterList) {
            if (i == actor) {
                return true;
            }
        }
        return false;
    }

    public static IntrinsicWeaponBooster getInstance(Actor actor) {
        for (IntrinsicWeaponBooster i : intrinsicWeaponBoosterList) {
            if (i == actor) {
                return i;
            }
        }
        return null;
    }
}
