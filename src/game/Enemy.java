package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

import javax.management.MBeanNotificationInfo;
import java.util.*;

public abstract class Enemy extends Actor {
    private static List<Enemy> enemyList = new ArrayList<>();
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
            behaviours.put(9, new FollowBehaviour(otherActor));
            behaviours.put(8, new AttackBehaviour(otherActor));
        }
        else {
            behaviours.remove(8);
        }
        return actions;
    }

    public void addBehaviour(Integer priority, Behaviour behaviour) {
        behaviours.put(priority, behaviour);
    }

    public Map<Integer, Behaviour> getBehaviours() {
        return behaviours;
    }

    // removes all behaviours from an enemy
    public void clearBehaviours() {
        behaviours = new TreeMap<>();
    }
}
