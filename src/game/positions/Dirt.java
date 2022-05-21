package game.positions;

import edu.monash.fit2099.engine.positions.Ground;
import game.utils.Status;

/**
 * A class that represents bare dirt.
 * @author FIT2099, extended by Satya Jhaveri
 * @version 1.0
 */
public class Dirt extends Ground {
	/**
	 * Constructor
	 */
	public Dirt() {
		super('.');
		super.addCapability(Status.FERTILE);
	}

}
