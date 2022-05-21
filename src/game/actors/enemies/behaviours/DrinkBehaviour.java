package game.actors.enemies.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DrinkAction;
import game.items.Drinkable;
import game.positions.Fountain;
import game.utils.Status;

/**
 * A class that generate DrinkAction when stand in the fountain
 * @author Klarissa Jutivannadevi
 * @version 1.0
 */

public class DrinkBehaviour implements Behaviour {
    private boolean justDrank;

    public DrinkBehaviour() {
        // Let enemies drink only every other turn, otherwise while they drink the player can easily kill them, or they get too overpowered by the water
        this.justDrank = false;
    }

//    private final Drinkable drinkable;

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (this.justDrank) {
            this.justDrank = false;
        }
        else {
            Location actorLocation = map.locationOf(actor);
            if (actorLocation.getGround().hasCapability(Status.DRINKABLE)) {
                Fountain fountain = Fountain.getInstance(actorLocation.getGround());
                this.justDrank = true;
                return new DrinkAction(fountain);
            }
        }
        return null;
    }
}
