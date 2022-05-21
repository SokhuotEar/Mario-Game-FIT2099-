package game.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.actors.enemies.Enemy;
import game.utils.Status;

/**
 * A class that represents the floor inside a building.
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
	 * Enemies cannot enter floor tiles
	 *
	 * @param actor the Actor to check
	 * @return true
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return !actor.hasCapability(Status.CANT_ENTER_FLOOR);
	}
}
