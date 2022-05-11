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
    private String direction;
    private Location start;

    public TeleportAction(Location destination) {
        this.destination = destination;
        this.direction = "To the Lava Zone";
    }

    /** This method teleports Player from the Main Map to Lava Zone. Then it creates a new Warp that returns
     * the player back to Main Map at the exact starting location.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */

    @Override
    public String execute(Actor actor, GameMap map) {

        if (!Player.isInstance(actor))
        {
            return null;
        }

        // record the starting point of the player, as player will need to return to the same location
        start = map.locationOf(actor);

        // teleport player
        new MoveActorAction(destination,direction).execute(actor,map);

        // remove any warp item on Lava zone at (0,0) that already exists
        // to avoid duplication
        ArrayList<Item> itemToRemove = new ArrayList<>();
        for (Item item: destination.getItems())
        {
            if (WarpPipe.isInstance(item))
            {
                itemToRemove.add(item);
            }
        }

        for (Item item: itemToRemove)
        {
            destination.removeItem(item);
        }

        // create a returning pipe from Lava Zone to the main map
        // the pipe can send player back to 'start'
        WarpPipe returningPipe = new WarpPipe();
        returningPipe.addPipeAction(new MoveActorAction(start, "To the Main Map"));
        destination.addItem(returningPipe);



        return "Player teleports to Lava Zone";

    }

    @Override
    public String menuDescription(Actor actor) {
        return "Teleport to lava Zone";
    }




}
