package game.positions;

import edu.monash.fit2099.engine.positions.Ground;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents bare dirt.
 * @author FIT2099, extended by Satya Jhaveri
 * @version 1.0
 */
public class Dirt extends Ground {
	/**
	 * A list that contains the bare dirt
	 */
	private static final List<Ground> dirtList = new ArrayList<>();

	/**
	 * Constructor which create and add dirt to dirtList
	 */
	public Dirt() {
		super('.');
		dirtList.add(this);
	}

	/**
	 * An instance that is used to check whether a specific ground is
	 * within the dirtList
	 *
	 * @param ground the ground to be checked
	 * @return boolean based on whether the ground is in dirtList
	 */
	public static boolean isInstance(Ground ground) {
		return dirtList.contains(ground);
	}
}
