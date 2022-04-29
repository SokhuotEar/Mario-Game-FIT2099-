package game;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import javax.swing.*;
import java.util.Random;
import java.util.HashSet;

public class Tree extends Ground implements Resettable {

    //attribute
    private int age;
    private TreeType treeType;
    private static int treeCount = 0;
    private static final int maxTreeCount = 50; // new sprouts will only grow if there are less than this number of trees

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
    public int getAge() {
        return age;
    }

    public TreeType getTreeType() {
        return treeType;
    }

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

        treeType = getTreeType(); // get tree type

        //No Goomba is spawned if the player is here so:
        //check if an actor is here
        if (!location.containsAnActor()){
            // if not, then do the following

            if (treeType == treeType.SPROUT) {
                if (new RNG().rng(10))  //10% possibility of spawning Goomba
                {
                    location.addActor(new Goomba());
                }
            }
            else if (treeType == treeType.SAPLING) {
                return;
            }

            else if (treeType == treeType.MATURE) {
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
        if (treeType == treeType.SPROUT) {
            probability = 10;
        }
        else if (treeType == treeType.SAPLING) {
            probability = 10;
        }

        else if (treeType == treeType.MATURE) {
            probability = 10;
        }


        if (!(location.getItems() instanceof Coin)) {
            // no coins are spawned if there is already an item there
            if (new RNG().rng(probability)) {
                int coinValue = 20;
                location.addItem(new Coin(coinValue));
            }
        }

    }

    //method for growing new sprout
    private void growNewSprout(Location location){
        if (this.treeType == TreeType.MATURE & this.age%5== 0 & treeCount <= maxTreeCount) {

            // see what location is possible
            CheckEmptyLocation checkLocation = new CheckEmptyLocation();
            Boolean hasTreeSpawn = false;
            int newX = location.x();
            int newY = location.y();


            int random= new Random().nextInt(3);//0 = up; 1 = down; 2 = left; 3 = right
            //get random location
            while (hasTreeSpawn == false) {
                if (random == 0 & checkLocation.upEmpty(location)) {
                    //tree spawn can spawn up
                    hasTreeSpawn = true;
                    newY = newY-1;

                } else if (random == 1 & checkLocation.downEmpty(location)) {
                    //tree spawn can spawn down
                    hasTreeSpawn = true;
                    newY = newY+1;
                } else if (random == 2 & checkLocation.rightEmpty(location)) {
                    //tree spawn can spawn right
                    hasTreeSpawn = true;
                    newX = newX+1;
                } else if (random == 3 & checkLocation.leftEmpty(location)) {
                    //tree spawn can spawn left
                    hasTreeSpawn = true;
                    newX = newX-1;
                } else {
                    hasTreeSpawn = false;
                    random = new Random().nextInt(3);
                }

            }

            //spawn trees
            if (!(location.map().at(newX,newY).getGround() instanceof Wall)) {
                location.map().at(newX, newY).setGround(new Tree());
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
