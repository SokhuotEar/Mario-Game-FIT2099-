package game;

import java.util.Random;

/** @Author: Sok Ear
 * Use this whenever you need to generate RNG (or work with probability)
 * **/


public class RNG {

    // method
    // rng(probability):    just input the probability needed, then it will give True and False
    //                      true indicates success, false means fail

    public boolean rng(int probability) {
        Random rand = new Random();
        int number = rand.nextInt(99);

        return (number < probability);
    }

}
