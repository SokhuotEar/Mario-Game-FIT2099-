package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.PiranhaPlant;

import java.util.ArrayList;

public class WarpPipe extends Item {
    private static int count = 0;
    private static int pipeMaxCount = 5;
    private int age = 0;

    private static ArrayList<WarpPipe> pipeList = new ArrayList<>();

    /***
     * Constructor
     * This is used to create a Warp pipe
     */
    public WarpPipe() {
        super("Warp Pipe", 'c', false);
        pipeList.add(this);
    }


    /** to check if an Item object is an instance of WarpPipe **/
    public static boolean isInstance(Item item) {
        return pipeList.contains(item);
    }

    public void addPipeAction(Action action){
        super.addAction(action);
    }

    @Override
    public void tick(Location currentLocation) {
        age++;
        super.tick(currentLocation);
        if (age == 2)
        {
            currentLocation.addActor(new PiranhaPlant());
        }
    }
}
