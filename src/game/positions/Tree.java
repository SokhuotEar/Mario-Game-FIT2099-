package game.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.utils.RNG;
import game.utils.Status;
import game.items.FireFlower;
import game.reset.ResetManager;
import game.reset.Resettable;
import game.actors.enemies.Goomba;
import game.actors.enemies.Koopa;
import game.items.Coin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class Tree is a child class of Ground and implement Resettable
 * that is used for reset game.
 * @author Sok Huot Ear
 * @version 1.0
 */
public abstract class Tree extends HighGround implements Resettable {

    //attribute
    /**
     * age of tree
     */
    private int age;


    /**
     * Constructor. Starts to create Tree from sprout
     * @param displayChar the character used to represent the tree on the map
     */

    public Tree(char displayChar) {
        super(displayChar);
        this.age = 0;
        this.registerInstance();
    }

    public void incrementAge() {
        age++;
    }

    public int getAge() {
        return age;
    }

    /**
     * Resets the tree object
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
