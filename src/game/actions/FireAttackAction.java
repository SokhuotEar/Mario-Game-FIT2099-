package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.positions.Fire;

import java.util.Random;

public class FireAttackAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * The direction of incoming attack.
     */
    protected String direction;



    /**
     * Constructor.
     *
     * @param target    the Actor to attack
     * @param direction the Direction is which the attack is taking place
     */
    public FireAttackAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }


    @Override
    public String execute(Actor actor, GameMap map) {
        //get location of the target
        Location targetLocation = map.locationOf(target);

        // add fire to the location of the target
        targetLocation.addItem(new Fire());

        return actor.toString() + " attacks " + target + " at " + direction + " using fire ";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() +  " uses Fire to attack " + target + " at " + direction + " using fire ";
    }

}
