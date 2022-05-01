package game.actors.enemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.reset.Resettable;
import game.Status;
import game.actors.enemies.behaviours.*;
import java.util.*;

/**
 * An abstract base class for all Enemies to extend from
 * @author FIT2099, extended by Satya Jhaveri
 * @version 1.0
 */
public abstract class Enemy extends Actor implements Resettable {
    private static final List<Enemy> enemyList = new ArrayList<>();
    private Map<Integer, Behaviour> behaviours = new TreeMap<>(); // priority, behaviour

    /**
     * Constructor.
     *
     * @param name        the name of the Enemy
     * @param displayChar the character that will represent the Enemy in the display
     * @param hitPoints   the Enemy's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.behaviours.put(BehaviourPriority.WANDERER.ordinal(), new WanderBehaviour());
        enemyList.add(this);
        this.registerInstance();
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

    /**
     * Removes an enemy from the static enemy list
     * @param actor the enemy to be removed
     */
    public static void removeInstance(Actor actor) {
        if (isInstance(actor)) {
            enemyList.remove(actor);
        }
    }

    /**
     * At the moment, we only make it can be attacked by Player.
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            // If other actor is hostile to enemy, add attack action and add follow behaviour
            actions.add(new AttackAction(this,direction));
            behaviours.put(BehaviourPriority.FOLLOW.ordinal(), new FollowBehaviour(otherActor));
            behaviours.put(BehaviourPriority.ATTACK.ordinal(), new AttackBehaviour(otherActor));
        }
        else {
            behaviours.remove(BehaviourPriority.ATTACK.ordinal());
        }
        return actions;
    }

    /**
     * Adds a behaviour to the Map of behaviours
     * @param priority the priority of the behaviour (smaller number -> higher priority)
     * @param behaviour the Behaviour to add
     */
    public void addBehaviour(Integer priority, Behaviour behaviour) {
        behaviours.put(priority, behaviour);
    }

    /**
     * Gets the map of behaviours
     * @return A map of the enemies' behaviours
     */
    public Map<Integer, Behaviour> getBehaviours() {
        return behaviours;
    }

    /**
     * Removes all behaviours from an enemy
     */
    public void clearBehaviours() {
        behaviours = new TreeMap<>();
    }

    /**
     * Resets the Enemy instance
     */
    @Override
    public void resetInstance() {
        for (Enemy enemy : enemyList) {
            enemy.resetMaxHp(0);
        }

    }
}
