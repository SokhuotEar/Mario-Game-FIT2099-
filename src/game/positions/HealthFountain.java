package game.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

public class HealthFountain extends Ground implements Drinkable{

    int healPoint = 50;

    /**
     * Constructor.
     *
     */
    public HealthFountain() {
        super('H');
    }

    @Override
    public void addPowerUp(Actor actor) {
        actor.heal(healPoint);

    }

    @Override
    public Ground getFountain() {
        return this;
    }
}
