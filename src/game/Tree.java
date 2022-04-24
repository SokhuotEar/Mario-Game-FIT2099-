package game;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import javax.swing.*;
import java.util.Random;

public class Tree extends Ground {

    //attribute
    private int age;
    private TreeType treeType;

    /**
     * Constructor.
     */
    public Tree() {
        super('+');
        this.age = 0;
        this.treeType = TreeType.SPROUT;
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
            switch (treeType) {
                case SPROUT:
                    if (new RNG().rng(10))  //10% possibility of spawning Goomba
                    {
                        location.addActor(new Goomba());
                    }

                case SAPLING:
                    // no enemy is spawned

                case MATURE:
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
        int probability;
        /*
        switch (treeType) {
            case SPROUT:
            // no coins are dropped

            case SAPLING:
            // 10% chance of dropping coins
                probability = 10;
                if (new RNG().rng(probability))
                {
                    location.addItem(new Coin());
                }

            case MATURE:
                probability = 10;
                if (new RNG().rng(probability))
                {
                    location.addItem(new Coin());
                }

        }*/
        if (treeType == TreeType.SAPLING) {
            Random rand = new Random();
            if (rand.nextInt(100) < 10) {  // spawn coin with 10% probability
                location.addItem(new Coin());
            }
        }
    }

    //method for growing new sprout
    private void growNewSprout(Location location){
        // TODO: fix the way we check if location is ground since checking displaychar is a code smell
        if ((getTreeType() == TreeType.MATURE) && this.getAge()%5 == 0) {
            // new sprouts are grown
            //check if the location has an actor and if there is already a tree

            //get random location
            int random = new Random().nextInt(3);

            if (random == 0) {
                int newX = location.x() - 1;
                int newY = location.y();
                //check if it is in map bound
                if (newX <= location.map().getXRange().min()) {
                    //check if actor the location is clear
                    if (location.map().at(newX, newY).getDisplayChar() == '.') {
                        location.map().at(newX, newY).setGround(new Tree());
                        return;
                    }
                }

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
        grow();                     // trees can grow
        spawnEnemy(location);       // trees can spawn enemies
        dropCoin(location);         // trees can drop coins
        growNewSprout(location);        // trees can grow new sprout
        wither(location);
    }


}
