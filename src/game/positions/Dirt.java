package game.positions;

import edu.monash.fit2099.engine.positions.Ground;
import game.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents bare dirt.
 * @author FIT2099, extended by Satya Jhaveri
 * @version 1.0
 */
public class Dirt extends Ground {
	/**
	 * Constructor which create and add dirt to dirtList
	 */
	public Dirt() {
		super('.');
		super.addCapability(Status.FERTILE);
	}

}
