package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.items.Fire;

/**
 * Special Action for attacking other Actors with Fire.
 * @author Sok Huot Ear, Satya Jhaveri
 * @version 1.0
 */
public class FireAttackAction extends AttackAction {
    /**
     * Constructor.
     *
     * @param target    the Actor to attack
     * @param direction the Direction is which the attack is taking place
     */
    public FireAttackAction(Actor target, String direction) {
        super(target, direction);
    }


    /**
     * Executes the FireAttackAction
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a descriptive string of the action that just took place
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        //get location of the target
        Location targetLocation = map.locationOf(target);

        String result = super.execute(actor, map);
        if (this.attackSuceeded) {
            // Only add fire if the attack did not miss:
            result += " with fire!";
            // add fire to the location of the target
            targetLocation.addItem(new Fire());
        }

        return result;
    }

    /**
     *
     * @param actor The actor performing the action.
     * @return A descriptive string to display on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor +  " uses Fire to attack " + target + " at " + direction;
    }

}
