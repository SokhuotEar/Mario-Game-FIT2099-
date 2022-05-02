package game.positions;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Wall class.
 * @author FIT2099, modified by Satya Jhaveri
 * @version 1.0
 */
public class Wall extends HighGround {

	/**
	 * Constructor
	 */
	public Wall() {
		super('#');
	}

	/**
	 * Gets the % chance of success when a jump is made
	 * @param actor the actor that is making the jump
	 * @return the % chance of success when making a jump
	 */
	@Override
	public int getJumpChanceSuccess(Actor actor) {
		return 80;
	}

	/**
	 * Gets the fall damage that the actor will take if they fail the jump
	 * @param actor the actor that is making the jump
	 * @return the HP the actor will lose if they fail the jump
	 */
	@Override
	public int getFallDamage(Actor actor) {
		return 20;
	}
}
