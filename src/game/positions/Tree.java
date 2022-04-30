package game.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.RNG;
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

    //getters


    //Methods

    /**
     * method that changes tree type based on display character
     */
    private void changeTreeStatus() {
        char treeDisplay = getDisplayChar();
        if (treeDisplay == 't') {
            this.treeType = TreeType.SAPLING;
        }
        if (treeDisplay == 'T') {
            this.treeType = TreeType.MATURE;
        }
    }

    // function for trees growing
    private void grow() {
        // Increment age:
        age++;

        // Update status and display char if necessary:
        if (age == 10) {
            // trees grow to sapling in 10 turns
            setDisplayChar('t');
            changeTreeStatus();
        } else if (age == 20) {
            // trees grow to mature in another 10 turns
            setDisplayChar('T');
            changeTreeStatus();
        }
    }

    // function for tree spawning Goomba
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

    // trees can drop coins
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

    //method for growing new sprout
    private void growNewSprout(Location location){
        // check the tree is mature, and it has been 5 turns since the last growNewSprout, and we have not exceeded the maximum tree count
        if (this.treeType == TreeType.MATURE & this.age%5== 0 & treeCount <= maxTreeCount) {
            // create list to contain fertile surrounding squares:
            List<Location> fertileLocations = new ArrayList<>();
            // iterate over surrounding locations to add fertile ground (dirt):
            for (Exit exit : location.getExits()) {
                Ground ground = exit.getDestination().getGround();
                if (Dirt.isInstance(ground)) {
                    fertileLocations.add(exit.getDestination());
                }
            }

            // Pick a random square to spawn a sprout in:
            if (!fertileLocations.isEmpty()) {
                fertileLocations.get(new Random().nextInt(fertileLocations.size())).setGround(new Tree());
            }
        }
    }

    // Method for withering away
    private void wither(Location location) {
        if (this.treeType == TreeType.MATURE && RNG.rng(20)) {  // 20% chance to wither and die if mature
            location.setGround(new Dirt());  // change current location to Dirt
        }
    }


    public void tick(Location location){

        grow();                         // trees can grow
        spawnEnemy(location);           // trees can spawn enemies
        dropCoin(location);             // trees can drop coins
        growNewSprout(location);        // trees can grow new sprout
        wither(location);               // trees can wither
        super.tick(location);
    }

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

    @Override
    public void resetInstance() {
        if (RNG.rng(50)) {
            //setDisplayChar(new Dirt().getDisplayChar());

        }
    }


}
