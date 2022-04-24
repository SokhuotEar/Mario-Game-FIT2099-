package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Enemy extends Actor {
    private static List<Enemy> enemyList = new ArrayList<>();
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.behaviours.put(10, new WanderBehaviour());
        enemyList.add(this);
    }

    /**
     * Checks if an actor is an enemy or not.
     *
     * @param actor     an instance of an Actor subclass
     * @return true if the actor is an enemy, and false otherwise
     */
    public static boolean isInstance(Actor actor) {
        boolean result = false;
        for (Enemy current : enemyList) {
            if (current == actor) {
                result = true;
                break;
            }
        }

        return result;
    }

    public void addBehaviour(Integer priority, Behaviour behaviour) {
        behaviours.put(priority, behaviour);
    }

    public Map<Integer, Behaviour> getBehaviours() {
        return behaviours;
    }
}
