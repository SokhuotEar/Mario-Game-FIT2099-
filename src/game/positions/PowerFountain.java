package game.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

public class PowerFountain extends Ground implements Drinkable{
    /**
     * Constructor.
     *
     */
    public PowerFountain() {
        super('A');
    }

    @Override
    public void addPowerUp(Actor actor) {

    }

    @Override
    public Ground getFountain() {
        return this;
    }
}
