package game.positions;

import edu.monash.fit2099.engine.positions.Ground;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {
	private static final List<Ground> dirtList = new ArrayList<>();

	public Dirt() {
		super('.');
		dirtList.add(this);
	}

	public static boolean isInstance(Ground ground) {
		return dirtList.contains(ground);
	}
}
