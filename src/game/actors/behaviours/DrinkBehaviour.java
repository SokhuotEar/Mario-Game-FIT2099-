package game.actors.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DrinkAction;
import game.positions.Fountain;
import game.utils.Status;

/**
 * A special behaviour for generating DrinkActions
 * @author Klarissa Jutivannadevi
 * @version 1.0
 */
public class DrinkBehaviour implements Behaviour {
    /**
     * Whether whatever exhibits this behaviour drank in the previous turn
     */
    private boolean justDrank;

    /**
     * Constructor
     */
    public DrinkBehaviour() {
        // Let enemies drink only every other turn, otherwise while they drink the player can easily kill them, or they get too overpowered by the water
        this.justDrank = false;
    }

    /**
     * Gets a drink action if the actor did not drink in the previous round
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return null if the actor drank in the previous round, otherwise returns a drink action if the actor is on a drinkable ground
     */
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
