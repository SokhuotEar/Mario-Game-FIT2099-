package game.positions;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RefillAction;
import game.items.Bottle;

public class PowerFountain extends Ground implements Drinkable{

    private final int FULL_CAPACITY = 10;
    private int currentVolume;
    private int count;

    /**
     * Constructor.
     *
     */
    public PowerFountain() {
        super('A'); }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        if (actor.getInventory().contains(Bottle.getInstance()) && direction == "") {
            actions.add(new RefillAction(this));
        }
        return actions;
    }



    @Override
    public void addPowerUp(Actor actor) {

    }

    @Override
    public Ground getFountain() {
        return this;
    }

    @Override
    public int getCapacity() {
        return this.currentVolume;
    }

    @Override
    public void setVolume(int drink) {
        this.currentVolume = drink;
    }

    @Override
    public String fountainName() {
        return "Power water";
    }

    @Override
    public String printCapacity() {
        return "(" + currentVolume + "/" + FULL_CAPACITY + ")";
    }

    @Override
    public void tick(Location location) {
        if (count == 5) {
            setVolume(FULL_CAPACITY);
            count = 0;
        }
        if (currentVolume <= 0) {
            count++;
        }
    }
}
