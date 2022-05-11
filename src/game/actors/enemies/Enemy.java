package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.actions.FireAttackAction;
import game.actors.Player;
import game.reset.ResetManager;
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
    private boolean canWander;      //check if the enemy can Wander

    /**
     * Constructor.
     *
     * @param name        the name of the Enemy
     * @param displayChar the character that will represent the Enemy in the display
     * @param hitPoints   the Enemy's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints, boolean canWander) {
        super(name, displayChar, hitPoints);
        this.canWander = canWander;
        if (canWander) {
            // put wander behaviour only if the enemy can move
            this.behaviours.put(BehaviourPriority.WANDERER.ordinal(), new WanderBehaviour());
        }
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
     * Adds an enemy to the static enemy list
     * @param enemy the enemy to be added
     */
    public static void addInstance(Enemy enemy) {
        if (enemy != null) {
            enemyList.add(enemy);
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
            // If other actor is hostile to enemy, add attack action and add follow/attack behaviour
            actions.add(new AttackAction(this,direction));
            // it will follow only if it can move
            if (canWander) {
                behaviours.put(BehaviourPriority.FOLLOW.ordinal(), new FollowBehaviour(otherActor));
            }
            behaviours.put(BehaviourPriority.ATTACK.ordinal(), new AttackBehaviour(otherActor));
        }
        else {
            // remove the attack behaviour if the hostile_to_enemy actor is not adjacent to this enemy
            behaviours.remove(BehaviourPriority.ATTACK.ordinal());
        }

        // at the moment, only the player can use fire attack
        if (Player.isInstance(otherActor) & otherActor.hasCapability(Status.FIREATTACK)) {
            actions.add(new FireAttackAction(this,direction));
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
    public void resetInstance(GameMap map) {
        // drop every item in inventory:
        for (Item item : getInventory()) {
            map.locationOf(this).addItem(item);
        }

        // remove Enemy from map and EnemyList
        map.removeActor(this);
        Enemy.removeInstance(this);
        // remove Enemy from the reset manager (since it is destroyed)
        ResetManager.getInstance().cleanUp(this);
    }

    /**
     * Do some damage to the current Actor. Removes the Enemy from the Enemy list if it dies
     * <p>
     * If the Actor's hitpoints go down to zero, it will be knocked out.
     *
     * @param points number of hitpoints to deduct.
     */
    @Override
    public void hurt(int points) {
        super.hurt(points);
        if (!isConscious()) {
            removeInstance(this);
        }
    }

    /**
     * Figures out what action the Enemy will take next
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return An action that the Enemy will execute
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for(game.actors.enemies.behaviours.Behaviour Behaviour : getBehaviours().values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }
}
