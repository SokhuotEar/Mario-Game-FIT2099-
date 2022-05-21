package game.positions;

import edu.monash.fit2099.engine.positions.GameMap;
import game.utils.RNG;
import game.reset.ResetManager;
import game.reset.Resettable;

/**
 * The abstract Tree Class
 * @author Sok Huot Ear, Satya Jhaveri
 * @version 1.0
 */
public abstract class Tree extends HighGround implements Resettable {
    /**
     * the age of the tree
     */
    private int age;


    /**
     * Constructor.
     * @param displayChar the character used to represent the tree on the map
     */

    public Tree(char displayChar) {
        super(displayChar);
        this.age = 0;
        this.registerInstance();
    }

    /**
     * Increments the age of the tree
     */
    public void incrementAge() {
        age++;
    }

    /**
     * Gets the age of the tree
     * @return the age of the tree
     */
    public int getAge() {
        return age;
    }

    /**
     * Resets the tree object
     * @param map The map that the Tree is on
     */
    @Override
    public void resetInstance(GameMap map) {
        if (RNG.rng(50)) {
            boolean found = false;
            for (int x : map.getXRange()) {
                for (int y : map.getYRange()) {
                    if (map.at(x,y).getGround() == this) {
                        found = true;
                        map.at(x,y).setGround(new Dirt());
                        break;
                    }
                }
                if (found) {
                    break;
                }
            }
            ResetManager.getInstance().cleanUp(this);
        }
    }



}
