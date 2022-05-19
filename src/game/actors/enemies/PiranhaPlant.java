package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
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
    }

}
