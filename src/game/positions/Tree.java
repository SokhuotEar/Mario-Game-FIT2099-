package game.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.RNG;
import game.Status;
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
 * @author Sok Huot Ea
 * @version 1.0
 */
public class Tree extends HighGround implements Resettable {

    //attribute
    /**
     * age of tree
     */
    private int age;

    /**
     * the stages of tree represented with enumerator
     */
    private TreeType treeType;

    /**
     * the count for tree in the map
     */
    private static int treeCount = 0;

    /**
     * maximum tree allowed in the map
     */
    private static final int maxTreeCount = 50; // new sprouts will only grow if there are less than this number of trees, avoids overcrowding

    /**
     * Constructor. Starts to create Tree from sprout
     */
    public Tree() {
        super('+');
        this.age = 0;
        this.treeType = TreeType.SPROUT;
        treeCount++;
        this.registerInstance();
    }



    /**
     * Function for incrementing the age of the tree and updating tree's type if it has grown enough
     */
    private void grow() {
        // Increment age:
        age++;

        // Update tree type and display char if necessary:
        if (age == 10) {
            // trees grow to sapling in 10 turns
            this.treeType = TreeType.SAPLING;
            super.setDisplayChar('t');
        }
        else if (age == 20) {
            // trees grow to mature in another 10 turns
            this.treeType = TreeType.MATURE;
            super.setDisplayChar('T');
        }
    }


    /**
     * Spawns an Enemy
     * @param location the location that the tree is on
     */
    private void spawnEnemy(Location location) {

        //check if an actor is here
        if (!location.containsAnActor()){
            // if not, then do the following

            if (treeType == TreeType.SPROUT) {
                if (RNG.rng(10))  //10% possibility of spawning Goomba
                {
                    location.addActor(new Goomba());
                }
            }
            else if (treeType == TreeType.MATURE) {
                if (RNG.rng(15))  //15% possibility of spawning Koopa
                {
                    location.addActor(new Koopa());
                }
            }
        }
    }

    /**
     * Drops a coin by chance
     * @param location the location that the tree is on
     */
    private void dropCoin(Location location) {
        // for RNG purposes
        int probability = 0;
        if (treeType == TreeType.SAPLING) {
            probability = 10;
        }

        if (RNG.rng(probability)) {
            int coinValue = 20;
            location.addItem(new Coin(coinValue));
        }

    }

    /**
     * Grows a new sprout in fertile dirt every 5 rounds
     * @param location the location that the tree is on
     */
    private void growNewSprout(Location location){
        // check the tree is mature, and it has been 5 turns since the last new sprout, and we have not exceeded the maximum tree count (avoids crowding)
        if (this.treeType == TreeType.MATURE & this.age%5== 0 & treeCount <= maxTreeCount) {
            // create list to contain fertile surrounding squares:
            List<Location> fertileLocations = new ArrayList<>();
            // iterate over surrounding locations to add fertile ground:
            for (Exit exit : location.getExits()) {
                Ground ground = exit.getDestination().getGround();
                if (ground.hasCapability(Status.FERTILE)) {
                    fertileLocations.add(exit.getDestination());
                }
            }

            // Pick a random square to spawn a sprout in:
            if (!fertileLocations.isEmpty()) {
                fertileLocations.get(new Random().nextInt(fertileLocations.size())).setGround(new Tree());
            }
        }
    }

    /**
     * Chance to wither and die
     * @param location the location that the tree is on
     */
    private void wither(Location location) {
        if (this.treeType == TreeType.MATURE && RNG.rng(20)) {  // 20% chance to wither and die if mature
            location.setGround(new Dirt());  // change current location to Dirt
        }
    }


    /**
     * Method executed once every turn
     * @param location The location of the Ground that the tree is on
     */
    @Override
    public void tick(Location location){
            grow();                         // trees can grow
            spawnEnemy(location);           // trees can spawn enemies
            dropCoin(location);             // trees can drop coins
            growNewSprout(location);        // trees can grow new sprout
            wither(location);               // trees can wither
            super.tick(location);

    }

    /**
     * Gets the % chance of success when a jump is made
     * @param actor the actor that is making the jump
     * @return the % chance of success when making a jump
     */
    @Override
    public int getJumpChanceSuccess(Actor actor) {
        int jumpSuccess = 0;
        switch (treeType) {
            case MATURE -> jumpSuccess = 70;
            case SAPLING -> jumpSuccess = 80;
            case SPROUT -> jumpSuccess = 90;
        }
        return jumpSuccess;
    }

    /**
     * Gets the fall damage if the actor fails the jump
     * @param actor the actor that is making the jump
     * @return the number of HP that the actor will lose if they fail the jump
     */
    @Override
    public int getFallDamage(Actor actor) {
        int fallDamage = 0;
        switch (treeType) {
            case MATURE -> fallDamage = 30;
            case SAPLING -> fallDamage = 20;
            case SPROUT -> fallDamage = 10;
        }
        return fallDamage;
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
