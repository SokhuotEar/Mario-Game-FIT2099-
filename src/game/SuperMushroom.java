package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

/**
 * Class dedicated for the functionality of SuperMushroom
 *
 * @author Klarissa Jutivannadevi
 */

public class SuperMushroom extends Item implements Consumable {
    private int mushroomHP;
    private int jumpRate;
    /***
     * Constructor.
     *
     */
    public SuperMushroom() {
        super("Super Mushroom", '^', true);
    }

    public int increaseHP(){
        this.mushroomHP = 50;
        return this.mushroomHP; 
    }
    
    public void changeBig(Actor player) {
        player.addCapability(Status.TALL);
    }
}
