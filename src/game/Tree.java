package game;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tree extends Ground implements Resettable {

    //attribute
    private int age;
    private TreeType treeType;
    private static int treeCount = 0;
    private static final int maxTreeCount = 50; // new sprouts will only grow if there are less than this number of trees, avoids overcrowding

    /**
     * Constructor.
     */
    public Tree() {
        super('+');
        this.age = 0;
        this.treeType = TreeType.SPROUT;
        treeCount++;
        this.resetInstance();
    }

    //getters


    //Methods

    //method that changes tree type based on display character
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
                if (new RNG().rng(10))  //10% possibility of spawning Goomba
                {
                    location.addActor(new Goomba());
                }
            }
            else if (treeType == TreeType.MATURE) {
                if (new RNG().rng(15))  //15% possibility of spawning Koopa
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

        if (new RNG().rng(probability)) {
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
        if (this.treeType == TreeType.MATURE && new Random().nextInt(5) == 0) {  // 20% chance to wither and die if mature
            location.setGround(new Dirt());  // change current location to Dirt
        }
    }


    public void tick(Location location){
        grow();                         // trees can grow
        spawnEnemy(location);           // trees can spawn enemies
        dropCoin(location);             // trees can drop coins
        growNewSprout(location);        // trees can grow new sprout
        wither(location);               // trees can wither
    }

    @Override
    public void resetInstance() {
        if (new RNG().rng(50)) {
            //setDisplayChar(new Dirt().getDisplayChar());
        }
    }


}
