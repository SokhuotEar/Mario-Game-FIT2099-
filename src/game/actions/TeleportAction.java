package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Player;
import game.items.WarpPipe;

import java.util.ArrayList;

public class TeleportAction extends Action {
    private Location destination;

    public TeleportAction(Location teleportDestination) {
        this.destination = teleportDestination;
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Set the destination of the destination pipe to the location of the current pipe:
        Location currentPosition = map.locationOf(actor);
        WarpPipe.linkPipes(WarpPipe.getInstance(currentPosition.getGround()), currentPosition, WarpPipe.getInstance(destination.getGround()), destination);

        // Remove the actor from its current map:
        map.removeActor(actor);

        // Remove actors from the destination location so that the teleport can proceed:
        if (this.destination.containsAnActor()) {
            this.destination.map().removeActor(this.destination.getActor());
        }

        // Add the actor to the destination location:
        this.destination.addActor(actor);

        return this.menuDescription(actor);
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " enters the warp pipe";
    }
}
