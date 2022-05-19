package game.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Goomba;
import game.items.FireFlower;
import game.reset.ResetManager;
import game.utils.RNG;

public class Sprout extends Tree{
    public Sprout() {
        super('+');
    }

    /**
     * Gets the fall damage if the actor fails the jump
     *
     * @param actor the actor that is making the jump
     * @return the number of HP that the actor will lose if they fail the jump
     */
    @Override
    public int getFallDamage(Actor actor) {
        return 10;
    }

    /**
     * Gets the % chance of success when a jump is made
     *
     * @param actor the actor that is making the jump
     * @return the % chance of success when making a jump
     */
    @Override
    public int getJumpChanceSuccess(Actor actor) {
        return 90;
    }

    /**
     * Method executed once every turn
     *
     * @param location The location of the Ground that the tree is on
     */
    @Override
    public void tick(Location location) {
        // Increase age:
        this.incrementAge();

        // Spawn Goomba:
        if (RNG.rng(10) && !location.containsAnActor())  //10% possibility of spawning Goomba
        {
            location.addActor(new Goomba());
        }

        // If the tree is old enough, grow:
        if (this.getAge() == 10) {
            location.setGround(new Sapling());
            // 50% chance to spawn fire flower:
            if (RNG.rng(50)) {
                location.addItem(new FireFlower());
            }
        }
    }
}
