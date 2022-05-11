package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actors.Player;

public class FireFlower extends Item implements Consumable {

    private int lifetime = 5;
    private static int count = 0;
    private boolean consumed = false;
    private static final int maxCount = 100;
    private Action consumeAction;


    /***
     * Constructor.
     */

    public FireFlower() {
        super ("FireFlower",'f',false);
        this.consumeAction = new ConsumeAction(this);
        super.addAction(consumeAction);
        count++;
    }

    public static int getCount() {
        return count;
    }

    public static int getMaxCount() {
        return maxCount;
    }

    @Override
    public Item getItem() {
        return this;
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation, actor);

        // decrement its lifetime only when it is consumed
        if (!this.consumed){
            return;
        }
        lifetime--;
        if (lifetime == 0)
        {
            actor.removeCapability(Status.FIREATTACK);
        }
        String println = lifetime + " round left." ;
        new Display().println(println);
    }

    @Override
    public void consume(Actor actor) {
        // only actor can consume the fire attack
        if (!Player.isInstance(actor))
        {
            return;
        }
        //set consumed to true
        consumed = true;

        //temporarily add it to the inventory list
        actor.addItemToInventory(this);
        // the player can now perform fire attack
        actor.addCapability(Status.FIREATTACK);
        //
        this.removeAction(consumeAction);
    }

}
