package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.Player;
import game.actors.enemies.behaviours.WanderBehaviour;

import java.util.ArrayList;
import java.util.List;

public class PiranhaPlant extends Enemy {

    public PiranhaPlant() {
        super("Piranha Plant",'Y',40, false);
        List<String> lines = new ArrayList<>();
        lines.add("Slsstssthshs~! (Never gonna say goodbye~)");
        lines.add("Ohmnom nom nom nom.");
        this.setLines(lines);
        this.setBaseAttackDamage(90);
    }


    /**
     * Resets the Enemy instance
     *
     * @param map
     */
    @Override
    public void resetInstance(GameMap map) {
        // Increase HP by 50 and heal to max:
        this.resetMaxHp(this.getMaxHp() + 50);
    }

    @Override
    public String getVerb() {
        return "chomps";
    }
}
