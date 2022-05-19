package game.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.items.Coin;
import game.items.FireFlower;
import game.utils.RNG;

public class Sapling extends Tree{
    public Sapling() {
        super('t');
    }

    /**
     * Gets the % chance of success when a jump is made
     *
     * @param actor the actor that is making the jump
     * @return the % chance of success when making a jump
     */
    @Override
    public int getJumpChanceSuccess(Actor actor) {
        return 80;
    }

    /**
     * Gets the fall damage that the actor will take if they fail the jump
     *
     * @param actor the actor that is making the jump
     * @return the HP the actor will lose if they fail the jump
     */
    @Override
    public int getFallDamage(Actor actor) {
        return 20;
    }

    /**
     * Method executed once every turn
     *
     * @param location The location of the Ground that the tree is on
     */
    @Override
    public void tick(Location location) {
        // Increment age:
        this.incrementAge();

        // Spawn coin:
        if (RNG.rng(10)) {  // 10% chance to drop a $20 coin each turn
            int coinValue = 20;
            location.addItem(new Coin(coinValue));
        }
        // If old enough, change to next stage of tree:
        if (this.getAge() == 10) {
            location.setGround(new Mature());

            // 50% chance to spawn fire flower:
            if (RNG.rng(50)) {
                location.addItem(new FireFlower());
            }
        }
    }
}
