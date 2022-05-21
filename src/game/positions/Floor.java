package game.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.utils.Status;

/**
 * A class that represents the floor.
 * @author FIT2099, modified by Satya Jhaveri
 * @version 1.0
 */
public class Floor extends Ground {

	/**
	 * Constructor of Floor
	 */
	public Floor() {
		super('_');
	}


	/**
	 * Checks if an Actor can enter this Ground
	 *
	 * @param actor the Actor to check
	 * @return true if the Actor can enter the floor
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return !actor.hasCapability(Status.CANT_ENTER_FLOOR);
	}
}
