package game;

import java.util.Random;

/**
 * Random generator class to deal with % probaiblities
 * @author Sok Ear
 * @version 1.0
 */
public class RNG {
    /**
     * Random object for use with the classes methods
     */
    private static final Random rand = new Random();

    /**
     * Probability generator
     * @param probability the % chance of success
     * @return true probability% of the time, and false (100-probability)% of the time
     */
    public static boolean rng(int probability) {
        int number = rand.nextInt(100);
        return (number < probability);
    }
}
