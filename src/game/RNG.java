package game;

import java.util.Random;

/** @Author: Sok Ear
 * Use this whenever you need to generate RNG (or work with probability)
 * **/


public class RNG {
    private static final Random rand = new Random();

    // method
    // rng(probability):    just input the probability needed, then it will give True and False
    //                      true indicates success, false means fail

    public static boolean rng(int probability) {
        int number = rand.nextInt(99);

        return (number < probability);
    }

}
