package game.actors.enemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.items.SuperMushroom;
import game.utils.Status;

import java.util.ArrayList;
import java.util.List;

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
        super("Koopa", 'K', 100,true);
        //addBehaviour(10, new WanderBehaviour());
        SuperMushroom mushroom = new SuperMushroom();
        addItemToInventory(mushroom);
        dormant = false;
        List<String> lines = new ArrayList<>();
        lines.add("Never gonna make you cry!");
        lines.add("Koopi koopi koopii~!");
        this.setLines(lines);
        this.setBaseAttackDamage(30);
    }

    /**
     * Overloaded Constructor that accepts custom name.
     */
    public Koopa(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints,true);
        //addBehaviour(10, new WanderBehaviour());
        SuperMushroom mushroom = new SuperMushroom();
        addItemToInventory(mushroom);
        dormant = false;
        List<String> lines = new ArrayList<>();
        lines.add("Never gonna make you cry!");
        lines.add("Koopi koopi koopii~!");
        this.setLines(lines);
        this.setBaseAttackDamage(30);
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

        // remove monologue lines:
        this.removeLines();

        // remove all behaviours (so the Koopa doesn't follow, wander, or attack):
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
                // check if they have a wrench
                if (otherActor.hasCapability(Status.CAN_DESTROY_KOOPA_SHELLS)) {
                    actions.add(new AttackAction(this, direction));
                }
                return actions;
            }
        }
        return new ActionList();
    }

    /**
     * Gets the verb of the Koopa's attack
     * @return the verb of Koopa's attack
     */
    @Override
    public String getVerb() {
        return "punch";
    }
}
