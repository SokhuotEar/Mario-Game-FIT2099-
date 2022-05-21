package game.positions;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RefillAction;
import game.items.Bottle;
import game.items.Drinkable;
import game.items.HealthWater;

public class HealthFountain extends Fountain {
    /**
     * Constructor.
     *
     */
    public HealthFountain() {
        super('H', new HealthWater());
    }

    @Override
    public String toString() {
        return "Health Fountain";
    }
}
