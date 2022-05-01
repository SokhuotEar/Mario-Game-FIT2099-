package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.*;
import game.actions.AttackAction;
import game.actors.enemies.behaviours.WanderBehaviour;
import game.items.SuperMushroom;
import game.items.Wrench;

/**
 * Koopa Enemy Class.
 * @author Satya Jhaveri
 * @version 1.0
 */
public class Koopa extends Enemy {
    /**
     * Whether the Koopa is in its shell or not
     */
    private boolean dormant;

    /**
     * Constructor.
     */
    public Koopa() {
        super("Koopa", 'K', 100);
        addBehaviour(10, new WanderBehaviour());
        SuperMushroom mushroom = new SuperMushroom();
        mushroom.togglePortability();
        addItemToInventory(mushroom);
        dormant = false;
    }

    /**
     * Figures out what action the Goomba will take next
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return An action that the Koopa will execute
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

    /**
     * Creates and returns an intrinsic weapon.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "punch");
    }

    /**
     * Do some damage to the current Actor.
     * <p>
     * If the Actor's hitpoints go down to zero, it will be knocked out.
     *
     * @param points number of hitpoints to deduct.
     */
    @Override
    public void hurt(int points) {

        super.hurt(points);  // do the damage

        // Make Koopa go dormant if not conscious:
        if (!dormant && !isConscious()) {
                goDormant();
        }

    }

    /**
     * Makes the Koopa go inside its shell
     */
    private void goDormant() {
        // set max hp to the damage done by a wrench
        resetMaxHp(50);

        // set the dormant attribute to true
        dormant = true;

        // update the display character
        setDisplayChar('D');

        // remove all behaviours:
        clearBehaviours();
    }


    /**
     * If the Koopa is dormant, it can only be attacked by an actor with a Wrench. Otherwise, can be attacked by anyone
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     * @see Status#HOSTILE_TO_ENEMY
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        if (!dormant) {
            return super.allowableActions(otherActor, direction, map);
        }
        else {  // if the koopa is dormant, it can only be attacked if the other actor has a wrench
            if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                ActionList actions =  new ActionList();
                // iterate over the attackers' inventory to check if they have a wrench
                for (Item item : otherActor.getInventory()) {
                    if (Wrench.isInstance(item)){
                        actions.add(new AttackAction(this, direction));
                        break;
                    }
                }
                return actions;
            }
        }
        return new ActionList();
    }
}
