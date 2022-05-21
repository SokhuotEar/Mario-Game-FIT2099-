package game.actors.enemies;

import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Piranha Plant Enemy Class.
 * @author Satya Jhaveri
 * @version 1.0
 */
public class PiranhaPlant extends Enemy {

    /**
     * Constructor
     */
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
     * @param map The map that the Piranha is on
     */
    @Override
    public void resetInstance(GameMap map) {
        // Increase HP by 50 and heal to max:
        this.resetMaxHp(this.getMaxHp() + 50);
    }

    /**
     * Gets the verb of the Piranha Plant's attack
     * @return the verb of Piranha Plant's attack
     */
    @Override
    public String getVerb() {
        return "chomps";
    }
}
