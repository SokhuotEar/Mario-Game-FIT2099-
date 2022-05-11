package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.actors.enemies.behaviours.WanderBehaviour;

public class PiranhaPlant extends Enemy {

    public PiranhaPlant() {
        super("Piranha Plant",'Y',5, false);
    }

}
